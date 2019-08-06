package cn.xincan.security.core.properties;

import cn.xincan.security.core.properties.image.ImageCodeProperties;
import cn.xincan.security.core.properties.sms.SmsCodeProperties;

/**
 * @description: 验证码配置类
 * @className: ValidateCodeProperties
 * @date: 2019/8/5 16:24
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ValidateCodeProperties {

    //图片验证码配置类
    private ImageCodeProperties image = new ImageCodeProperties();

    // 短信验证码配置类
    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

}
