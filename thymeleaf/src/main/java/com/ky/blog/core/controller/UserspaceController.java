package com.ky.blog.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/u")
public class UserspaceController {

    Log logger = LogFactory.getLog(UserspaceController.class);

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        logger.info("username" + username);
        return "/userspace/u";
    }

    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "category", required = false) Long category,
                                   @RequestParam(value = "keyword", required = false) String keyword) {

        if (category != null) {

            logger.info("category:" + category);
            logger.info("selflink:" + "redirect:/u/" + username + "/blogs?category=" + category);
            return "/userspace/u";

        } else if (keyword != null && keyword.isEmpty() == false) {

            logger.info("keyword:" + keyword);
            logger.info("selflink:" + "redirect:/u/" + username + "/blogs?keyword=" + keyword);
            return "/userspace/u";
        }

        logger.info("order:" + order);
        logger.info("selflink:" + "redirect:/u/" + username + "/blogs?order=" + order);
        return "/userspace/u";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {

        logger.info("blogId:" + id);
        return "/userspace/blog";
    }


    @GetMapping("/{username}/blogs/edit")
    public String editBlog() {

        return "/userspace/blogedit";
    }
}
