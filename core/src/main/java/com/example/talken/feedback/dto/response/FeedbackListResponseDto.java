package com.example.talken.feedback.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FeedbackListResponseDto {

    private List<FeedbackResponseDto> feedbackResponseList;

    @Builder
    public FeedbackListResponseDto(List<FeedbackResponseDto> feedbackResponseList) {
        this.feedbackResponseList = feedbackResponseList;
    }
}
