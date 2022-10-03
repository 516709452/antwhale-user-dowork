package org.antwhale.bpo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.antwhale.dto.userinfodto.WeifxUserImportParamDTO;
import org.antwhale.dto.userinfodto.WeifxUserQueryParamDTO;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/8/1816:01
 * @Description:
 */
public interface WeifxUserInfoBPO {
    /**
     *@author 何欢
     *@Date 18:55 2022/8/22
     *@Description 微分销用户信息导入功能
     **/
    List<WeifxUserInfo> importWeifxUser(MultipartFile file) throws IOException;

    /**
     *@author 何欢
     *@Date 18:56 2022/8/22
     *@Description 查询微分销用户信息
     **/

    IPage<WeifxUserInfo> queryWeifxUser(WeifxUserQueryParamDTO weifxUserQueryParamDTO) throws Exception;
}
