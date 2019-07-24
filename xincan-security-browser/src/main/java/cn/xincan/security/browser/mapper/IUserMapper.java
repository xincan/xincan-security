package cn.xincan.security.browser.mapper;


import cn.xincan.security.browser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户信息数据接口层
 * @className: IUserMapper
 * @date: 2019-07-23 18:50:22
 * @author: Xincan Jiang
 * @version: 1.0
 */
@Repository
public interface IUserMapper extends JpaRepository<User, Long> {

    /**
     * @description: 根据用户名称查询用户信息
     * @method:
     * @author: Xincan Jiang
     * @date: 2019-07-24 10:37:46
     * @param: []null
     * @return: cn.xincan.security.browser.entity.User
     */
    User findByUsername(String username);

}
