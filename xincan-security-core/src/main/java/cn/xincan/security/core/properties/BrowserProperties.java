package cn.xincan.security.core.properties;

/**
 * @description: 客户端配置管理类
 * @className: BrowserProperties
 * @date: 2019/7/24 11:07
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class BrowserProperties {

    private String loginPage = "/browser-login.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
