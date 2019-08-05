package cn.xincan.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 图形验证码控制层
 * @className: ValidateCodeController
 * @date: 2019/8/3 20:29
 * @author: Xincan Jiang
 * @version: 1.0
 */
@RestController
public class ValidateCodeController {

    /**
     * @description: 定义session key的名称，用于存储图形数字校验码
     * @method:
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:20:11
     */
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    /**
     * @description: 实例化session处理类
     * @method:
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:20:11
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * @description: 注入自定义验证码生成策略接口
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:32:18
     */
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = this.imageCodeGenerator.generator(new ServletWebRequest(request));
        this.sessionStrategy.setAttribute(new ServletWebRequest(request), this.SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
