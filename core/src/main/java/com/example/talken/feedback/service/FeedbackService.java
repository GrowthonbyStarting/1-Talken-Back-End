package com.example.talken.feedback.service;

import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.feedback.dto.FeedbackRequestDto;
import com.example.talken.feedback.dto.FeedbackResponseDto;
import com.example.talken.feedback.entity.Feedback;
import com.example.talken.feedback.exception.FeedbackError;
import com.example.talken.feedback.exception.FeedbackException;
import com.example.talken.feedback.respository.FeedbackRepository;
import com.example.talken.resume.entity.Resume;
import com.example.talken.resume.repository.ResumeRepository;
import com.example.talken.user.entity.User;
import com.example.talken.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;


    public FeedbackResponseDto createFeedback(Long resumeId, FeedbackRequestDto requestDto, UserDetailsImpl userDetails) {
        String email = userDetails.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new FeedbackException(FeedbackError.INVALID_USER)
        );

        Resume resume = resumeRepository.findById(resumeId).orElseThrow(
                () -> new FeedbackException(FeedbackError.RESUME_NOT_FOUND)
        );

        Feedback feedback = feedbackRepository.save(requestDto.toEntity(user, resume));

        return FeedbackResponseDto.fromEntity(feedback);
    }
}