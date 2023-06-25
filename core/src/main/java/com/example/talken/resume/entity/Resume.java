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
    private String parentCategory;

    @Column(nullable = false)
    private String childCategory;

    @Column
    private String imageURl;

    @Column(nullable = false)
    private Status.Resume publicStatus;

    @Column(nullable = false)
    private Status.Feedback feedbackStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Resume(String parentCategory, String childCategory, String imageURl,
                  Status.Resume publicStatus, Status.Feedback feedbackStatus, User user) {

        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.imageURl = imageURl;
        this.publicStatus = publicStatus;
        this.feedbackStatus = feedbackStatus;
        this.user = user;
    }

}
