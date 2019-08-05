package cn.xincan.security.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 继承spring提供过滤器类，保证每次启动只会调用一次
 * @className: ValidateCodeFilter
 * @date: 2019/8/3 21:16
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ValidateCodeFilter extends OncePerRequestFilter {


    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

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
        if (StringUtils.equals("/authentication/form", request.getRequestURI())
            && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {

            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException exception) {
                this.authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }

        } else {
            chain.doFilter(request, response);
        }
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

        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
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
}
