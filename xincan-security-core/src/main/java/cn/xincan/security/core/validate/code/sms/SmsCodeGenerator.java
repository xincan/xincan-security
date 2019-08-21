package cn.xincan.security.core.validate.code.sms;

import cn.xincan.security.core.properties.SecurityProperties;
import cn.xincan.security.core.validate.code.ValidateCode;
import cn.xincan.security.core.validate.code.ValidateCodeGenerator;
import cn.xincan.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @description: 自定义图片校验码生成器
 * @className: SmsCodeGenerator
 * @date: 2019/8/5 19:13
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    /**
     * @description: 注入统一认证配置信息类
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:36:44
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @description: 图形校验码生成器函数
     * @method: generate
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:15:08
     * @param: [request: 请求]
     * @return: cn.xincan.security.core.validate.code.image.ImageCode
     * @exception:
     */
    @Override
    public SmsCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(this.securityProperties.getCode().getSms().getLength());
        return new SmsCode(code, this.securityProperties.getCode().getSms().getExpireIn());
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
