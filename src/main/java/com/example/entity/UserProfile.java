package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}