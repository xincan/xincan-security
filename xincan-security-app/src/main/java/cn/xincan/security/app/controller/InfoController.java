package cn.xincan.security.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("core")
public class InfoController {

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
