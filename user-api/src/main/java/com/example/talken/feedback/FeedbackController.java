package com.example.talken.feedback;

import com.example.talken.common.Response;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.feedback.dto.request.FeedbackRequestDto;
import com.example.talken.feedback.dto.response.FeedbackListResponseDto;
import com.example.talken.feedback.dto.response.FeedbackResponseDto;
import com.example.talken.feedback.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "feedback", description = "피드백 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(summary = "피드백 작성")
    @Parameter(name = "resumeId", description = "피드백을 작성할 이력서의 id")
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

    @Operation(summary = "이력서별 피드백 조회", description = "이력서 id를 이용하여 피드백 조회")
    @Parameter(name = "resumeId")
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
