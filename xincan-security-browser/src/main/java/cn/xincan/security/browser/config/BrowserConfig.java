package cn.xincan.security.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@Configuration
public class BrowserConfig  {

    /**
     *
     * @return
     */
    @Bean
    public RequestCache requestCache(){
        return new HttpSessionRequestCache();
    }

}
