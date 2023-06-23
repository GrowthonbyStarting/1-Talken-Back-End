package com.example.talken.feedback;

import com.example.talken.common.Response;
import com.example.talken.feedback.dto.FeedbackRequestDto;
import com.example.talken.feedback.dto.FeedbackResponseDto;
import com.example.talken.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/{resumeId}")
    public Response<FeedbackResponseDto> createFeedback(@PathVariable Long resumeId,
                                                        @RequestBody FeedbackRequestDto requestDto) {
        feedbackService.createFeedback(requestDto);

        return Response.<FeedbackResponseDto>builder()
                .code(HttpStatus.OK.value())
                .build();
    }
}
