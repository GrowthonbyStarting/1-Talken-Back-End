package com.example.talken.resume;

import com.example.talken.common.Response;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.resume.dto.response.ResumeDetailResponseDto;
import com.example.talken.resume.dto.response.ResumeListResponseDto;
import com.example.talken.resume.dto.request.ResumeRequestDto;
import com.example.talken.resume.dto.response.ResumeResponseDto;
import com.example.talken.resume.repository.ResumeRepository;
import com.example.talken.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping(value = "/api/resumes", consumes = {"multipart/form-data"})
    public Response<ResumeResponseDto> createResume(@RequestPart(value = "request") ResumeRequestDto request,
                                                    @RequestPart(value = "images", required = false) List<MultipartFile> files,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        resumeService.createResume(request, userDetails, files);

        return Response.<ResumeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/api/resumes")
    public ResponseEntity<Response<ResumeListResponseDto>> readResumeList(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<ResumeListResponseDto>builder()
                        .data(resumeService.readResumeList(userDetails))
                        .build());

    }

    @GetMapping("/api/users/resumes/{resumeId}")
    public ResponseEntity<Response<ResumeDetailResponseDto>> readResume(@PathVariable Long resumeId,
                                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<ResumeDetailResponseDto>builder()
                        .data(resumeService.readResumeByUserId(resumeId, userDetails))
                        .build());
    }

}
