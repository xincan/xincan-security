package cn.xincan.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @description: 注入图形、短信验证码短信验证码接口类
     *
     * 系统启动时会生成这两个实例，如果存在则不创建
     *
     * @author: Xincan Jiang
     * @date: 2019-08-21 18:11:02
     */
    @Autowired
    private  Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * @description: 创建验证码
     * 根据验证码类型不同，调用不同的{@link ValidateCodeProcessor} 接口实现
     * @method: createCode
     * @author: Xincan Jiang
     * @date: 2019-08-20 18:26:25
     * @param: [request, response, type]
     * @return:
     * @exception:
     */
    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        this.validateCodeProcessors.get(type + "ValidateCodeProcessor").create(new ServletWebRequest(request, response));
    }

}
