package cn.xincan.security.core.validate.code.impl;

import java.util.Map;

import cn.xincan.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @description: 抽象的图片验证码处理器
 * @className: AbstractValidateCodeProcessor
 * @date: 2019/8/6 16:50
 * @author: Xincan Jiang
 * @version: 1.0
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * @description: 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:17:23
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	/**
	 * @description: 注入验证码存取器接口
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:17:23
	 */
	@Autowired
	private ValidateCodeRepository validateCodeRepository;

	/**
	 * @description: 创建验证码
	 * @method: create
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:18:11
	 * @param: [request]
	 * @exception: Exception
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * @description: 生成校验码
	 * @method: generate
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:18:44
	 * @param: []request
	 * @return: C
	 */
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request).toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = this.validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * @description: 保存校验码
	 * @method: save
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:19:29
	 * @param: [request, validateCode]
	 * @return: void
	 * @exception:
	 */
	private void save(ServletWebRequest request, C validateCode) {
		ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
		this.validateCodeRepository.save(request, code, getValidateCodeType(request));
	}

	/**
	 * @description: 发送校验码，由子类实现
	 * @method: save
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:19:29
	 * @param: [request, validateCode]
	 * @return: void
	 * @exception: Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	/**
	 * @description: 根据请求的url获取校验码的类型
	 * @method: getValidateCodeType
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:20:35
	 * @param: [request]
	 * @return: cn.xincan.security.core.validate.code.ValidateCodeType
	 */
	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeType codeType = getValidateCodeType(request);
		C codeInSession = (C) this.validateCodeRepository.get(request, codeType);
		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("请填写"+codeType + "验证码");
		}
		if (codeInSession == null) {
			throw new ValidateCodeException(codeType + "验证码不存在");
		}
		if (codeInSession.isExpried()) {
			this.validateCodeRepository.remove(request, codeType);
			throw new ValidateCodeException(codeType + "验证码已过期，请重新获取");
		}
		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(codeType + "验证码不正确");
		}
		this.validateCodeRepository.remove(request, codeType);
	}
}
