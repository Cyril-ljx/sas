package com.lingnan.sas.core.util;

import lombok.Data;

import java.util.List;

@Data
public class LayuiVO<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public static LayuiVO success(Integer count, Object data) {
        LayuiVO LayuiVO = new LayuiVO();
        LayuiVO.setCode(0);
        LayuiVO.setMsg("请求成功");
        LayuiVO.setCount(count);
        LayuiVO.setData((List) data);
        return LayuiVO;
    }

    public static LayuiVO error() {
        LayuiVO LayuiVO = new LayuiVO();
        LayuiVO.setCode(1);
        LayuiVO.setMsg("请求失败");
        LayuiVO.setCount(0);
        LayuiVO.setData(null);
        return LayuiVO;
    }
}
