package com.lingnan.sas.client.Exception;

import lombok.Data;

/**
 * @author 19399.
 * @date 2021/3/24.
 * @time 1:54
 */
@Data
public class ClientException extends RuntimeException {
    private int code = 500;
    private String msg;

    public ClientException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ClientException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ClientException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ClientException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}
