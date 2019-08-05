package cn.xincan.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @description: 添加图形验证码类
 * @className: ImageCode
 * @date: 2019/8/3 20:24
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ImageCode {

    // 图片
    private BufferedImage image;

    // 图片上的随机数（存入session）
    private String code;

    // 验证码过期时间
    private LocalDateTime expireTime;

    public ImageCode(){}

    public ImageCode(BufferedImage image, String code, int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn); // 设置过期时间点
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
