package cn.xincan.security.core.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 验证码处理管理器
 * @className: SmsCodeAuthenticationSecurityConfig
 * @date: 2019/8/21 15:15
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * @description: 验证码验证处理器类型
	 * @method: findValidateCodeProcessor
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:00:59
	 * @param: [type]
	 * @return: cn.xincan.security.core.validate.code.ValidateCodeProcessor
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	/**
	 * @description: 验证码验证处理器类型
	 * @method: findValidateCodeProcessor
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:01:18
	 * @param: [type]
	 * @return: cn.xincan.security.core.validate.code.ValidateCodeProcessor
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}
