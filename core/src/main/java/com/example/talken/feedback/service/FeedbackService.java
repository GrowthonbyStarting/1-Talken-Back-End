package com.example.talken.feedback.service;

import com.example.talken.feedback.dto.FeedbackRequestDto;
import com.example.talken.feedback.dto.FeedbackResponseDto;
import com.example.talken.feedback.entity.Feedback;
import com.example.talken.feedback.respository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;


    public FeedbackResponseDto createFeedback(FeedbackRequestDto requestDto) {
        Feedback feedback = feedbackRepository.save(requestDto.toEntity());

        return FeedbackResponseDto.fromEntity(feedback);
    }
}
