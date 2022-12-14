package com.echobuf.internalcommon.dto;

import com.echobuf.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @USER: echobuf
 * @Date: 2022/12/6 - 12 - 06 - 00:18
 * @Description: online-taxi
 */

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    /**
     * 默认成功响应
     * @return
     */
    public static<T> ResponseResult success(){
        return  new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue());
    }
    /**
     * 成功响应
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ResponseResult success(T data){
        return  new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }

    /**
     * 失败：统一的失败
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }

    /**
     * 失败：自定义失败 错误码和提示信息
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 失败：自定义失败 错误码、提示信息、具体错误
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code,String message,String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
