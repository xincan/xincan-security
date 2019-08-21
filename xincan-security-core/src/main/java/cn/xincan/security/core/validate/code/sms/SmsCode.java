package cn.xincan.security.core.validate.code.sms;

import cn.xincan.security.core.validate.code.ValidateCode;

import java.time.LocalDateTime;

/**
 * @description: 添加短信验证码类
 * @className: SmsCode
 * @date: 2019/8/3 20:24
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class SmsCode extends ValidateCode {

    private static final long serialVersionUID = -2037196914732043185L;

    public SmsCode(String code, int expireIn){
        super(code, expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime){
        super(code, expireTime);
    }
}
