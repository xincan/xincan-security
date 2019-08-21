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
import org.springframework.stereotype.Service;


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
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    /**
     * @description: 注入用户数据操作接口
     * @author: Xincan Jiang
     * @date: 2019-07-24 20:02:23
     */
    @Autowired
    private IUserMapper userMapper;

    /**
     * @description: 根据用户名称查询用户信息
     * @method: loadUserByUsername
     * @author: Xincan Jiang
     * @date: 2019-07-24 20:03:48
     * @param: [username 用户登录名称]
     * @return: org.springframework.security.core.userdetails.UserDetails
     * @exception:
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("登录用户：" + username);
        User user = this.userMapper.findByUsernameOrMobile(username, username);
        JSONObject json = (JSONObject) JSONObject.toJSON(user);
        System.out.println(json);
        return user;

    }

}
