package com.echobuf.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @USER: echobuf
 * @Description: online-taxi
 */
public enum CommonStatusEnum {
    /**
     * 验证码错误(1000-1099为错误码)
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
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
