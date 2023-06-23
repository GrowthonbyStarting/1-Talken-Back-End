package com.example.talken.feedback;

import com.example.talken.common.Response;
import com.example.talken.feedback.dto.FeedbackRequestDto;
import com.example.talken.feedback.dto.FeedbackResponseDto;
import com.example.talken.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("")
    public Response<FeedbackResponseDto> createFeedback(@RequestBody FeedbackRequestDto requestDto) {
        feedbackService.createFeedback(requestDto);

        return Response.<FeedbackResponseDto>builder()
                .code(HttpStatus.OK.value())
                .build();
    }
}
