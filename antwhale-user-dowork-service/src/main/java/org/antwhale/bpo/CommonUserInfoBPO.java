package org.antwhale.bpo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antwhale.dto.userinfodto.CommonUserParamDTO;
import org.antwhale.entity.CommonUserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 何欢
 * @Date: 2022/9/2619:39
 * @Description:
 */
public interface CommonUserInfoBPO {
    /**
     * @author 何欢
     * @Date 20:35 2022/9/26
     * @Description 用户公共信息查询
     **/
    Page<CommonUserInfo> queryCommonUser(CommonUserParamDTO commonUserQueryParamDTO);

    /**
    *@author 何欢
    *@Date 20:45 2022/9/28
    *@Description 头像上传
    **/
    String uploadAvatar(MultipartFile file);

    /**
    *@author 何欢
    *@Date 11:50 2022/10/2
    *@Description 新增用户
    **/
    String addUserInfo(CommonUserParamDTO commonUserParamDTO);
}
