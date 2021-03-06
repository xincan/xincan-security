package cn.xincan.security.core.support;

/**
 * @description: 结果状态处理枚举类
 * @className: ResultCode
 * @date: 2019-07-23 18:50:22
 * @author: Xincan Jiang
 * @version: 1.0
 */
public enum  ResultCode {

    // 成功
    SUCCESS(200),

    // 失败
    FAIL(500),

    // 未认证（签名错误）
    UNAUTHORIZED(401),

    // 接口不存在
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(501);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
