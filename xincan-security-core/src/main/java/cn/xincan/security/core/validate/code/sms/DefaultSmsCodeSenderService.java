package cn.xincan.security.core.validate.code.sms;

/**
 * @description: 默认发送短信验证码实现
 * @className: DefaultSmsCodeSenderService
 * @date: 2019/8/6 16:50
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class DefaultSmsCodeSenderService implements SmsCodeSenderService {

    /**
     * @description: 发送验证码
     * @method: send
     * @author: Xincan Jiang
     * @date: 2019-08-06 16:50:54
     * @param: [mobile: 手机号, code: 手机验证码]
     * @return: void
     * @exception:
     */
    @Override
    public void send(String mobile, String code) {
        System.out.println("请配置真实的短信验证码发送器(SmsCodeSenderService)");
        System.out.println("向手机【" + mobile + "】用户发送短信验证码【" + code + "】");
    }
}
