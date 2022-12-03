package com.echobuf.apipassenger.request;

/**
 * @USER: echobuf
 * @Date: 2022/12/2 - 12 - 02 - 00:28
 * @Description: online-taxi
 */
public class VerificationCodeDTO {

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    private String passengerPhone;
}
