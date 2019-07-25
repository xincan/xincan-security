package cn.xincan.security.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 * @className: UserController
 * @date: 2019/7/24 17:26
 * @author: Xincan Jiang
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public List<Map<String, Object>> list(){
        List<Map<String, Object>> list = new ArrayList<>(3);

        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("username", "张三");
        user1.put("age", 23);
        user1.put("birthday", "2018-12-18");

        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("username", "李四");
        user2.put("age", 26);
        user2.put("birthday", "2018-08-08");

        Map<String, Object> user3 = new HashMap<>();
        user3.put("id", 3);
        user3.put("username", "王五");
        user3.put("age", 17);
        user3.put("birthday", "2012-11-13");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        return list;
    }

}
