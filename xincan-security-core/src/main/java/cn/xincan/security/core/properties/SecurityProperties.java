package cn.xincan.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: 安全配置管理类
 * @className: SecurityProperties
 * @date: 2019/7/24 11:06
 * @author: Xincan Jiang
 * @version: 1.0
 */

@ConfigurationProperties( prefix = "xincan.security")
public class SecurityProperties {

    BrowserProperties browser = new BrowserProperties();

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }
}
