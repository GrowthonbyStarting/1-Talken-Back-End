package com.example.talken.resume.dto;

import com.example.talken.resume.entity.Resume;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResumeResponseDto {

    private String username;

    @Builder
    public ResumeResponseDto(String username) {
        this.username = username;
    }

    public static ResumeResponseDto fromEntity(Resume resume) {
        return ResumeResponseDto.builder()
                .build();
    }
}
