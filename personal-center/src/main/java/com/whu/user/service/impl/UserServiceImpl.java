package com.whu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.UserMapper;
import com.whu.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 检查用户是否存在
     * author Jiahan Wang
     * create 2020-07-20 10:01
     * updator
     * update
     * @param userAccount
     * @return
     */
    @Override
    public boolean checkUserExists(String userAccount) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount);
        Integer integer = userMapper.selectCount(queryWrapper);
        return integer>0;
    }

    /**
     * 检查密码是否正确
     * author Jiahan Wang
     * create 2020-07-20 10:05
     * updator
     * update
     * @param userAccount
     * @return
     */
    @Override
    public boolean checkPwd(String userAccount, String userPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount)
                .eq("user_password",userPassword);
        Integer integer = userMapper.selectCount(queryWrapper);
        return integer==1;
    }

    /**
     * 添加用户
     * author Jiahan Wang
     * create 2020-07-20 10:05
     * updator
     * update
     * @param userAccount
     * @param userPassword
     */
    @Override
    public int saveUser(String userAccount, String userPassword) {
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(userPassword);
        int insert = userMapper.insert(user);
        return insert;
    }

    /**
     * 修改密码
     * author Jiahan Wang
     * create 2020-07-20 10:10
     * updator
     * update
     * @param userAccount
     * @param userPassword
     */
    @Override
    public int updateUserPassword(String userAccount, String userPassword, String newPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount)
                .eq("user_password",userPassword);
        User user = userMapper.selectOne(queryWrapper);
        user.setUserPassword(newPassword);
        int i = userMapper.updateById(user);
        return i;
    }

    /**
     * 根据ID查询用户信息
     * author Jiahan Wang
     * create 2020-07-20 10:20
     * updator
     * update
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

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
    @Override
    public User getUserByAccountAndPwd(String userAccount, String userPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount)
                .eq("user_password",userPassword);
        return userMapper.selectOne(queryWrapper);
    }


    /**
     * 更新用户信息
     * author Jiahan Wang
     * create 2020-07-20 10:40
     * updator
     * update
     * @param user
     * @return
     */
    @Override
    public int updateUserInfo(User user) {
        int i = userMapper.updateById(user);
        return i;
    }
}
