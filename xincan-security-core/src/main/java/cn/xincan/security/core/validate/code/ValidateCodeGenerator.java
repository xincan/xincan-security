package cn.xincan.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 校验码生成器
 *
 * 提供校验码生成器，方便别人重写此方法
 *
 * @className: ValidateCodeGenerator
 * @date: 2019/8/5 19:10
 * @author: Xincan Jiang
 * @version: 1.0
 */
public interface ValidateCodeGenerator {

    /**
     * @description: 自定义校验器生成规则接口
     * @author: Xincan Jiang
     * @date: 2019-08-05 19:38:51
     */
    ValidateCode generator(ServletWebRequest request);

}
