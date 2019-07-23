package cn.xincan.security.browser.support;


/**
 * Copyright (C), 2018,
 * FileName: ResultResponse
 * Author:   Xincan Jiang
 * Date:     2018-12-19 16:34:00
 * Description: 将结果转换为封装对象实体类
 */
public class ResultResponse {

    private final static String SUCCESS_MSG = "请求成功";

    private final static String FAIL_MSG = "请求失败";

    /**
     * 返回处理结果
     *
     * 针对于返回业务处理之后，无需通知前端具体处理信息，走系统默认的提示信息
     *
     * @param <T> 响应数据类型
     * @return ResultObject<T>
     */
    public static <T> ResultObject<T> success() {
        return new ResultObject<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(SUCCESS_MSG);
    }

    /**
     * 返回处理结果
     *
     * 针对于返回业务处理之后，需要向前端反馈后台处理的数据，将其返回
     *
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return ResultObject<T>
     */
    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(SUCCESS_MSG)
                .setCount(1)
                .setData(data);
    }

    /**
     * 返回处理结果
     *
     * 针对于返回业务处理之后，需要向前端反馈后台处理的数据，并且将处理结果描述提供给前端
     *
     * @param message 响应描述
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return ResultObject<T>
     */
    public static <T> ResultObject<T> success(String message, T data) {
        return new ResultObject<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(message)
                .setCount(1)
                .setData(data);
    }

    /**
     * 返回分页数据信息
     *
     *  token快要过期提醒
     *
     * @param msg       响应结果信息
     * @param data      查询分页数据
     * @param <T>       响应数据类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> ResultObject<T> success(ResultCode code,String msg, T data){
        return new ResultObject<T>()
                .setCode(code)
                .setMsg(msg)
                .setCount(0)
                .setData(data);
    }


    /*****************************************************************************/

    /**
     * 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默认返回失败处理信息
     *
     * @param <T> 响应数据类型
     * @return ResultObject<T>
     */
    public static <T> ResultObject<T> error() {
        return new ResultObject<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(FAIL_MSG)
                .setCount(0)
                .setData(null);
    }

    /**
     * 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默返回自定义失败处理信息
     *
     * @param message 响应描述
     * @param <T> 响应数据类型
     * @return ResultObject<T>
     */
    public static <T> ResultObject<T> error(String message) {
        return new ResultObject<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(message)
                .setCount(0)
                .setData(null);
    }

    /**
     * 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默返回自定义失败处理信息并返回传入修改的信息
     *
     * @param message 响应描述
     * @param <T> 响应数据类型
     * @return ResultObject<T>
     */
    public static <T> ResultObject<T> error(String message, T data) {
        return new ResultObject<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(message)
                .setCount(0)
                .setData(data);
    }

    /**
     * 返回处理结果
     *
     * @Description token过期传入code,或其他的错误传入code值
     *
     * @param code 响应结果编码, message 响应结果信息, T 响应结果数据
     * @param <T> 响应数据类型
     * @Return ResultObject<T>
     */
    public static <T> ResultObject<T> error(ResultCode code, String message, T data) {
        return new ResultObject<T>()
                .setCode(code)
                .setMsg(message)
                .setCount(0)
                .setData(data);
    }

}
