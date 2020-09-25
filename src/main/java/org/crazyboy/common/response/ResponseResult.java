package org.crazyboy.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: kevin
 * @date: 2020/9/25
 * @time: 下午9:58
 * @description: server unified response body
 **/

@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseResult<T> success(int code, String msg) {
        return new ResponseResult<T>(code, msg);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
    }

    public static <T> ResponseResult<T> error(int code, String msg) {
        return new ResponseResult<>(code, msg);
    }

}
