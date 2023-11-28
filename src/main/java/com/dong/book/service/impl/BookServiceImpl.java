package com.dong.book.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.book.mapper.BookMapper;
import com.dong.book.pojo.Book;
import com.dong.book.service.BookService;
import org.springframework.stereotype.Service;
@Service
public class BookServiceImpl  extends ServiceImpl<BookMapper,Book> implements BookService {

}