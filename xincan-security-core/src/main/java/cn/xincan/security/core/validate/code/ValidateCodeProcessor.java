/**
 * 
 */
package cn.xincan.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 校验码处理器，封装不同校验码的处理逻辑
 * @className: ValidateCodeProcessor
 * @date: 2019/8/21 15:15
 * @author: Xincan Jiang
 * @version: 1.0
 */
public interface ValidateCodeProcessor {

	/**
	 * @description: 创建校验码
	 * @method:
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:06:54
	 * @param: [request]
	 * @return:
	 * @exception: Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * @description: 校验验证码
	 * @author: Xincan Jiang
	 * @date: 2019-08-21 18:06:30
	 * @return: [servletWebRequest]
	 */
	void validate(ServletWebRequest servletWebRequest);

}
