package com.lingnan.sas.core.vo;

import lombok.Data;

@Data
public class Response {
    private String msg;
    private String code;
    private boolean succeed;
    private Object data;

    /**
     * 请求成功
     */
    public static Response success(Object data) {
        Response response = new Response();
        response.setMsg("请求成功!");
        response.setSucceed(true);
        response.setCode("0");
        response.setData(data);
        return response;
    }

    /**
     * 请求失败
     */
    public static Response error(String msg) {
        Response response = new Response();
        response.setMsg(msg);
        response.setSucceed(false);
        response.setCode("1");
        return response;
    }

}
