package com.echobuf.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @USER: echobuf
 * @Date: 2022/12/7 - 12 - 07 - 05:03
 * @Description: online-taxi
 */
@Data
public class PassengerUser {

    private long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;

    private String profilePhoto;

}
