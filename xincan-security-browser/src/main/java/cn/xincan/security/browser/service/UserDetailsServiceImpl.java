package cn.xincan.security.browser.service;


import cn.xincan.security.browser.entity.User;
import cn.xincan.security.browser.mapper.IUserMapper;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @Copyright (C), 2018
 * @ProjectName: xincan-security
 * @Package: cn.xincan.security.browser.service
 * @ClassName: IUserMapper
 * @Author: Xincan Jiang
 * @Description: 实现SpringSecurity UserDetailsService接口，重写loadUserByUsername方法
 * @Date: 2019/4/16 16:59
 * @Version: 1.0
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("登录用户：" + username);
        User user = this.userMapper.findByUsername(username);
        JSONObject json = (JSONObject) JSONObject.toJSON(user);
        System.out.println(json);
        return user;

    }

}
