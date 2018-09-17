package com.ky.thymeleaf.controller;

import com.ky.thymeleaf.model.User;
import com.ky.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView list(Model model){
        model.addAttribute("userList", userService.listUser());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    @PostMapping
    public ModelAndView saveOrUpdateUser(User user){
        userService.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping("modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("/users/form", "userModel", model);
    }
}
