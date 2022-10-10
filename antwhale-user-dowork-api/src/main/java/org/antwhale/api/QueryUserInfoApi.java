package org.antwhale.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.antwhale.dto.userinfodto.ApiCommonUserParamDTO;
import org.antwhale.dto.userinfodto.ApiCommonUserResultDTO;
import org.antwhale.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: 何欢
 * @Date: 2022/10/421:44
 * @Description:
 */
@Api("用户信息查询接口")
public interface QueryUserInfoApi {
    @ApiOperation("用户基础信息查询 -> 默认第1页，10条数据")
    @PostMapping("/userinfo/queryCommonUser")
    ResultVo<Page<ApiCommonUserResultDTO>> queryCommonUser(@RequestBody ApiCommonUserParamDTO commonUserQueryParamDTO);
}
