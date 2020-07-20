package com.whu.user.controller;


import com.constants.ResultCode;
import com.entity.User;
import com.results.CommonResult;
import com.whu.user.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hedon
 * @since 2020-07-11
 */
@RestController
public class UserController {

    @Autowired
    IUserService userService;


    /**
     * 接口 6.5.1 登录
     * author Jiahan Wang
     * create 2020-07-20 10:00
     * updator
     * updater
     * @param userAccount
     * @param userPassword
     * @return
     */
    @ApiOperation(value = "接口 6.5.1 登录",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount",value = "用户账号",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "userPassword",value = "用户密码",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/user/login")
    public CommonResult login(@RequestParam("userAccount")String userAccount,
                              @RequestParam("userPassword")String userPassword,
                              HttpSession session){

        //1. 检查用户名是否存在
        boolean exists = userService.checkUserExists(userAccount);
        if (!exists){
            //用户名不存在，直接返回
            return CommonResult.fail(ResultCode.USER_NOT_EXISTS);
        }
        //2. 检查密码是否正确
        User user = userService.getUserByAccountAndPwd(userAccount,userPassword);
        if (user == null){
            //密码错误
            return CommonResult.fail(ResultCode.PASSWORD_WRONG);
        }
        session.setAttribute("user",user);
        return CommonResult.success();
    }


    /**
     * 接口 6.5.2 注册
     * author Jiahan Wang
     * create 2020-07-20 10:10
     * updator
     * updater
     * @param userAccount
     * @param userPassword
     * @return
     */
    @ApiOperation(value = "接口 6.5.2 注册",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount",value = "用户账号",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "userPassword",value = "用户密码",paramType = "query",dataType = "String",required = true)
    })
    @PostMapping("/user/register")
    public CommonResult register(@RequestParam("userAccount")String userAccount,
                                 @RequestParam("userPassword")String userPassword){
        //1. 检查用户名是否存在
        boolean exists = userService.checkUserExists(userAccount);
        if (exists){
            return CommonResult.fail(ResultCode.USER_EXISTS);
        }
        //2. 保存用户
        int i = userService.saveUser(userAccount, userPassword);
        if (i!=1){
            return CommonResult.fail(ResultCode.INSERT_ERROR);
        }
        return CommonResult.success();
    }


    /**
     * 接口 6.5.3 修改
     * author Jiahan Wang
     * create 2020-07-20 10:15
     * updator
     * updater
     * @param userAccount
     * @param userPassword
     * @return
     */
    @ApiOperation(value = "接口 6.5.3 修改密码",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount",value = "用户账号",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "userPassword",value = "用户密码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "newPassword",value = "用户新密码",paramType = "query",dataType = "String",required = true)
    })
    @PostMapping("/user/changePwd")
    public CommonResult changePwd(@RequestParam("userAccount")String userAccount,
                                 @RequestParam("userPassword")String userPassword,
                                  @RequestParam("newPassword")String newPassword){
        //1. 检查原密码是否正确
        boolean right = userService.checkPwd(userAccount, userPassword);
        if (!right){
            return CommonResult.fail(ResultCode.PASSWORD_WRONG);
        }
        //2. 更新密码
        int i = userService.updateUserPassword(userAccount, userPassword, newPassword);
        if (i!=1){
            return CommonResult.fail(ResultCode.UPDATE_ERROR);
        }
        return CommonResult.success();
    }


    /**
     * 接口 6.5.4 查看用户基本信息 =》根据ID查询版本
     * author Jiahan Wang
     * create 2020-07-20 10:20
     * updator
     * updater
     * @param userId
     * @return
     */
    @ApiOperation(value = "接口 6.5.4 查看用户基本信息",httpMethod = "GET")
    @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "path",dataType = "Integer",required = true)
    @GetMapping("/user/{userId}")
    public CommonResult getUserById(@PathVariable("userId")Integer userId){
        if (userId == null){
            return CommonResult.fail(ResultCode.EMPTY_USER_ID);
        }
        User user = userService.getUserById(userId);
        if (user == null){
            return CommonResult.fail(ResultCode.USER_NOT_EXISTS);
        }
        return CommonResult.success().add("user",user);
    }




    /**
     * 接口 6.5.4 查看用户基本信息 =》根据用户名和密码查询版本
     * author Jiahan Wang
     * create 2020-07-20 10:25
     * updator
     * updater
     * @param userAccount
     * @param userPassword
     * @return
     */
    @ApiOperation(value = "接口 6.5.4 查看用户基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount",value = "用户账号",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "userPassword",value = "用户密码",paramType = "query",dataType = "String",required = true)
    })
    @GetMapping("/user/getUser")
    public CommonResult getUserByAccountAndPwd(@RequestParam("userAccount")String userAccount,
                                            @RequestParam("userPassword")String userPassword){
        User user = userService.getUserByAccountAndPwd(userAccount,userPassword);
        if (user == null){
            return CommonResult.fail(ResultCode.PASSWORD_WRONG);
        }
        return CommonResult.success().add("user",user);
    }


    /**
     * 接口 6.5.5 修改用户基本信息
     * author Jiahan Wang
     * create 2020-07-20 10:30
     * updator
     * updater
     * @param user
     * @return
     */
    @ApiOperation(value = "接口 6.5.5 修改用户基本信息",httpMethod = "PUT")
    @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "path",dataType = "Integer",required = true)
    @PutMapping("/user")
    public CommonResult updateUserInfo(@RequestBody User user){
        int count = userService.updateUserInfo(user);
        if (count != 1){
            return CommonResult.fail(ResultCode.UPDATE_ERROR);
        }
        return CommonResult.success();
    }


    /**
     * 接口 6.5.6 退出登录
     * author Jiahan Wang
     * create 2020-07-20 10:33
     * updator
     * updater
     * @param session
     * @return
     */
    @ApiOperation(value = "接口 6.5.6 退出登录",httpMethod = "GET")
    @GetMapping("/logout")
    public CommonResult logout(HttpSession session){
        session.removeAttribute("user");
        return CommonResult.success();
    }

    /**
     * 接口 6.5.7 读取用户头像
     * author Jiahan Wang
     * create 2020-07-20 10:36
     * updator
     * updater
     * @param userId
     * @return
     */
    @ApiOperation(value = "接口 6.5.7 读取用户头像",httpMethod = "GET")
    @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "query",dataType = "Integer",required = true)
    @GetMapping("/user/avator")
    public CommonResult getUserAvator(@RequestParam("userId")Integer userId){
        User user = userService.getUserById(userId);
        if (user == null){
            return CommonResult.fail(ResultCode.USER_NOT_EXISTS);
        }
        String userAvatorUrl = user.getUserAvatorUrl();
        return CommonResult.success().add("avator",userAvatorUrl);
    }

    /**
     * 接口 6.5.8 修改用户头像
     * author Jiahan Wang
     * create 2020-07-20 10:40
     * updator
     * updater
     * @param userId
     * @param userAvatorUrl
     * @return
     */
    @ApiOperation(value = "接口 6.5.8 修改用户头像",httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "userAvatorUrl",value = "用户头像URL",paramType = "query",dataType = "String",required = true)
    })
    @PutMapping("/user/avator")
    public CommonResult changeUserAvator(@RequestParam("userId")Integer userId,
                                         @RequestParam("userAvatorUrl")String userAvatorUrl){
        User user = userService.getUserById(userId);
        user.setUserAvatorUrl(userAvatorUrl);
        int i = userService.updateUserInfo(user);
        if (i!=1){
            return CommonResult.fail(ResultCode.UPDATE_ERROR);
        }
        return CommonResult.success();
    }
}
