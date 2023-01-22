package org.antwhale.bpo.impl;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antwhale.blo.CommonUserInfoBLO;
import org.antwhale.blo.EduCourseUserBLO;
import org.antwhale.bpo.CommonUserInfoBPO;
import org.antwhale.dto.course.UserCourseParamDTO;
import org.antwhale.dto.userinfodto.CommonUserParamDTO;
import org.antwhale.entity.CommonUserInfo;
import org.antwhale.entity.EduCourseUser;
import org.antwhale.mapper.SequenceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private EduCourseUserBLO eduCourseUserBLO;

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
        if (CommonUtils.IsNotNull(commonUserQueryParamDTO.getCurrentPage())) {
            currentPage = commonUserQueryParamDTO.getCurrentPage();
        }
        if (CommonUtils.IsNotNull(commonUserQueryParamDTO.getPageSize())) {
            pageSize = commonUserQueryParamDTO.getPageSize();
        }
        //分页查询
        Page<CommonUserInfo> commonUserInfoIPage = commonUserInfoBLO.page(new Page(currentPage, pageSize), queryWrapper);
        if (CommonUtils.IsNull(commonUserInfoIPage.getRecords())) {
            throw new RuntimeException("根据条件为查询到用户信息");
        }
        return commonUserInfoIPage;
    }

    /**
     * @author 何欢
     * @Date 18:52 2022/12/25
     * @Description 用户基本信息查询 - 不分页
     **/
    @Override
    public List<CommonUserInfo> queryCommonUserNoPage(CommonUserParamDTO commonUserParamDTO) {
        //Mybatis查询参数组装
        QueryWrapper<CommonUserInfo> queryWrapper = getQueryWrapper(commonUserParamDTO);

        List<CommonUserInfo> commonUserInfoList = commonUserInfoBLO.list(queryWrapper);

        return commonUserInfoList;
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
        String userIdSeq = sequenceService.getUserIdSeq();
        //密码【BCryptPasswordEncoder加密】
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        CommonUserInfo commonUserInfo = new CommonUserInfo();
        commonUserInfo.setCommonUserinfoId(userIdSeq);
        commonUserInfo.setCommonUserinfoAccount(commonUserParamDTO.getCommonUserinfoPhone());
        commonUserInfo.setCommonUserinfoHeadImg(commonUserParamDTO.getCommonUserinfoHeadImg());
        commonUserInfo.setCommonUserinfoUsername(commonUserParamDTO.getCommonUserinfoUsername());
        commonUserInfo.setCommonUserinfoSex(commonUserParamDTO.getCommonUserinfoSex());
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
     * @Date 17:31 2022/12/25
     * @Description 分发课程给用户
     **/
    @Override
    public void assignCourse(UserCourseParamDTO userCourseParamDTO) {
        //参数校验
        validAssignCourseMethod(userCourseParamDTO);

        //得到分发课程入库保存入参
        List<EduCourseUser> saveEduCourseUserParam = getSaveEduCourseUserParam(userCourseParamDTO);

        eduCourseUserBLO.saveOrUpdateBatch(saveEduCourseUserParam);
    }

    @Override
    public List<EduCourseUser> queryUserCourse(String userId) {
        List<EduCourseUser> eduCourseUserList = eduCourseUserBLO.list(new QueryWrapper<EduCourseUser>().eq("common_userinfo_id", userId));
        return eduCourseUserList;
    }

    /**
     * @author 何欢
     * @Date 11:48 2022/10/2
     * @Description 修改用户
     **/
    @Override
    public void editUserInfo(CommonUserParamDTO commonUserParamDTO) {
        //参数校验
        validSaveOrUpdateParam(commonUserParamDTO);

        CommonUserInfo commonUserInfo = new CommonUserInfo();
        BeanUtils.copyProperties(commonUserParamDTO,commonUserInfo);

        commonUserInfoBLO.updateById(commonUserInfo);
    }

    /**
     * @author 何欢
     * @Date 17:52 2022/12/25
     * @Description 得到分发课程入库保存入参
     **/
    private List<EduCourseUser> getSaveEduCourseUserParam(UserCourseParamDTO userCourseParamDTO) {
        List<EduCourseUser> eduCourseUserList = new ArrayList<>();

        //用户id
        List<String> commonUserinfoIdList = userCourseParamDTO.getCommonUserinfoIdList();

        //课程id
        List<String> courseIdList = userCourseParamDTO.getCourseIdList();

        //已经被分配过的用户
        List<EduCourseUser> eduCourseUsers = eduCourseUserBLO.list(
                new QueryWrapper<EduCourseUser>().in("common_userinfo_id", commonUserinfoIdList)
        );

        //遍历所有用户
        commonUserinfoIdList
                .stream()
                .forEach(userId -> {
                    //该用户已经被分配过课程
                    String courseIdHaveAssigned = eduCourseUsers
                            .stream()
                            .filter(eduCourseUser -> eduCourseUser.getCommonUserinfoId().equals(userId))
                            .map(EduCourseUser::getCourseId)
                            .collect(Collectors.joining(","));
                    //该用户未被分配的课程
                    courseIdList
                            .stream()
                            .filter(courseId -> !courseIdHaveAssigned.contains(courseId))
                            .forEach(courseId -> {
                                EduCourseUser courseUser = new EduCourseUser();
                                courseUser.setCommonUserinfoId(userId);
                                courseUser.setCourseId(courseId);
                                eduCourseUserList.add(courseUser);
                            });
                });
        return eduCourseUserList;
    }

    /**
     * @author 何欢
     * @Date 17:36 2022/12/25
     * @Description 分发课程参数校验
     **/
    private void validAssignCourseMethod(UserCourseParamDTO userCourseParamDTO) {
        if (CommonUtils.IsNull(userCourseParamDTO)) {
            throw new RuntimeException("参数不能为空");
        }
        if (CommonUtils.IsNull(userCourseParamDTO.getCourseIdList())) {
            throw new RuntimeException("未选择要分发的课程");
        }
        if (CommonUtils.IsNull(userCourseParamDTO.getCommonUserinfoIdList())) {
            throw new RuntimeException("未选择要分发的用户");
        }
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
        Optional.ofNullable(commonUserQueryParamDTO.getCommonUserinfoIdList())
                .ifPresent(commonUserinfoIdList -> queryWrapper.in("common_userinfo_id", commonUserinfoIdList));
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
        if (CommonUtils.IsNotNull(commonUserQueryParamDTO.getCommonUserinfoAuth())) {
            queryWrapper.eq("common_userinfo_auth", commonUserQueryParamDTO.getCommonUserinfoAuth());
        }

        if (CommonUtils.IsNotNull(commonUserQueryParamDTO.getUserinfoRegistertime())) {
            String endDate;
            String startDate;
            List<String> registerTimeList = commonUserQueryParamDTO.getUserinfoRegistertime();
            if (CommonUtils.IsNull(registerTimeList.get(0))) {
                throw new RuntimeException("请填写开始日期");
            }
            if (CommonUtils.IsNull(registerTimeList.get(1))) {
                throw new RuntimeException("请填写终止日期");
            }
            startDate = commonUserQueryParamDTO.getUserinfoRegistertime().get(0);
            endDate = commonUserQueryParamDTO.getUserinfoRegistertime().get(1);
            queryWrapper.apply("date_format(createtime,'%Y-%m-%d') between {0} and {1}", startDate, endDate);
        }
        return queryWrapper;
    }

    /**
     * @author 何欢
     * @Date 11:48 2022/10/2
     * @Description 查询保存参数校验
     **/
    private void validSaveOrUpdateParam(CommonUserParamDTO commonUserParamDTO){
        if(CommonUtils.IsNull(commonUserParamDTO)){
            throw new RuntimeException("参数不能为空");
        }
    }
}
