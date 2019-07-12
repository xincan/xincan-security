package cn.xincan.security.browser.mapper;


import cn.xincan.security.browser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Copyright (C), 2018
 * @ProjectName: xincan-security
 * @Package: cn.xincan.security.browser.mapper
 * @ClassName: IUserMapper
 * @Author: Xincan Jiang
 * @Description: 用户信息接口层
 * @Date: 2019/4/16 16:59
 * @Version: 1.0
 */
@Repository
public interface IUserMapper extends JpaRepository<User, Long> {

    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return User
     */
    User findByUsername(String username);

}
