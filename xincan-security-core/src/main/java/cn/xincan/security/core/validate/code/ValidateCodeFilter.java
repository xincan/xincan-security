package cn.xincan.security.core.validate.code;

import cn.xincan.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 继承spring提供过滤器类，保证每次启动只会调用一次
 * @className: ValidateCodeFilter
 * @date: 2019/8/3 21:16
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {


    /**
     * @description: 注入鉴权失败接口
     * @author: Xincan Jiang
     * @date: 2019-08-05 16:55:25
     */
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * @description: 注入session会话
     * @author: Xincan Jiang
     * @date: 2019-08-05 16:55:25
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * @description: 注入统一鉴权配置信息
     * @author: Xincan Jiang
     * @date: 2019-08-05 16:55:25
     */
    private SecurityProperties securityProperties;

    /**
     * @description: 注入spring匹配路径请求类
     * @author: Xincan Jiang
     * @date: 2019-08-05 16:55:25
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * @description: 定义配置请求的url，做校验过滤
     * @author: Xincan Jiang
     * @date: 2019-08-05 16:55:25
     */
    private Set<String> urls = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if (!StringUtils.isBlank(this.securityProperties.getCode().getImage().getUrl())) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(this.securityProperties.getCode().getImage().getUrl(), ",");
            for (String config : configUrls) {
                urls.add(config);
            }
        }
        urls.add("/authentication/form");
    }

    /**
     * @description: 登录请求触发该过滤器，否则不触发
     * @method: doFilterInternal
     * @author: Xincan Jiang
     * @date: 2019-08-03 21:26:14
     * @param: [request: 请求, response: 相应, chain: 是否放行对象]
     * @return:
     * @exception: [ServletException, IOException]
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean action = false;
        // 循环匹配url，如果当前访问地址包含配置定义的路径则需要进行验证码验证，否则直接放行
        for (String url : urls) {
            if (this.pathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }
        if (action) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException exception) {
                this.authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * @description: 校验验证码是否正确
     * @method: validate
     * @author: Xincan Jiang
     * @date: 2019-08-05 09:38:17
     * @param: [request: 请求]
     * @return: void
     * @exception:
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException  {

        ImageCode codeInSession = (ImageCode) this.sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            this.sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已经过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        this.sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}
