package com.example.talken.feedback.dto;

import com.example.talken.feedback.entity.Feedback;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackRequestDto {

    private String content;

    public Feedback toEntity() {
        return Feedback.builder()
                .content(content)
                .build();
    }
}
