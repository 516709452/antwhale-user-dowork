package org.antwhale.blo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.CommonUserInfoBLO;
import org.antwhale.entity.CommonUserInfo;
import org.antwhale.mapper.CommonUserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/9/2619:37
 * @Description:
 */
@Service
public class CommonUserInfoBLOImpl extends ServiceImpl<CommonUserInfoMapper, CommonUserInfo> implements CommonUserInfoBLO {
}
