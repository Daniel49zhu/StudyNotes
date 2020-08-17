package com.zjc.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.entity.User;
import com.zjc.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}