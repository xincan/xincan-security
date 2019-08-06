package cn.xincan.security.core.validate.code;

import cn.xincan.security.core.properties.SecurityProperties;
import cn.xincan.security.core.validate.code.image.ImageCodeGenerator;
import cn.xincan.security.core.validate.code.sms.DefaultSmsCodeSenderService;
import cn.xincan.security.core.validate.code.sms.SmsCodeGenerator;
import cn.xincan.security.core.validate.code.sms.SmsCodeSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 图形验证码自动装配配置类
 * @className: ValidateCodeConfig
 * @date: 2019/8/5 19:22
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Configuration
public class ValidateCodeConfig {

    /**
     * @description: 注入统一鉴权配置参数类，为ImageCodeGenerator实现类提供配置项
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:28:21
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @description: 装配ImageCodeGenerator类
     *
     * 如果发现系统中有imageCodeGenerator装配类则，此bean就不会在装配，用现有的装配类，否则装配当前默认实现类
     *
     * @method: imageCodeGenerator
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:29:31
     * @return: cn.xincan.security.core.validate.code.ValidateCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(this.securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator() {
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(this.securityProperties);
        return smsCodeGenerator;
    }

    /**
     * @description: 装配SmsCodeSenderService类
     *
     * 如果发现系统中有SmsCodeSenderService.class装配类则，此bean就不会在装配，用现有的装配类，否则装配当前默认实现类
     *
     * @method: imageCodeGenerator
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:29:31
     * @return: cn.xincan.security.core.validate.code.ValidateCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSenderService.class)
    public SmsCodeSenderService smsCodeSenderService() {
        return new DefaultSmsCodeSenderService();
    }


}
