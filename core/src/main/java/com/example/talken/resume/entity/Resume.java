package com.example.talken.resume.entity;

import com.example.talken.common.Status;
import com.example.talken.common.entity.BaseEntity;
import com.example.talken.resume.dto.request.ResumeRequestDto;
import com.example.talken.resumeImage.entity.ResumeImage;
import com.example.talken.user.entity.User;
import com.example.talken.experience.entity.Experience;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.Resume publicStatus;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status.Feedback feedbackStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userInfo_id")
    private Experience experience;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.PERSIST)
    private List<ResumeImage> images = new ArrayList<>();

    @Builder
    public Resume(String parentCategory, String childCategory, Status.Resume publicStatus,
                  Status.Feedback feedbackStatus, User user, Experience experience) {

        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.publicStatus = publicStatus;
        this.feedbackStatus = feedbackStatus;
        this.user = user;
        this.experience = experience;
    }

    public void update(ResumeRequestDto resumeRequest) {
        this.parentCategory = resumeRequest.getParentCategory();
        this.childCategory = resumeRequest.getChildCategory();
        this.experience = resumeRequest.getExperienceRequest().toEntity();
    }
}
