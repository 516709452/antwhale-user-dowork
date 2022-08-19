package org.antwhale.bpo;

import org.antwhale.dto.userinfodto.WeifxUserImportParamDTO;
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
public interface UserInfoBPO {
    List<WeifxUserInfo> importWeifxUser(MultipartFile file) throws IOException;
}
