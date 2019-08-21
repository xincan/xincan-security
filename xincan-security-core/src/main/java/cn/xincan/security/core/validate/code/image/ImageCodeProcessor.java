package cn.xincan.security.core.validate.code.image;

import cn.xincan.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @description: 自定义图片校验码生成器
 * @className: ImageCodeGenerator
 * @date: 2019/8/5 19:13
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * @description: 向调用方传递图形验证码
     * @method: send
     * @author: Xincan Jiang
     * @date: 2019-08-21 18:22:19
     * @param: [request, imageCode]
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
