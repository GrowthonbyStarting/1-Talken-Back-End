package com.example.talken.feedback;

import com.example.talken.common.Response;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.feedback.dto.request.FeedbackRequestDto;
import com.example.talken.feedback.dto.response.FeedbackListResponseDto;
import com.example.talken.feedback.dto.response.FeedbackResponseDto;
import com.example.talken.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/{resumeId}")
    public ResponseEntity<Response<FeedbackResponseDto>> createFeedback(@PathVariable Long resumeId,
                                                        @RequestBody FeedbackRequestDto requestDto,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        feedbackService.createFeedback(resumeId, requestDto, userDetails);

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<FeedbackResponseDto>builder()
                        .data(feedbackService.createFeedback(resumeId, requestDto, userDetails))
                        .build());
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<FeedbackListResponseDto>> readFeedbackList(@PathVariable Long resumeId,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<FeedbackListResponseDto>builder()
                        .data(feedbackService.readFeedbackList(resumeId, userDetails))
                        .build());
    }
}
