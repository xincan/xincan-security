package cn.xincan.security.core;

import cn.xincan.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 安全核心配置类
 * @className: SecurityCoreConfig
 * @date: 2019/7/24 11:12
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
