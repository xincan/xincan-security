package cn.xincan.security.core.validate.code.sms;

/**
 * @description: 短信验证码接口类
 * @className: SmsCodeSenderService
 * @date: 2019/8/6 16:48
 * @author: Xincan Jiang
 * @version: 1.0
 */
public interface SmsCodeSenderService {

    /**
     * @description: 发送短信
     * @method: send
     * @author: Xincan Jiang
     * @date: 2019-08-21 18:13:42
     * @param: [mobile, code]
     */
    void send(String mobile, String code);

}
