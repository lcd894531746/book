package com.dong.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.book.mapper.ReaderMapper;
import com.dong.book.pojo.Reader;
import com.dong.book.service.ReaderService;
import org.springframework.stereotype.Service;

@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements ReaderService {
//    @Override
    public boolean login(String userName, String userPwd) {
//      条件构造器
        QueryWrapper<Reader> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rname", userName);
        Reader reader = this.baseMapper.selectOne(queryWrapper);
        if (reader == null) {
            return false;
        }
//        String s = DigestUtil.md5Hex(userPwd);
//        s 是前端 输入的密码  user.getPassword() 是数据库密码
        if (userPwd.equals(reader.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
