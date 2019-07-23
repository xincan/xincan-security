package cn.xincan.security.browser.support;

/**
 * Copyright (C), 2018,
 * FileName: ResultObject
 * Author:   Xincan Jiang
 * Date:     2018-12-19 16:32:00
 * Description: 创建返回对象实体类
 */
public class ResultObject<T> {

    // 响应结果编码
    public Integer code;

    // 响应结果信息
    private String msg;

    // 统计列表总数
    private long count;

    // 响应结果数据
    private T data;

    public ResultObject<T> setCode(ResultCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ResultObject<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultObject<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultObject<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getCount() {
        return count;
    }

    public ResultObject<T> setCount(long count) {
        this.count = count;
        return this;
    }
}
