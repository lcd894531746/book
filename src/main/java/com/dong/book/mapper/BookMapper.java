package com.dong.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.book.pojo.Book;
import org.springframework.stereotype.Repository;



@Repository
public interface BookMapper extends BaseMapper<Book> {
}
