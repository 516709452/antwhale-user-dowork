package org.antwhale.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 何欢
 * @Date: 2022/8/1721:38
 * @Description:
 */
@Data
@TableName("weifx_userinfo")
public class WeifxUserInfo {
    @TableId(value = "weifx_userinfo_id")
    @ApiModelProperty(value = "微分销用户id")
    private String weifxUserinfoId;

    @TableField("weifx_userinfo_name")
    @ApiModelProperty(value = "微分销用户(真实姓名)")
    private String weifxUserinfoName;

    @TableField("weifx_userinfo_phone")
    @ApiModelProperty(value = "微分销用户手机号码")
    private String weifxUserinfoPhone;

    @TableField("weifx_userinfo_wxname")
    @ApiModelProperty(value = "微分销用户微信名称")
    private String weifxUserinfoWxname;

    @TableField("weifx_userinfo_level")
    @ApiModelProperty(value = "微分销用户等级")
    private String weifxUserinfoLevel;

    @TableField("weifx_userinfo_registertime")
    @ApiModelProperty(value = "微分销用户注册时间")
    private String weifxUserinfoRegistertime;

    @TableField("weifx_userinfo_wxopenid")
    @ApiModelProperty(value = "微分销用户微信OpenID")
    private String weifxUserinfoWxopenid;

    @TableField("weifx_userinfo_wxunionid")
    @ApiModelProperty(value = "微分销用户UnionID")
    private String weifxUserinfoWxunionid;

    @TableField("weifx_userinfo_address")
    @ApiModelProperty(value = "微分销用户地址信息")
    private String weifxUserinfoAddress;

    @TableField("common_userinfo_id")
    @ApiModelProperty(value = "鲸战队用户id")
    private String commonUserinfoId;

    @TableField("validflag")
    @ApiModelProperty(value = "数据有效标识")
    private String validflag;

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "数据新增时间")
    private String createtime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "数据修改时间")
    private String updatetime;
}
