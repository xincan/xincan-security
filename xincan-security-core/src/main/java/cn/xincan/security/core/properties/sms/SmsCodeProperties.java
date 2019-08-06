package cn.xincan.security.core.properties.sms;

/**
 * @description: 短信验证器参数配置类
 * @className: SmsCodeProperties
 * @date: 2019/8/5 16:21
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class SmsCodeProperties {

    /**
     * @description: 验证码长度
     * @author: Xincan Jiang
     * @date: 2019-08-06 18:58:12
     */
    private int length = 6;

    /**
     * @description: 验证码过期时间（秒）
     * @author: Xincan Jiang
     * @date: 2019-08-06 18:58:12
     */
    private int expireIn = 60;

    /**
     * @description: 验证码要拦截的url，多个url用逗号隔开，ant pattern
     * @author: Xincan Jiang
     * @date: 2019-08-06 18:58:12
     */
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
