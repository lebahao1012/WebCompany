package com.company.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event.ID;

import com.company.web.entity.Blog;
import com.company.web.repository.BlogRepository;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public Blog getBlog(ID id) {
        return blogRepository.findById(id);
    }
}
