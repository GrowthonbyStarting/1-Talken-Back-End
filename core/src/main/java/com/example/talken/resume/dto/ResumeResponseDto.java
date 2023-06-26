package com.example.talken.resume.dto;

import com.example.talken.resume.entity.Resume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResumeResponseDto {

    private String parentCategory;
    private String childCategory;
    private String imageUrl;
    private String username;


    @Builder
    public ResumeResponseDto(String parentCategory, String childCategory,
                             String imageUrl, String username) {

        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public static ResumeResponseDto fromEntity(Resume resume) {
        return ResumeResponseDto.builder()
                .parentCategory(resume.getParentCategory())
                .childCategory(resume.getChildCategory())
                .imageUrl(resume.getImageUrl())
                .username(resume.getUser().getUsername())
                .build();
    }
}
