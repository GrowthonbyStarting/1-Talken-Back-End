package com.example.talken.feedback.service;

import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.feedback.dto.request.FeedbackRequestDto;
import com.example.talken.feedback.dto.response.FeedbackListResponseDto;
import com.example.talken.feedback.dto.response.FeedbackResponseDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;


    public FeedbackResponseDto createFeedback(Long resumeId, FeedbackRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = validateUser(userDetails.getUser());

        Resume resume = resumeRepository.findById(resumeId).orElseThrow(
                () -> new FeedbackException(FeedbackError.RESUME_NOT_FOUND)
        );

        Feedback feedback = requestDto.toEntity(user, resume);
        feedbackRepository.save(feedback);

        return FeedbackResponseDto.fromEntity(feedback);
    }

    public FeedbackListResponseDto readFeedbackList(Long resumeId, UserDetailsImpl userDetails) {
        validateUser(userDetails.getUser());

        resumeRepository.findById(resumeId).orElseThrow(
                () -> new FeedbackException(FeedbackError.RESUME_NOT_FOUND)
        );

        List<Feedback> feedbackList = feedbackRepository.findByResumeId(resumeId);
        List<FeedbackResponseDto> feedbackResponseDtoList = feedbackList.stream()
                .map( feedback -> new FeedbackResponseDto(feedback.getContent()))
                .collect(Collectors.toList());

        return FeedbackListResponseDto.builder()
                .feedbackResponseList(feedbackResponseDtoList)
                .build();
    }

    private User validateUser(User user) {
        return userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new FeedbackException(FeedbackError.INVALID_USER)
        );
    }

}