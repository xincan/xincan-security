package cn.xincan.security.core.validate.code;

import cn.xincan.security.core.validate.code.image.ImageCode;
import cn.xincan.security.core.validate.code.sms.SmsCode;
import cn.xincan.security.core.validate.code.sms.SmsCodeSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     *
     * 图片验证码生成器
     *
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:32:18
     */
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    /**
     * @description: 注入自定义验证码生成策略接口
     *
     * 短信验证码生成器
     *
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:32:18
     */
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    /**
     * @description: 注入短信验证码接口类
     * @method:
     * @author: Xincan Jiang
     * @date: 2019-08-06 17:03:46
     */
    @Autowired
    private SmsCodeSenderService smsCodeSenderService;



    /**
     * @description: 获取图片验证码
     * @method: createImageCode
     * @author: Xincan Jiang
     * @date: 2019-08-06 16:40:50
     * @param: [request, response]
     * @return: void
     * @exception: IOException
     */
    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) this.imageCodeGenerator.generator(new ServletWebRequest(request));
        this.sessionStrategy.setAttribute(new ServletWebRequest(request), this.SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * @description: 获取短信验证码
     * @method: createSmsCode
     * @author: Xincan Jiang
     * @date: 2019-08-06 16:40:50
     * @param: [request, response]
     * @return: void
     * @exception: IOException
     */
    @GetMapping("/code/sms")
    public Map<String, Object> createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        SmsCode smsCode = (SmsCode)this.smsCodeGenerator.generator(new ServletWebRequest(request));
        this.sessionStrategy.setAttribute(new ServletWebRequest(request), this.SESSION_KEY, smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        this.smsCodeSenderService.send(mobile, smsCode.getCode());

        Map<String, Object> map = new HashMap<>();
        map.put("code", smsCode.getCode());
        map.put("mobile", mobile);
        return map;
    }

}
