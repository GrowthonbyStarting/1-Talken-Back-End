package com.example.talken.feedback.dto.request;

import com.example.talken.feedback.entity.Feedback;
import com.example.talken.resume.entity.Resume;
import com.example.talken.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackRequestDto {

    @NotNull
    private String content;

    public Feedback toEntity(User user, Resume resume) {
        return Feedback.builder()
                .content(content)
                .user(user)
                .resume(resume)
                .build();
    }
}
