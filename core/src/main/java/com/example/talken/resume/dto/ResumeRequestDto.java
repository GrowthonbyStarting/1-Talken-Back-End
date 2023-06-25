package com.example.talken.resume.dto;

import com.example.talken.common.Status;
import com.example.talken.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeRequestDto {

    private String parentCategory;
    private String childCategory;
    private List<String> keywords;
    private String imageURl;

    public Resume toEntity(Status.Resume publicStatus, Status.Feedback feedbackStatus) {
        return Resume.builder()
                .parentCategory(parentCategory)
                .childCategory(childCategory)
                .imageURl(imageURl)
                .publicStatus(publicStatus)
                .feedbackStatus(feedbackStatus)
                .build();
    }
}
