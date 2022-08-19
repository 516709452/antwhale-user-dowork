package org.antwhale.controller;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.UserInfoBPO;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.utils.CommonUtils;
import org.antwhale.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/8/1721:54
 * @Description:
 */
@Slf4j
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoBPO userInfoBPO;

    @PostMapping("/userinfo/importWeifxUser")
    public ResultVo<List<WeifxUserInfo>> importWeifxUserController(MultipartFile file) throws IOException {
        List<WeifxUserInfo> weifxUserInfos = userInfoBPO.importWeifxUser(file);
        if(CommonUtils.IsNull(weifxUserInfos)){
            ResultVo.fail(weifxUserInfos,"上传结果集为空");
        }
        return ResultVo.ok(weifxUserInfos,"上传成功");
    }

}
