package com.echobuf.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @USER: echobuf
 * @Date: 2022/12/5 - 12 - 05 - 23:19
 * @Description: online-taxi
 */
//@Data
public enum CommonStatusEnum {

    SUCCESS(1, "success"),
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
