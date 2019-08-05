package cn.xincan.security.core.properties;

/**
 * @description: 验证码配置类
 * @className: ValidateCodeProperties
 * @date: 2019/8/5 16:24
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ValidateCodeProperties {

    // 注入配置验证码类
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
