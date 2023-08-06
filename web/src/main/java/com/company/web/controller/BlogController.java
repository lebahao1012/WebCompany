package com.company.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.company.web.entity.Blog;
import com.company.web.entity.CommentBlog;
import com.company.web.entity.User;
import com.company.web.repository.UserRepository;
import com.company.web.service.BlogService;
import com.company.web.service.CommentService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("create-post")
    public String createPost(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "post";
    }

    @PostMapping("/blog-login")
    public String blogLogin(Model model, @RequestBody User user, HttpSession session) {
        User userData = userRepository.findByEmail(user.getEmail());
        if (userData != null && userData.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", userData);
            return "redirect:/blog-login";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "error";
        }
    }

}
