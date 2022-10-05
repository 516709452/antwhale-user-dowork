package org.antwhale.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: 何欢
 * @Date: 2022/9/2618:52
 * @Description:
 */
@Data
@TableName("common_userinfo")
public class CommonUserInfo {
    @TableId(value = "common_userinfo_id",type = IdType.AUTO)
    @ApiModelProperty(value = "公共信息表_用户id")
    private Long commonUserinfoId;

    @TableField(value = "common_userinfo_account")
    @ApiModelProperty(value = "公共信息表_用户登录账号")
    private String commonUserinfoAccount;

    @TableField(value = "common_userinfo_password")
    @ApiModelProperty(value = "公共信息表_用户登录密码")
    private String commonUserinfoPassword;

    @TableField(value = "common_userinfo_headimg")
    @ApiModelProperty(value = "公共信息表_用户头像")
    private String commonUserinfoHeadImg;

    @TableField(value = "common_userinfo_username")
    @ApiModelProperty(value = "公共信息表_用户姓名")
    private String commonUserinfoUsername;

    @TableField(value = "common_userinfo_sex")
    @ApiModelProperty(value = "公共信息表_用户性别")
    private String commonUserinfoSex;

    @TableField(value = "common_userinfo_address")
    @ApiModelProperty(value = "公共信息表_地址")
    private String commonUserinfoAddress;

    @TableField(value = "common_userinfo_phone")
    @ApiModelProperty(value = "公共信息表_用户电话")
    private String commonUserinfoPhone;

    @TableField(value = "common_userinfo_auth")
    @ApiModelProperty(value = "公共信息表_用户权限")
    private String commonUserinfoAuth;

    @TableField(value = "common_userinfo_introduction")
    @ApiModelProperty(value = "公共信息表_用户简介")
    private String commonUserinfoIntroduction;

    @TableField(value = "weifx_userinfo_id")
    @ApiModelProperty(value = "微分销用户id")
    private Long weifxUserinfoId;

    @TableField("validflag")
    @ApiModelProperty(value = "数据有效标识")
    private String validflag;

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "数据新增时间")
    private String createtime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "数据修改时间")
    private String updatetime;

    @TableField("operationid")
    @ApiModelProperty(value = "业务操作序号")
    private String operationid;
}
