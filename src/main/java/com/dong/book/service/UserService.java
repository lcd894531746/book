package com.dong.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.book.pojo.User;

public interface UserService extends IService<User> {
    boolean login(String userName, String usePwd);
}
