package com.echobuf.servicepassengeruser.request;

import lombok.Data;

/**
 * @USER: echobuf
 * @Date: 2022/12/2 - 12 - 02 - 00:28
 * @Description: online-taxi
 */
@Data
public class VerificationCodeDTO {

    private String passengerPhone;
    private String verificationCode;
}
