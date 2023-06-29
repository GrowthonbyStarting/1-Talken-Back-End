package com.example.talken.resume.dto.request;

import com.example.talken.common.Status;
import com.example.talken.experience.dto.ExperienceRequestDto;
import com.example.talken.experience.entity.Experience;
import com.example.talken.resume.entity.Resume;
import com.example.talken.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeRequestDto {

    private String parentCategory;
    private String childCategory;
    private ExperienceRequestDto experienceRequest;

    public Resume toEntity(Status.Resume publicStatus, Status.Feedback feedbackStatus, User user) {
        return Resume.builder()
                .parentCategory(parentCategory)
                .childCategory(childCategory)
                .publicStatus(publicStatus)
                .feedbackStatus(feedbackStatus)
                .user(user)
                .experience(experienceRequest.toEntity())
                .build();
    }
}
