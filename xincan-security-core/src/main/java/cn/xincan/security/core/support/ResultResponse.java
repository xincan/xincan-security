package cn.xincan.security.core.support;


/**
 * @description: 结果响应处理类
 * @className: ResultResponse
 * @date: 2019-07-23 18:50:22
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ResultResponse {

    private final static String SUCCESS_MSG = "请求成功";

    private final static String FAIL_MSG = "请求失败";

    /**
     * @description: 返回处理结果
     *
     * 针对于返回业务处理之后，无需通知前端具体处理信息，走系统默认的提示信息
     *
     * @method: success
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:44:04
     * @param: []
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> success() {
        return new ResultObject<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(SUCCESS_MSG);
    }

    /**
     * @description: 返回处理结果
     *
     * 针对于返回业务处理之后，需要向前端反馈后台处理的数据，将其返回
     *
     * @method: success
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:44:04
     * @param: data: 响应数据
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(SUCCESS_MSG)
                .setCount(1)
                .setData(data);
    }

    /**
     * @description: 返回处理结果
     *
     * 针对于返回业务处理之后，需要向前端反馈后台处理的数据，并且将处理结果描述提供给前端
     *
     * @method: success
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:44:04
     * @param: [message: 响应描述, data: 响应数据]
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> success(String message, T data) {
        return new ResultObject<T>()
                .setCode(ResultCode.SUCCESS)
                .setMsg(message)
                .setCount(1)
                .setData(data);
    }

    /**
     * @description: 返回分页数据信息
     * @method: success
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:44:04
     * @param: [code: 结果状态码, message: 响应描述, data: 响应数据]
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> success(ResultCode code, String message, T data){
        return new ResultObject<T>()
                .setCode(code)
                .setMsg(message)
                .setCount(0)
                .setData(data);
    }


    /**
     * @description: 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默认返回失败处理信息
     *
     * @method: error
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:47:32
     * @param: []
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> error() {
        return new ResultObject<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(FAIL_MSG)
                .setCount(0)
                .setData(null);
    }

    /**
     * @description: 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默返回自定义失败处理信息
     *
     * @method: error
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:47:32
     * @param: message: 响应描述
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> error(String message) {
        return new ResultObject<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(message)
                .setCount(0)
                .setData(null);
    }

    /**
     * @description: 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默返回自定义失败处理信息并返回传入修改的信息
     *
     * @method: error
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:47:32
     * @param: [message: 响应描述, data: 响应数据]
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> error(String message, T data) {
        return new ResultObject<T>()
                .setCode(ResultCode.FAIL)
                .setMsg(message)
                .setCount(0)
                .setData(data);
    }

    /**
     * @description: 返回处理结果
     *
     * 针对于系统业务处理失败之后，系统默返回自定义失败处理信息并返回传入修改的信息
     *
     * @method: error
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:47:32
     * @param: [code: 结果状态码, message: 响应描述, data: 响应数据]
     * @return: cn.xincan.security.core.support.ResultObject<T>
     */
    public static <T> ResultObject<T> error(ResultCode code, String message, T data) {
        return new ResultObject<T>()
                .setCode(code)
                .setMsg(message)
                .setCount(0)
                .setData(data);
    }

}
