package com.echobuf.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
public enum CommonStatusEnum {
    /**
     * 验证码错误(1000-1099)
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    /**
     * token错误（1100-1199）
     */
    TOKEN_ERROR(1199,"token错误"),
    /**
     * user错误（1200-1299）
     */
    USER_ERROR(1299,"user not exist"),
    /**
     * 成功
     */
    SUCCESS(1, "success"),
    /**
     * 失败
     */
    FAIL(0, "fail");

    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
