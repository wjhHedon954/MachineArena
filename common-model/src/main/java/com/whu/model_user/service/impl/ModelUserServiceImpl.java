package com.whu.model_user.service.impl;

import com.entity.ModelUser;
import com.mapper.ModelUserMapper;
import com.whu.model_user.service.IModelUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hedon
 * @since 2020-07-19
 */
@Service
public class ModelUserServiceImpl extends ServiceImpl<ModelUserMapper, ModelUser> implements IModelUserService {

}
