package com.dong.book.pojo;

/*
 * 实体类  个人理解就是 拿到具体的值   代表的是 数据库的对应字段
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/*
 * 使用 Data  注解 可以 不用写  get   set  方法  获取 设置  成员变量的数据
 */

@Data
@TableName("book")
public class Book {
    private Integer id;
    private String bname;
    private String type;
    private String author;
    private String stock;
    private String language;
    private String bimage;
}
