package com.example.talken.feedback.dto.response;

import com.example.talken.feedback.entity.Feedback;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedbackResponseDto {

    private String content;

    @Builder
    public FeedbackResponseDto(String content) {
        this.content = content;
    }

    public static FeedbackResponseDto fromEntity(Feedback feedback) {
        return FeedbackResponseDto.builder()
                .content(feedback.getContent())
                .build();
    }
}
