package com.company.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.web.service.BlogService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BlogController {
    private BlogService blogService;

    @GetMapping("/blog")
    public String getAllBlog(Model model) {
        model.addAttribute("blog", blogService.getAllBlog());
        return "blog";
    }

    @GetMapping("/blog-details/{id}")
    public String showBlog(Model model) {
        model.addAttribute("blog", blogService.getBlog(null));
        return "blog-details";
    }

}
