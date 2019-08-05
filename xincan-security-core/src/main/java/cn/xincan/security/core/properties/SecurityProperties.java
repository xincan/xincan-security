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

    // 客户端登录参数配置
    private BrowserProperties browser = new BrowserProperties();

    // 验证码参数配置
    private ValidateCodeProperties code = new ValidateCodeProperties();

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
