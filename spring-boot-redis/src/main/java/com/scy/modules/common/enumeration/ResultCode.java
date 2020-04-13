package com.scy.modules.common.enumeration;

import io.swagger.annotations.ApiModel;

/**
 * 类名： ResultCode <br>
 * 描述：TODO <br>
 * 创建日期： 2019/11/29 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@ApiModel(description = "结果枚举")
public enum ResultCode {
    //成功
    SUCCESS(200, "SUCCESS"),
    //失败
    FAILURE(400, "FAILURE"),
    // 未登录
    UN_LOGIN(401, "未登录"),
    //未认证（签名错误、token错误）
    UNAUTHORIZED(403, "未认证或Token失效"),
    //未通过认证
    USER_UNAUTHORIZED(402, "用户名或密码不正确"),
    //接口不存在
    NOT_FOUND(404, "接口不存在"),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private Integer code;
    private String desc;

    ResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultCode of(Integer code) {
        for (ResultCode result : values()) {
            if (code.equals(result.getCode())) {
                return result;
            }
        }
        return null;
    }
}
