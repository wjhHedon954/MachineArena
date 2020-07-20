package com.whu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
public interface IUserService extends IService<User> {

    /**
     * 检查用户是否存在
     * author Jiahan Wang
     * create 2020-07-20 10:01
     * updator
     * update
     * @param userAccount
     * @return
     */
    boolean checkUserExists(String userAccount);

    /**
     * 检查密码是否正确
     * author Jiahan Wang
     * create 2020-07-20 10:05
     * updator
     * update
     * @param userAccount
     * @return
     */
    boolean checkPwd(String userAccount, String userPassword);

    /**
     * 添加用户
     * author Jiahan Wang
     * create 2020-07-20 10:05
     * updator
     * update
     * @param userAccount
     * @param userPassword
     */
    int saveUser(String userAccount, String userPassword);

    /**
     * 修改密码
     * author Jiahan Wang
     * create 2020-07-20 10:10
     * updator
     * update
     * @param userAccount
     * @param userPassword
     */
    int updateUserPassword(String userAccount, String userPassword, String newPassword);

    /**
     * 根据ID查询用户信息
     * author Jiahan Wang
     * create 2020-07-20 10:20
     * updator
     * update
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据账户名和密码查询用户信息
     * author Jiahan Wang
     * create 2020-07-20 10:30
     * updator
     * update
     * @param userAccount
     * @param userPassword
     * @return
     */
    User getUserByAccountAndPwd(String userAccount, String userPassword);

    /**
     * 更新用户信息
     * author Jiahan Wang
     * create 2020-07-20 10:40
     * updator
     * update
     * @param user
     * @return
     */
    int updateUserInfo(User user);
}
