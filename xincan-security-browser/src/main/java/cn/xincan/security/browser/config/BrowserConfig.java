package cn.xincan.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
public class BrowserConfig  {

    /**
     * 配置当前请求缓存中的路径处理类，用于获取之前session中的请求路径
     * @return
     */
    @Bean
    public RequestCache requestCache(){
        return new HttpSessionRequestCache();
    }


    /**
     * 配置路径跳转工具类
     * @return
     */
    @Bean
    public RedirectStrategy redirectStrategy(){
        return new DefaultRedirectStrategy();
    }

}
