package com.dong.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.book.pojo.User;
import com.dong.book.service.ReaderService;
import com.dong.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReaderService readerService;
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String userPwd, Model model, HttpSession session) {
        System.out.println("userName:" + userName);
        //管理员 的账号是固定的admin
        if (userName.equals("admin")) {
            boolean i=userService.login(userName, userPwd);
            if (i) {
                session.setAttribute("currentUser", userName);
                return "waterMainMenu";
            } else {
                model.addAttribute("msg", "用户名或密码错误");
                return "index";
            }
        } else {
            /*
             *普通用户
             * */
            boolean i=readerService.login(userName, userPwd);
            if (i) {
//                查询用户的头像
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("username", userName);
                User user=userService.getOne(queryWrapper);
                session.setAttribute("currentUser", userName);
                session.setAttribute("image", user.getImage());
                session.setAttribute("password", user.getPassword());
                session.setAttribute("email", user.getEmail());
                return "waterMainMenu1";
            } else {
                model.addAttribute("msg", "用户名或密码错误");
                return "index";
            }
        }
    }



}
