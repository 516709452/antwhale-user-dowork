package org.antwhale.blo.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.WeifxUserInfoBLO;
import org.antwhale.dto.userinfodto.WeifxUserImportParamDTO;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.mapper.WeifxUserInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Author: 何欢
 * @Date: 2022/8/1816:02
 * @Description:
 */
@Service
public class  WeifxUserInfoBLOImpl extends ServiceImpl<WeifxUserInfoMapper, WeifxUserInfo> implements WeifxUserInfoBLO {

}
