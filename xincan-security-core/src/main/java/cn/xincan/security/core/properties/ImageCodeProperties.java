package cn.xincan.security.core.properties;

/**
 * @description: 图片验证器参数配置类
 * @className: ImageCodeProperties
 * @date: 2019/8/5 16:21
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ImageCodeProperties {

    // 图形验证码图片宽度
    private int width = 60;

    // 图形验证码图片高度
    private int height = 24;

    // 图形验证码长度
    private int length = 4;

    // 图形验证码过期时间（秒）
    private int expireIn = 60;

    private String url;

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
