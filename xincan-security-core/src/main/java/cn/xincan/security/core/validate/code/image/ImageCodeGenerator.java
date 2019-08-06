package cn.xincan.security.core.validate.code.image;

import cn.xincan.security.core.properties.SecurityProperties;
import cn.xincan.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @description: 自定义图片校验码生成器
 * @className: ImageCodeGenerator
 * @date: 2019/8/5 19:13
 * @author: Xincan Jiang
 * @version: 1.0
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    /**
     * @description: 注入统一认证配置信息类
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:36:44
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @description: 图形校验码生成器函数
     * @method: gencrate
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:15:08
     * @param: [request: 请求]
     * @return: cn.xincan.security.core.validate.code.image.ImageCode
     * @exception:
     */
    @Override
    public ImageCode generator(ServletWebRequest request) {

        // 获取请求中得图形验证码得宽高，如果没有走默认配置
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", this.securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", this.securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        // this.securityProperties.getCode().getImage().getLength()获取配置中图形验证码得默认长度
        for (int i = 0; i < this.securityProperties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();
        // this.securityProperties.getCode().getImage().getExpireIn() 获取配置中图形验证码的默认过期时间
        return new ImageCode(image, sRand, this.securityProperties.getCode().getImage().getExpireIn());
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
