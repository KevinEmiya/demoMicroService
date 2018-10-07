package com.ky.blog.core.controller;

import com.ky.blog.core.entity.User;
import com.ky.blog.core.model.Response;
import com.ky.blog.core.service.AuthorityService;
import com.ky.blog.core.service.UserService;
import com.ky.blog.core.utils.ConstraintViolationExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @GetMapping
    public ModelAndView list(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             @RequestParam(value = "name", required = false, defaultValue = "") String name,
                             Model model) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);

        model.addAttribute("page", page);
        model.addAttribute("userList", page.getContent());
        model.addAttribute("title", "用户管理");

        String url = async ? "users/list :: #mainContainerRepleace" : "users/list";

        return new ModelAndView(url, "userModel", model);
    }

    @GetMapping("/add")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("users/add", "userModel", model);
    }

    @PostMapping
    public ResponseEntity<Response> saveOrUpdateUser(User user, Long authorityId) {
        user.addAuthority(authorityService.getAuthorityById(authorityId));
        try {
            userService.saveOrUpdateUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(Response.fail(ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(Response.ok("创建成功！", user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(Response.fail(e.getMessage()));
        }
        return ResponseEntity.ok().body(Response.ok("删除成功！", id));
    }

    @GetMapping("edit/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.get());
        return new ModelAndView("/users/edit", "userModel", model);
    }
}
