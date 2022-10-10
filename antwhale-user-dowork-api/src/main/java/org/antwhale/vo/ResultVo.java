package org.antwhale.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 何欢
 * @Date: 2022/7/21 12:06
 * @Description: 全局统一返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "全局统一返回结果")
public class ResultVo<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public static <T> ResultVo<T> build(T body, Integer code, String message) {
        ResultVo<T> result = new ResultVo<T>();
        if (body != null) {
            result.setData(body);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> ResultVo<T> ok() {
        return ResultVo.ok(null);
    }

    /**
     * 操作成功
     *
     * @param data baseCategory1List
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> ok(T data) {
        return build(data, 200, "成功");
    }

    public static <T> ResultVo<T> ok(T data,String successMsg) {
        return build(data, 200, successMsg);
    }

    public static <T> ResultVo<T> fail() {
        return ResultVo.fail(null);
    }

    /**
     * 操作失败
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> fail(T data) {
        return build(data, 201, "失败");
    }

    public static <T> ResultVo<T> fail(T data,String errMsg) {
        return build(data, 201, errMsg);
    }

    public ResultVo<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public ResultVo<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
