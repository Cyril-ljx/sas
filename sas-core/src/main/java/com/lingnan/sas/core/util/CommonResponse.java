package com.lingnan.sas.core.util;

import lombok.Data;

@Data
public class CommonResponse {
    private String msg;
    private String code;
    private boolean succeed;
    private Object data;

    /**
     * 请求成功
     */
    public static CommonResponse success(Object data) {
        CommonResponse CommonResponse = new CommonResponse();
        CommonResponse.setMsg("请求成功!");
        CommonResponse.setSucceed(true);
        CommonResponse.setCode("0");
        CommonResponse.setData(data);
        return CommonResponse;
    }

    /**
     * 请求失败
     */
    public static CommonResponse error(String msg) {
        CommonResponse CommonResponse = new CommonResponse();
        CommonResponse.setMsg(msg);
        CommonResponse.setSucceed(false);
        CommonResponse.setCode("1");
        return CommonResponse;
    }
}
