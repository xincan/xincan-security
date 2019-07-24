package cn.xincan.security.browser.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 测试类
 * @ClassName: InfoController
 * @Date: 2019/7/23 18:50
 * @Author Xincan Jiang
 * @Version 1.0
 */
@RestController
@RequestMapping("browser")
public class InfoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;


    /**
     * @description 
     * @Method 
     * @Author Xincan Jiang
     * @Date 2019-07-23 1824:46:23
     * @param * @Param null
     * @Return 
     */
    @GetMapping("info")
    public Map<String, Object> info(){



        Map<String, Object> map = new HashMap<>();
        map.put("applicationName", this.applicationName);
        map.put("port", this.serverPort);
        return map;
    }
}
