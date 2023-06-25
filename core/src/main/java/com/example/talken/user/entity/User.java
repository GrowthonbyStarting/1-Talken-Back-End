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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING)
    private String role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.Auth isAuthenticated;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.User isDeleted;

    @Builder
    public User(String email, String username, String password, String phone,
                String role, Status.Auth isAuthenticated, Status.User isDeleted) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.isAuthenticated = isAuthenticated;
        this.isDeleted = isDeleted;
    }

}
