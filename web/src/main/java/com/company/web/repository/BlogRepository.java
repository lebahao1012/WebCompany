package com.company.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

import com.company.web.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findById(ID id);
}
