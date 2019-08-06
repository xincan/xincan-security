package cn.xincan.security.browser.authentication;

import cn.xincan.security.core.enums.LoginType;
import cn.xincan.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 登录成功配置处理类
 *
 * 实现AuthenticationSuccessHandler接口中的onAuthenticationSuccess方法
 *
 * @className: BrowserAuthenticationSuccessHandler
 * @date: 2019/7/24 18:37
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Slf4j
@Component("browserAuthenticationSuccessHandler")
public class BrowserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * @description: 注入对象转换器
     * @author: Xincan Jiang
     * @date: 2019-07-25 16:54:57
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @description: 注入自定义安全配置对象实例化
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:32:13
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @description: 登录成功被调用
     * @method: onAuthenticationSuccess
     * @author: Xincan Jiang
     * @date: 2019-07-24 18:51:02
     * @param: [request 请求, response 响应, authentication 封装认证信息（ip，session等等）]
     * @return: void
     * @exception: IOException, ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        // 如果用户配置了登录返回类型是JSON，则返回JSON，否则走父类默认返回跳转界面
        if(LoginType.JSON.equals(this.securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(this.objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
