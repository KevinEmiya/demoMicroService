package com.ky.blog.core.controller;

import com.ky.blog.core.entity.User;
import com.ky.blog.core.service.AuthorityService;
import com.ky.blog.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    private static final Long ROLE_USER_AUTH_ID = 2L;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg","登陆失败，用户名或密码错误！");
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        user.addAuthority(authorityService.getAuthorityById(ROLE_USER_AUTH_ID));
        userService.registerUser(user);
        return "redirect:/login";
    }
}
