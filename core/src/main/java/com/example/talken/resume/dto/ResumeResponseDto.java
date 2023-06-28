package com.example.talken.resume.dto;

import com.example.talken.resume.entity.Resume;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResumeResponseDto {

    private String parentCategory;
    private String childCategory;
    private List<String> imageUrls;
    private String username;


    @Builder
    public ResumeResponseDto(String parentCategory, String childCategory,
                             List<String> imageUrls, String username) {

        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
        this.imageUrls = imageUrls;
        this.username = username;
    }

    public static ResumeResponseDto fromEntity(Resume resume) {
        return ResumeResponseDto.builder()
                .parentCategory(resume.getParentCategory())
                .childCategory(resume.getChildCategory())
                .username(resume.getUser().getUsername())
                .build();
    }
}
