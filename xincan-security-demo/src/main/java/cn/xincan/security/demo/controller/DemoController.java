package cn.xincan.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("core")
public class DemoController {

    @GetMapping("info")
    public String info(){
        return "xincan-security-core";
    }
}
