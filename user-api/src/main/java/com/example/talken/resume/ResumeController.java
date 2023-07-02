package com.example.talken.resume;

import com.example.talken.common.Response;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.resume.dto.request.ResumeRequestDto;
import com.example.talken.resume.dto.response.ResumeDetailResponseDto;
import com.example.talken.resume.dto.response.ResumeListResponseDto;
import com.example.talken.resume.dto.response.ResumeResponseDto;
import com.example.talken.resume.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "resume", description = "이력서 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @Operation(summary = "이력서 작성")
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public Response<ResumeResponseDto> createResume(@RequestPart(value = "request") ResumeRequestDto resumeRequest,
                                                    @RequestPart(value = "images", required = false) List<MultipartFile> files,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        resumeService.createResume(resumeRequest, userDetails, files);

        return Response.<ResumeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @Operation(summary = "이력서 리스트 조회", description = "publicStatus + feedbackStatus를 이용하여 이력서 조회")
    @GetMapping("")
    public ResponseEntity<Response<ResumeListResponseDto>> readResumeList(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<ResumeListResponseDto>builder()
                        .data(resumeService.readResumeList(userDetails))
                        .build());

    }

    @Operation(summary = "이력서 상세 조회", description = "id를 이용하여 이력서 조회")
    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<ResumeDetailResponseDto>> readResume(@Parameter(name = "resumeId", in = ParameterIn.PATH) @PathVariable Long resumeId,
                                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<ResumeDetailResponseDto>builder()
                        .data(resumeService.readResumeByUserId(resumeId, userDetails))
                        .build());
    }

    @Operation(summary = "이력서 수정")
    @Parameter(name = "resumeId", description = "수정할 이력서의 id")
    @PutMapping("/{resumeId}")
    public ResponseEntity<Response<ResumeResponseDto>> updateResume(@Parameter(name = "resumeId", in = ParameterIn.PATH) @PathVariable Long resumeId,
                                                                    @RequestBody ResumeRequestDto resumeRequest,
                                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<ResumeResponseDto>builder()
                        .data(resumeService.updateResume(resumeId, resumeRequest, userDetails))
                        .build());
    }

}
