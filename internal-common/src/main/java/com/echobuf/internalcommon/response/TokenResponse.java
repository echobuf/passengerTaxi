package com.echobuf.internalcommon.response;

import lombok.Data;

/**
 * @USER: echobuf
 * @Date: 2022/12/6 - 12 - 06 - 23:29
 * @Description: online-taxi
 */
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
