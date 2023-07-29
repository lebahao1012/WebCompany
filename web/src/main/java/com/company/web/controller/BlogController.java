package com.company.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.events.Event.ID;

import com.company.web.entity.Blog;
import com.company.web.entity.CommentBlog;
import com.company.web.entity.User;
import com.company.web.service.BlogService;
import com.company.web.service.CommentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BlogController {
    private BlogService blogService;
    private CommentService commentService;

    @GetMapping("/blog-login")
    public String getAllBlogLogin(Model model) {
        model.addAttribute("blog", blogService.getAllBlog());
        return "blog-login";
    }

    @GetMapping("/blog")
    public String getAllBlog(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("blog", blogService.getAllBlog());
        return "blog";
    }

    @GetMapping("/blog-details/{id}")
    public String showBlog(@PathVariable Integer id, Model model) {
        Blog blog = blogService.getBlog(id);
        List<CommentBlog> commentBlog = commentService.getAllCommentsByBlog(id);
        if (blog == null) {
            // Xử lý nếu không tìm thấy bài đăng blog với ID tương ứng
            return "error";
        }
        model.addAttribute("blog", blog);
        model.addAttribute("comment", commentBlog);
        return "blog-details";
    }

}
