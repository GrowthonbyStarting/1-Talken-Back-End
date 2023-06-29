package com.example.talken.resume.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResumeListResponseDto {

    private List<ResumeResponseDto> resumeResponseList;

    @Builder
    public ResumeListResponseDto(List<ResumeResponseDto> resumeResponseList) {
        this.resumeResponseList = resumeResponseList;
    }
}
