package com.example.talken.resume.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeDetailResponseDto {

    private ResumeResponseDto resumeResponse;
    private List<String> imageUrls;

    @Builder
    public ResumeDetailResponseDto(ResumeResponseDto resumeResponse, List<String> imageUrls) {
        this.resumeResponse = resumeResponse;
        this.imageUrls = imageUrls;
    }
}
