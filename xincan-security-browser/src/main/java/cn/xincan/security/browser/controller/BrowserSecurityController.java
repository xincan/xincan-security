package cn.xincan.security.browser.controller;

import cn.xincan.security.browser.support.ResultCode;
import cn.xincan.security.browser.support.ResultObject;
import cn.xincan.security.browser.support.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 处理身份认证请求控制类
 * @ClassName: BrowserSecurityController
 * @Date: 2019-07-2323 18:50:34
 * @Author Xincan Jiang
 * @Version 1.0
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    @Autowired
    private RequestCache requestCache;

    @Autowired
    private RedirectStrategy redirectStrategy;

    /**
     * @Description 需要身份认证时，进入此方法
     * @Method requireAuthention
     * @Author Xincan Jiang
     * @Date 2019-07-23 1824:56:46
     * @Param * [request 请求信息, response 转发信息]
     * @Return ResultObject
     * @Exception IOException
     */
    @GetMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultObject requireAuthention(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取缓存中访问的路径信息
        SavedRequest savedRequest = this.requestCache.getRequest(request,response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}", targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
                this.redirectStrategy.sendRedirect(request, response, "");
            }
        }
        return ResultResponse.error(ResultCode.UNAUTHORIZED, "访问的服务需要身份认证请引导用户到登录页", null);
    }


}
