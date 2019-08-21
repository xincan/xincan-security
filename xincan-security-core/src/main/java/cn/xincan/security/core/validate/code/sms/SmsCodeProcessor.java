package cn.xincan.security.core.validate.code.sms;

import cn.xincan.security.core.properties.SecurityConstants;
import cn.xincan.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 自定义图片校验码生成器
 * @className: SmsCodeGenerator
 * @date: 2019/8/5 19:13
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {

    /**
     * @description: 注入短信接口
     * @author: Xincan Jiang
     * @date: 2019-08-21 18:14:24
     */
    @Autowired
    private SmsCodeSenderService smsCodeSenderService;

    /**
     * @description: 发送短信校验码
     * @method: generate
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:15:08
     * @param: [request: 请求]
     * @return: cn.xincan.security.core.validate.code.image.ImageCode
     * @exception:
     */
    @Override
    public void send(ServletWebRequest request, SmsCode smsCode) throws ServletRequestBindingException {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        this.smsCodeSenderService.send(mobile, smsCode.getCode());
    }

}
