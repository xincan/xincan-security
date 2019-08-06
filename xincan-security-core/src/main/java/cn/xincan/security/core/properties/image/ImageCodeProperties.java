package cn.xincan.security.core.properties.image;

import cn.xincan.security.core.properties.sms.SmsCodeProperties;

/**
 * @description: 图片验证器参数配置类
 * @className: ImageCodeProperties
 * @date: 2019/8/5 16:21
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ImageCodeProperties extends SmsCodeProperties {

    // 图形验证码图片宽度
    private int width = 60;

    // 图形验证码图片高度
    private int height = 24;

    public ImageCodeProperties(){
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
