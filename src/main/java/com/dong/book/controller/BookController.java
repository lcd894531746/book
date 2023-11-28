package com.dong.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.book.pojo.Book;
import com.dong.book.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/listBook", method = RequestMethod.GET)
    public String listBook(@RequestParam(required = false, value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,
                           Model model, Book book
    ) {
        if (pageNum < 0) {
            pageNum = 1;
        }
        if (pageSize < 0) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (book.getBname() != null) {
            queryWrapper.like("bname", book.getBname());
        }
        List<Book> list = bookService.list(queryWrapper);
        PageInfo<Book> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "bookList";
    }
}
