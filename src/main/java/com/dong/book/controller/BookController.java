package com.dong.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.book.pojo.Book;
import com.dong.book.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    @Value("${location}")
    private String location;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/listBook", method = RequestMethod.GET)
    public String listBook(@RequestParam(required = false, value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize, Model model, Book book) {
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
    }/*

   添加图书

    */

    @RequestMapping("/addBook")
    public String addBook() {
        return "bookSave";
    }

    @RequestMapping("/saveBook")
    public String saveBook(Book book, MultipartFile file) {
//        处理文件
        transFile(book, file);
        Boolean save = bookService.save(book);
        return "redirect:/book/listBook";
    }
    /*
     * 不需要返回值的时候就需要  viod
     * transFile  处理文件名称
     */

    public void transFile(Book book, MultipartFile file) {

        String fileName = file.getOriginalFilename();//获取文件名称
        Integer index = fileName.lastIndexOf(".");//获取文件后缀名.的位置  index
        String suffix = fileName.substring(index);//切割出文件名称的
        String prefix = fileName.substring(0, index);
        String path = prefix + suffix;
        File file1 = new File(location);
        if (!file1.exists()) {
            file1.mkdirs();
        }

        File file2 = new File(file1, path);
        try {
            file.transferTo(file2);
        }catch (IOException e){
            e.printStackTrace();
        }
        book.setBimage(path);
    }
    /*
     * 删除图书* */
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Integer id) {

        System.out.println(id);

        Boolean a = bookService.removeById(id);
        return "redirect:/book/listBook";
    }
}
