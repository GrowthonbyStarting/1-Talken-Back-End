package com.example.talken.resume.entity;

import com.example.talken.common.Status;
import com.example.talken.common.entity.BaseEntity;
import com.example.talken.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String parentCategory;

    @Column(nullable = false)
    private String childCategory;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.Resume publicStatus;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.Feedback feedbackStatus;

    @OneToOne(mappedBy = "resume")
    private User user;

    @Builder
    public Resume(String parentCategory, String childCategory, String imageUrl,
                  Status.Resume publicStatus, Status.Feedback feedbackStatus, User user) {

        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.imageUrl = imageUrl;
        this.publicStatus = publicStatus;
        this.feedbackStatus = feedbackStatus;
        this.user = user;
    }

}
