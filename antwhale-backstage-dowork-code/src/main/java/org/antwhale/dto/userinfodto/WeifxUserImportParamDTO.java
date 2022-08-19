package org.antwhale.dto.userinfodto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: 何欢
 * @Date: 2022/8/1721:51
 * @Description:
 */
@Data
public class WeifxUserImportParamDTO {
    @ExcelProperty(index = 0)
    private Long weifxUserinfoId;

    @ExcelProperty("姓名")
    private String weifxUserinfoName;

    @ExcelProperty("手机号")
    private String weifxUserinfoPhone;

    @ExcelProperty("微信昵称")
    private String weifxUserinfoWxname;

    @ExcelProperty("会员等级")
    private String weifxUserinfoLevel;

    @ExcelProperty("注册时间")
    private String weifxUserinfoRegistertime;

    @ExcelProperty("微信OpenID")
    private String weifxUserinfoWxopenid;

    @ExcelProperty("UnionID")
    private String weifxUserinfoWxunionid;

    @ExcelProperty("用户地址信息")
    private String weifxUserinfoAddress;

    @ExcelProperty("鲸战队用户id")
    private Long commonUserinfoId;

    @ExcelProperty("数据有效标识")
    private String commonFlag;

    @ExcelProperty("数据新增时间")
    private String commonInserttime;

    @ExcelProperty("数据修改时间")
    private String commonUpdatetime;

    @ExcelProperty("业务操作序号")
    private String commonOperationid;
}
