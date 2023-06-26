package com.example.talken.feedback.dto;

import com.example.talken.feedback.entity.Feedback;
import com.example.talken.resume.entity.Resume;
import com.example.talken.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackRequestDto {

    private String content;

    public Feedback toEntity(User user, Resume resume) {
        return Feedback.builder()
                .content(content)
                .user(user)
                .resume(resume)
                .build();
    }
}
