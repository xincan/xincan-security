package cn.xincan.security.core.validate.code;

import cn.xincan.security.core.properties.SecurityProperties;
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


}
