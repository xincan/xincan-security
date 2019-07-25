package cn.xincan.security.browser.authentication;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @className: BrowserAuthenticationSuccessHander
 * @date: 2019/7/24 18:37
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Slf4j
@Component("browserAuthenticationSuccessHandler")
public class BrowserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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
        response.setContentType("application/json;charset=UTF-8");
        JSONObject result = JSONObject.parseObject(JSONObject.toJSONString(authentication));
        System.out.println(result);
        response.getWriter().write(JSONObject.toJSONString(authentication));
    }
}
