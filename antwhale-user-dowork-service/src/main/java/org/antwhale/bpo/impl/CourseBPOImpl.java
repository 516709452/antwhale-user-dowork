package org.antwhale.bpo.impl;

import com.qcloud.cos.model.COSObjectSummary;
import org.antwhale.bpo.CourseBPO;
import org.antwhale.code.TencentCosAccountEnum;
import org.antwhale.service.TencentCOSHandler;
import org.antwhale.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/8/2920:22
 * @Description:
 */
@Service
public class CourseBPOImpl implements CourseBPO {
    @Autowired
    private TencentCOSHandler tencentCOSHandler;

    @Override
    public void queryCourse(String filePath) {
        if(CommonUtils.IsNull(filePath)){
            tencentCOSHandler.queryMediaInfo();
        }

    }
}
