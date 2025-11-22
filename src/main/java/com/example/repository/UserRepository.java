package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    // Fix N+1 Problem
    @Query("SELECT u FROM User u JOIN FETCH u.posts")
    List<User> findAllUsersWithPosts();
}
