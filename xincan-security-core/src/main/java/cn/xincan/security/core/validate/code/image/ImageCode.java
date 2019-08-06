package cn.xincan.security.core.validate.code.image;

import cn.xincan.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @description: 添加图形验证码类
 * @className: ImageCode
 * @date: 2019/8/3 20:24
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ImageCode extends ValidateCode {


    private static final long serialVersionUID = -1271901550562149814L;

    // 图片
    private BufferedImage image;

    public ImageCode() {}

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
