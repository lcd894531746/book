package com.dong.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.book.pojo.Reader;

public interface ReaderService extends IService<Reader> {
    boolean login(String userName, String usePwd);
}
