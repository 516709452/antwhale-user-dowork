package org.antwhale.dto.userinfodto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/8/2218:57
 * @Description: 微分销用户信息查询入参
 */
@Data
public class WeifxUserQueryParamDTO{

    @ApiModelProperty(value = "微分销用户id")
    private Long weifxUserinfoId;

    @ApiModelProperty(value = "微分销用户(真实姓名)")
    private String weifxUserinfoName;

    @ApiModelProperty(value = "微分销用户手机号码")
    private String weifxUserinfoPhone;

    @ApiModelProperty(value = "微分销用户微信名称")
    private String weifxUserinfoWxname;

    @ApiModelProperty(value = "微分销用户等级")
    private String weifxUserinfoLevel;

    @ApiModelProperty(value = "微分销用户注册时间")
    private List<String> weifxUserinfoRegistertime;

    @ApiModelProperty(value = "微分销用户微信OpenID")
    private String weifxUserinfoWxopenid;

    @ApiModelProperty(value = "微分销用户UnionID")
    private String weifxUserinfoWxunionid;

    @ApiModelProperty(value = "微分销用户地址信息")
    private String weifxUserinfoAddress;

    @ApiModelProperty(value = "鲸战队用户id")
    private Long commonUserinfoId;

    @ApiModelProperty(value = "数据有效标识")
    private String commonFlag;

    @ApiModelProperty(value = "当前页码")
    private Long currentPage;

    @ApiModelProperty(value = "分页大小")
    private Long pageSize;

}
