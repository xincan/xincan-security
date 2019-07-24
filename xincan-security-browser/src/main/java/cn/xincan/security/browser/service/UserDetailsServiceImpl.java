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
 * @description: 用户信息服务实现层
 *
 * 重写UserDetailsService的loadUserByUsername方法，获取数据库用户信息
 *
 * @className: UserDetailsServiceImpl
 * @date: 2019-07-23 18:50:22
 * @author: Xincan Jiang
 * @version: 1.0
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
