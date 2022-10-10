package org.antwhale.dto.userinfodto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 何欢
 * @Date: 2022/10/1013:39
 * @Description:公共用户信息查询出参
 */
@Data
public class ApiCommonUserResultDTO {
    @ApiModelProperty(value = "公共信息表_用户id")
    private Long commonUserinfoId;

    @ApiModelProperty(value = "公共信息表_用户登录账号")
    private String commonUserinfoAccount;

    @ApiModelProperty(value = "公共信息表_用户登录密码")
    private String commonUserinfoPassword;

    @ApiModelProperty(value = "公共信息表_用户头像")
    private String commonUserinfoHeadImg;

    @ApiModelProperty(value = "公共信息表_用户姓名")
    private String commonUserinfoUsername;

    @ApiModelProperty(value = "公共信息表_用户性别")
    private String commonUserinfoSex;

    @ApiModelProperty(value = "公共信息表_地址")
    private String commonUserinfoAddress;

    @ApiModelProperty(value = "公共信息表_用户电话")
    private String commonUserinfoPhone;

    @ApiModelProperty(value = "公共信息表_用户权限")
    private String commonUserinfoAuth;

    @ApiModelProperty(value = "公共信息表_用户简介")
    private String commonUserinfoIntroduction;

    @ApiModelProperty(value = "微分销用户id")
    private Long weifxUserinfoId;

    @ApiModelProperty(value = "数据有效标识")
    private String validflag;

    @ApiModelProperty(value = "数据新增时间")
    private String createtime;

    @ApiModelProperty(value = "数据修改时间")
    private String updatetime;

    @ApiModelProperty(value = "业务操作序号")
    private String operationid;
}
