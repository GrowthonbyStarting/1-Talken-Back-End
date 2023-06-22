package com.example.talken.resume.entity;

import com.example.talken.common.Status;
import com.example.talken.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Status.Resume isPublic;

    @Column(nullable = false)
    private Status.Feedback feedbackStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Resume(Status.Resume isPublic, Status.Feedback feedbackStatus, User user) {
        this.isPublic = isPublic;
        this.feedbackStatus = feedbackStatus;
        this.user = user;
    }

}
