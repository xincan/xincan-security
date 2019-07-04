package cn.xincan.security.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @GetMapping("info")
    public Map<String, Object> info(){

        Map<String, Object> map = new HashMap<>();
        map.put("applicationName", this.applicationName);
        map.put("port", this.serverPort);
        return map;
    }
}
