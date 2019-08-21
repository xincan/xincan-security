package cn.xincan.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @description: 组装短信登录配置类
 * @className: SmsCodeAuthenticationSecurityConfig
 * @date: 2019/8/21 15:15
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * @description: 注入登录成功处理器
     * @author: Xincan Jiang
     * @date: 2019-08-21 17:58:57
     */
    @Autowired
    private AuthenticationSuccessHandler browserAuthenticationSuccessHandler;

    /**
     * @description: 注入登录失败处理器
     * @author: Xincan Jiang
     * @date: 2019-08-21 17:58:57
     */
    @Autowired
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;

    /**
     * @description: 注入登录信息查询接口
     * @author: Xincan Jiang
     * @date: 2019-08-21 17:58:57
     */
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 组装短信登录拦截器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(this.browserAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(this.browserAuthenticationFailureHandler);
        // 组装登录信息提供者
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        // 加入鉴权
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
