package org.antwhale.controller;

import com.antwhale.framework.utils.CommonUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.WeifxUserInfoBPO;
import org.antwhale.dto.userinfodto.WeifxUserQueryParamDTO;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/8/1721:54
 * @Description: 用户操作控制器
 */
@Slf4j
@RestController
public class WeifxUserInfoController {
    @Autowired
    private WeifxUserInfoBPO weifxUserInfoBPO;

    /**
    *@author 何欢
    *@Date 18:55 2022/8/22
    *@Description 微分销用户信息导入功能
    **/
    @PostMapping("/userinfo/importWeifxUser")
    public ResultVo<List<WeifxUserInfo>> importWeifxUserController(MultipartFile file) throws IOException {
        List<WeifxUserInfo> weifxUserInfos = weifxUserInfoBPO.importWeifxUser(file);
        if(CommonUtils.IsNull(weifxUserInfos)){
           return ResultVo.fail(weifxUserInfos,"导入成功，但导入条数为空");
        }
        return ResultVo.ok(weifxUserInfos,"导入成功");
    }

    /**
    *@author 何欢
    *@Date 18:56 2022/8/22
    *@Description 查询微分销用户信息
    **/
    @PostMapping("/userinfo/queryWeifxUser")
    public ResultVo<IPage<WeifxUserInfo>> queryWeifxUser(@RequestBody WeifxUserQueryParamDTO weifxUserQueryParamDTO) throws Exception {
        IPage<WeifxUserInfo> weifxUserInfos = weifxUserInfoBPO.queryWeifxUser(weifxUserQueryParamDTO);
        return ResultVo.ok(weifxUserInfos,"查询微分销用户信息成功");
    }

}
