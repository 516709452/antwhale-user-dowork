package org.antwhale.dto.userinfodto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/9/2620:31
 * @Description:公共用户信息查询入参
 */
@Data
public class CommonUserParamDTO {
    @ApiModelProperty(value = "公共信息表_用户id")
    private String commonUserinfoId;

    @ApiModelProperty(value = "公共信息表_用户id")
    private List<String> commonUserinfoIdList;

    @ApiModelProperty(value = "公共信息表_用户登录账号")
    private String commonUserinfoAccount;

    @ApiModelProperty(value = "公共信息表_用户头像")
    private String commonUserinfoHeadImg;

    @ApiModelProperty(value = "公共信息表_用户姓名")
    private String commonUserinfoUsername;

    @ApiModelProperty(value = "公共信息表_用户性别")
    private String commonUserinfoSex;

    @ApiModelProperty(value = "公共信息表_用户电话")
    private String commonUserinfoPhone;

    @ApiModelProperty(value = "公共信息表_用户权限")
    private String commonUserinfoAuth;

    @ApiModelProperty(value = "微分销用户id")
    private String weifxUserinfoId;

    @ApiModelProperty(value = "数据有效标识")
    private String validflag;

    @ApiModelProperty(value = "用户注册时间")
    private List<String> userinfoRegistertime;

    @ApiModelProperty(value = "用户简介")
    private String commonUserinfoIntroduction;

    @ApiModelProperty(value = "当前页码")
    private Long currentPage;

    @ApiModelProperty(value = "分页大小")
    private Long pageSize;
}

