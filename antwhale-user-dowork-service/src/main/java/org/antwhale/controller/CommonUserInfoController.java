package org.antwhale.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.CommonUserInfoBPO;
import org.antwhale.dto.userinfodto.CommonUserParamDTO;
import org.antwhale.entity.CommonUserInfo;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 何欢
 * @Date: 2022/9/2620:25
 * @Description:
 */
@Slf4j
@RestController
public class CommonUserInfoController{
    @Autowired
    private CommonUserInfoBPO commonUserInfoBPO;

    /**
    *@author 何欢
    *@Date 20:35 2022/9/26
    *@Description 用户基本信息查询
    **/
    @PostMapping("/userinfo/queryCommonUser")
    public ResultVo<Page<CommonUserInfo>> queryCommonUser(@RequestBody CommonUserParamDTO commonUserQueryParamDTO) {
        Page<CommonUserInfo> commonUserInfos = commonUserInfoBPO.queryCommonUser(commonUserQueryParamDTO);
        return ResultVo.ok(commonUserInfos,"查询用户信息成功");
    }

    /**
    *@author 何欢
    *@Date 20:43 2022/9/28
    *@Description 头像上传 - 每个用户每天只会留下一个头像
    **/
    @PostMapping("/userinfo/uploadAvatar")
    public ResultVo<String> uploadAvatar(MultipartFile file) {
        String avatarUrl = commonUserInfoBPO.uploadAvatar(file);
        return ResultVo.ok(avatarUrl,"头像上传成功");
    }

    /**
    *@author 何欢
    *@Date 11:48 2022/10/2
    *@Description 新增用户
    **/
    @PostMapping("/userinfo/addUserInfo")
    public ResultVo<String> addUserInfo(@RequestBody CommonUserParamDTO commonUserParamDTO) throws Exception {
        String result = commonUserInfoBPO.addUserInfo(commonUserParamDTO);
        return ResultVo.ok(result,"添加用户成功");
    }
}
