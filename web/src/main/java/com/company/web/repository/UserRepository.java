package com.company.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.web.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String userEmail);

}
