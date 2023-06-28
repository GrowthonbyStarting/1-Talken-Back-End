package com.example.talken.user.entity;

import com.example.talken.common.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long kakaoId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.Auth isAuthenticated;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.User isDeleted;

    @Builder
    public User(Long kakaoId, String email, String username, String password, String phone,
                UserRole role, Status.Auth isAuthenticated, Status.User isDeleted) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.isAuthenticated = isAuthenticated;
        this.isDeleted = isDeleted;
    }

    public User update(String username) {
        this.username = username;
        return this;
    }
}
