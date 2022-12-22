package org.antwhale.bpo.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antwhale.blo.CommonUserInfoBLO;
import org.antwhale.bpo.CommonUserInfoBPO;
import org.antwhale.dto.userinfodto.CommonUserParamDTO;
import org.antwhale.entity.CommonUserInfo;
import org.antwhale.mapper.SequenceService;
import org.antwhale.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 何欢
 * @Date: 2022/9/26 19:39
 * @Description:
 */
@Service
public class CommonUserInfoBPOImpl implements CommonUserInfoBPO {
    @Autowired
    private CommonUserInfoBLO commonUserInfoBLO;

    @Autowired
    private SequenceService sequenceService;
    /**
     * @author 何欢
     * @Date 11:50 2022/10/2
     * @Description 用户公共信息查询
     **/
    @Override
    public Page<CommonUserInfo> queryCommonUser(CommonUserParamDTO commonUserQueryParamDTO) {
        //Mybatis查询参数组装
        QueryWrapper<CommonUserInfo> queryWrapper = getQueryWrapper(commonUserQueryParamDTO);
        //分页参数，默认第1页，10条数据
        Long currentPage = 1L;
        Long pageSize = 10L;
        if (CommonUtils.isNotNull(commonUserQueryParamDTO.getCurrentPage())) {
            currentPage = commonUserQueryParamDTO.getCurrentPage();
        }
        if (CommonUtils.isNotNull(commonUserQueryParamDTO.getPageSize())) {
            pageSize = commonUserQueryParamDTO.getPageSize();
        }
        //分页查询
        Page<CommonUserInfo> commonUserInfoIPage = commonUserInfoBLO.page(new Page(currentPage, pageSize), queryWrapper);
        if (CommonUtils.isNull(commonUserInfoIPage.getRecords())) {
            throw new RuntimeException("根据条件为查询到用户信息");
        }
        return commonUserInfoIPage;
    }

    /**
     * @author 何欢
     * @Date 21:42 2022/10/1
     * @Description 头像上传 - 每个用户每天只会留下一个头像
     **/
    @Override
    public String uploadAvatar(MultipartFile file) {
        String userIdSeq = sequenceService.getUserIdSeq();
        //获取阿里云OSS实例
//        OSS ossClient = AliyunOSSUtils.getOssClient();
//        // 填写Bucket名称
//        String bucketName = "antwhalebucket";
////        String nowDate = SimpleDateUtils.getNowDate("yyyyMMdd");
//        String fileSuffix = CommonUtils.getFileSuffix(file);
//        String upLoadName = "avatar/" + userIdSeq + "." + fileSuffix;
//        try {
//            InputStream inputStream = file.getInputStream();
//            // 上传文件。
//            ossClient.putObject(bucketName, upLoadName, inputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
        return userIdSeq;
    }

    /**
     * @author 何欢
     * @Date 11:51 2022/10/2
     * @Description 新增用户
     **/
    @Override
    public String addUserInfo(CommonUserParamDTO commonUserParamDTO) {
        //密码【BCryptPasswordEncoder加密】
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        CommonUserInfo commonUserInfo = new CommonUserInfo();
        commonUserInfo.setCommonUserinfoId(commonUserParamDTO.getCommonUserinfoId());
        commonUserInfo.setCommonUserinfoAccount(commonUserParamDTO.getCommonUserinfoPhone());
        commonUserInfo.setCommonUserinfoHeadImg(commonUserParamDTO.getCommonUserinfoHeadImg());
        commonUserInfo.setCommonUserinfoUsername(commonUserParamDTO.getCommonUserinfoUsername());
        commonUserInfo.setCommonUserinfoSex("1");
        commonUserInfo.setCommonUserinfoPassword(bCryptPasswordEncoder.encode("123456"));
        commonUserInfo.setValidflag("1");
        commonUserInfo.setCommonUserinfoPhone(commonUserParamDTO.getCommonUserinfoPhone());
        commonUserInfo.setCommonUserinfoAuth(commonUserParamDTO.getCommonUserinfoAuth());
        commonUserInfo.setCommonUserinfoIntroduction(commonUserParamDTO.getCommonUserinfoIntroduction());
        commonUserInfoBLO.save(commonUserInfo);
        return "ok";
    }

    /**
     * @author 何欢
     * @Date 0:53 2022/10/5
     * @Description 查询用户信息参数校验
     **/
    private QueryWrapper<CommonUserInfo> getQueryWrapper(CommonUserParamDTO commonUserQueryParamDTO) {
        QueryWrapper<CommonUserInfo> queryWrapper = new QueryWrapper<>();

        Optional.ofNullable(commonUserQueryParamDTO.getCommonUserinfoId())
                .ifPresent(commonUserinfoId -> queryWrapper.eq("common_userinfo_id", commonUserinfoId));
        Optional.ofNullable(commonUserQueryParamDTO.getCommonUserinfoAccount())
                .ifPresent(commonUserinfoAccount -> queryWrapper.eq("common_userinfo_account", commonUserinfoAccount));
        Optional.ofNullable(commonUserQueryParamDTO.getCommonUserinfoUsername())
                .ifPresent(commonUserinfoUsername -> queryWrapper.eq("common_userinfo_username", commonUserinfoUsername));
        Optional.ofNullable(commonUserQueryParamDTO.getCommonUserinfoSex())
                .ifPresent(commonUserinfoSex -> queryWrapper.eq("common_userinfo_sex", commonUserinfoSex));
        Optional.ofNullable(commonUserQueryParamDTO.getCommonUserinfoPhone())
                .ifPresent(commonUserinfoPhone -> queryWrapper.eq("common_userinfo_phone", commonUserinfoPhone));
        Optional.ofNullable(commonUserQueryParamDTO.getWeifxUserinfoId())
                .ifPresent(weifxUserinfoId -> queryWrapper.eq("weifx_userinfo_id", weifxUserinfoId));
        if (CommonUtils.isNotNull(commonUserQueryParamDTO.getCommonUserinfoAuth())) {
            queryWrapper.eq("common_userinfo_auth", commonUserQueryParamDTO.getCommonUserinfoAuth());
        }

        if (CommonUtils.isNotNull(commonUserQueryParamDTO.getUserinfoRegistertime())) {
            String endDate;
            String startDate;
            List<String> registerTimeList = commonUserQueryParamDTO.getUserinfoRegistertime();
            if (CommonUtils.isNull(registerTimeList.get(0))) {
                throw new RuntimeException("请填写开始日期");
            }
            if (CommonUtils.isNull(registerTimeList.get(1))) {
                throw new RuntimeException("请填写终止日期");
            }
            startDate = commonUserQueryParamDTO.getUserinfoRegistertime().get(0);
            endDate = commonUserQueryParamDTO.getUserinfoRegistertime().get(1);
            queryWrapper.apply("date_format(createtime,'%Y-%m-%d') between {0} and {1}", startDate, endDate);
        }
        return queryWrapper;
    }
}
