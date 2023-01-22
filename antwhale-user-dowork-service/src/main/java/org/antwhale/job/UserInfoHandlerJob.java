package org.antwhale.job;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxl.job.core.biz.model.ReturnT;
import org.antwhale.blo.CommonUserInfoBLO;
import org.antwhale.blo.WeifxUserInfoBLO;
import org.antwhale.entity.CommonUserInfo;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.mapper.SequenceService;
import org.antwhale.utils.SimpleDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 何欢
 * @Date: 2022/9/2619:32
 * @Description:
 */
@Component
public class UserInfoHandlerJob {
    @Autowired
    private WeifxUserInfoBLO weifxUserInfoBLO;

    @Autowired
    private CommonUserInfoBLO commonUserInfoBLO;

    @Autowired
    private SequenceService sequenceService;
    @Transactional
//    @Scheduled(cron = "30 * * * * ?")
    public ReturnT<String> execute() throws Exception {
        Page<WeifxUserInfo> weifxUserInfoPage = weifxUserInfoBLO.page(new Page(1,300), new QueryWrapper<WeifxUserInfo>().isNull("validflag"));
        List<WeifxUserInfo> weifxUserInfoList = weifxUserInfoPage.getRecords();
        if(CommonUtils.IsNull(weifxUserInfoList)){
            return ReturnT.SUCCESS;
        }
        weifxUserInfoList.stream().forEach(weifxUserInfo -> weifxUserInfo.setValidflag("1"));
        weifxUserInfoBLO.updateBatchById(weifxUserInfoList);
        List<CommonUserInfo> commonUserInfoList = new ArrayList<>();
//        String userIdSeq = sequenceService.getUserIdSeq();
//        Integer userIdSeqInter = Integer.valueOf(userIdSeq.substring(0,userIdSeq.length()-1));
        weifxUserInfoList.forEach(
                weifxUserInfo -> {
                    //密码【BCryptPasswordEncoder加密】
                    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                    CommonUserInfo commonUserInfo =new CommonUserInfo();
                    commonUserInfo.setCommonUserinfoAccount(CommonUtils.getStringValue(weifxUserInfo.getWeifxUserinfoId()));
                    commonUserInfo.setCommonUserinfoPassword(bCryptPasswordEncoder.encode("123456"));
                    commonUserInfo.setCommonUserinfoUsername("鲸"+ UUID.randomUUID().toString().substring(0,10));
                    commonUserInfo.setCommonUserinfoSex("男");
                    commonUserInfo.setCommonUserinfoPhone(CommonUtils.getStringValue(weifxUserInfo.getWeifxUserinfoPhone()));
                    commonUserInfo.setCommonUserinfoAuth("user");
                    commonUserInfo.setWeifxUserinfoId(weifxUserInfo.getWeifxUserinfoId());
                    commonUserInfo.setValidflag("1");
                    commonUserInfoList.add(commonUserInfo);
                }
        );
        commonUserInfoBLO.saveBatch(commonUserInfoList);
        return ReturnT.SUCCESS;
    }

}
