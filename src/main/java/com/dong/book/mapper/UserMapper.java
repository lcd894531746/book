// UserMapper 接口
package com.dong.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.book.pojo.User;
import org.springframework.stereotype.Repository;

@Repository

/*
* mqpper 是操作数据库层面的
* UserMapper 是一个 MyBatis 的 Mapper 接口，用于执行数据库操作；
* */
public interface UserMapper extends BaseMapper<User> {
}