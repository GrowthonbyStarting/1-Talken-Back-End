package com.example.talken.resume.dto;

import com.example.talken.resume.entity.Resume;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeRequestDto {

    public Resume toEntity() {
        return Resume.builder()
                .build();
    }
}
