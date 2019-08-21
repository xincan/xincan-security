package cn.xincan.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @description: 提供短信校验逻辑类
 * @className: SmsCodeAuthenticationProvider
 * @date: 2019/8/21 14:59
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @description: 进行身份认证逻辑处理
     * @method: authenticate
     * @author: Xincan Jiang
     * @date: 2019-08-21 15:01:17
     * @param: [authentication]
     * @return: org.springframework.security.core.Authentication
     * @exception: AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = this.userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if(userDetails == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationTokenResult.setDetails(authentication.getDetails());
        return authenticationTokenResult;
    }

    /**
     * @description: 判断认证类型是否是短信登录类型
     * @method: supports
     * @author: Xincan Jiang
     * @date: 2019-08-21 15:01:47
     * @param: [aClass]
     * @return: boolean
     */
    @Override
    public boolean supports(Class<?> authenticatioin) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authenticatioin);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
