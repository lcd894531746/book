// UserServiceImpl 类
package com.dong.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.book.mapper.UserMapper;
import com.dong.book.pojo.User;
import com.dong.book.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //    @Override
    public boolean login(String userName, String usePwd) {
        /* QueryWrapper<User> queryWrapper = new QueryWrapper<>();:
         *这是 MyBatis-Plus 提供的查询条件构造器，用于构建数据库查询条件。在这里，它创建了一个查询条件，通过用户名（username字段）来查找用户。
         *
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            return false;
        }
//        String s = DigestUtil.md5Hex(usePwd);
        if (usePwd.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
