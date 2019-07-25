package cn.xincan.security.core.properties;

import cn.xincan.security.core.enums.LoginType;

/**
 * @description: 客户端配置管理类
 * @className: BrowserProperties
 * @date: 2019/7/24 11:07
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class BrowserProperties {

    // 如果用户没有指定登录页，默认采用浏览器端登录页
    private String loginPage = "/browser-login.html";

    // 如果用户没有指定跳转返回类型，默认返回JSON数据
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }


}
