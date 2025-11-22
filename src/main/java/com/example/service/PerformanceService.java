package com.example.service;

import com.example.entity.Post;
import com.example.entity.User;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerformanceService {
    private final UserRepository userRepo;
    private final PostRepository postRepo;

    public PerformanceService(UserRepository userRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    // -----------------------------
    // 1️⃣ N+1 PROBLEM DEMO
    // -----------------------------
    public List<User> getUsersNPlusOne() {
        List<User> users = userRepo.findAll();  // This generates N+1
        users.forEach(u -> u.getPosts().size()); // Trigger lazy loading
        return users;
    }

    public List<User> getUsersJoinFetch() {
        return userRepo.findAllUsersWithPosts(); // JOIN FETCH → single query
    }


    // -----------------------------
    // 2️⃣ MySQL INDEXING DEMO
    // -----------------------------
    public String createIndexes() {
        return """
            Suggested Indexes:
            1. CREATE INDEX idx_user_name ON user(name);
            2. CREATE INDEX idx_post_title ON post(title);
            3. CREATE COMPOSITE INDEX idx_post_user_title ON post(user_id, title);
            """;
    }


    // -----------------------------
    // 3️⃣ BATCH INSERT
    // -----------------------------
    @Transactional
    public String batchInsertPosts(Long userId, int count) {

        User user = userRepo.findById(userId).orElseThrow();

        for (int i = 1; i <= count; i++) {
            Post post = new Post();
            post.setTitle("Post " + i);
            post.setContent("Demo content");
            post.setUser(user);
            postRepo.save(post);
        }

        return count + " posts inserted in batch.";
    }


    // -----------------------------
    // 4️⃣ CACHING DEMO
    // -----------------------------
    @Cacheable("userCache")
    public User getUserCached(Long id) {
        System.out.println("DB Hit: Fetching user...");
        return userRepo.findById(id).orElseThrow();
    }


    // -----------------------------
    // 5️⃣ LAZY vs EAGER
    // -----------------------------
    public String lazyEagerDemo(Long id) {
        User user = userRepo.findById(id).orElseThrow();

        StringBuilder sb = new StringBuilder();
        sb.append("User Loaded. Posts not loaded yet.\n");

        // Accessing lazy field
        sb.append("Now accessing posts… size = ").append(user.getPosts().size());

        return sb.toString();
    }

}
