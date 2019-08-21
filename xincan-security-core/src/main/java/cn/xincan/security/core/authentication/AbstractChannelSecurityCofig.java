package cn.xincan.security.core.authentication;

import cn.xincan.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @description: 定义浏览器端校验成功失败配置器
 * @className: AbstractChannelSecurityCofig
 * @date: 2019/8/21 16:58
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class AbstractChannelSecurityCofig extends WebSecurityConfigurerAdapter {

    /**
     * @description: 注入自定义实现登录成功处理器
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:25:26
     */
    @Autowired
    protected AuthenticationSuccessHandler browserAuthenticationSuccessHandler;

    /**
     * @description: 注入自定义实现登录失败处理器
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:25:26
     */
    @Autowired
    protected AuthenticationFailureHandler browserAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(this.browserAuthenticationSuccessHandler)
                .failureHandler(this.browserAuthenticationFailureHandler);
    }

}
