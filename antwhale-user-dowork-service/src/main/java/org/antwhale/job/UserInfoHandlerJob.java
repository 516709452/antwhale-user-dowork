package org.antwhale.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxl.job.core.biz.model.ReturnT;
import org.antwhale.blo.CommonUserInfoBLO;
import org.antwhale.blo.WeifxUserInfoBLO;
import org.antwhale.entity.CommonUserInfo;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.utils.CommonUtils;
import org.antwhale.utils.SimpleDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @Transactional
//    @Scheduled(cron = "30 * * * * ?")
    public ReturnT<String> execute() throws Exception {
        List<WeifxUserInfo> weifxUserInfoList = weifxUserInfoBLO.list(
                new QueryWrapper<WeifxUserInfo>().isNull("validflag")
        );
        if(CommonUtils.isNull(weifxUserInfoList)){
            return ReturnT.SUCCESS;
        }
        weifxUserInfoList.stream().forEach(weifxUserInfo -> weifxUserInfo.setValidflag("1"));
        weifxUserInfoBLO.updateBatchById(weifxUserInfoList);
        List<CommonUserInfo> commonUserInfoList = new ArrayList<>();
        weifxUserInfoList.stream().forEach(
                weifxUserInfo -> {
                    CommonUserInfo commonUserInfo =new CommonUserInfo();
                    commonUserInfo.setCommonUserinfoAccount(CommonUtils.getStringValue(weifxUserInfo.getWeifxUserinfoPhone()));
                    commonUserInfo.setCommonUserinfoPassword("123456");
                    commonUserInfo.setCommonUserinfoUsername("小鲸鱼"+ UUID.randomUUID().toString().substring(0,10));
                    commonUserInfo.setCommonUserinfoSex("1");
                    commonUserInfo.setCommonUserinfoPhone(CommonUtils.getStringValue(weifxUserInfo.getWeifxUserinfoPhone()));
                    commonUserInfo.setCommonUserinfoAuth("1");
                    LocalDateTime userinfoRegistertime = SimpleDateUtils.formatStringToLocalDateTime(weifxUserInfo.getWeifxUserinfoRegistertime());
                    commonUserInfo.setCreatetime(SimpleDateUtils.formatLocalDateTimeToString(userinfoRegistertime,"yyyyMMdd"));
                    commonUserInfo.setWeifxUserinfoId(CommonUtils.getLongValue(weifxUserInfo.getWeifxUserinfoId()));
                    commonUserInfo.setValidflag("1");
                    commonUserInfoList.add(commonUserInfo);
                }
        );
        commonUserInfoBLO.saveBatch(commonUserInfoList);
        return ReturnT.SUCCESS;
    }

}