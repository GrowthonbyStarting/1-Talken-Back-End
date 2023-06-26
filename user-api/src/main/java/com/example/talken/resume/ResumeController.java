package com.example.talken.resume;

import com.example.talken.common.Response;
import com.example.talken.common.Status;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.resume.dto.ResumeListResponseDto;
import com.example.talken.resume.dto.ResumeRequestDto;
import com.example.talken.resume.dto.ResumeResponseDto;
import com.example.talken.resume.entity.Resume;
import com.example.talken.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("")
    public Response<ResumeResponseDto> createResume(@RequestBody ResumeRequestDto request,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        resumeService.createResume(request, userDetails);

        return Response.<ResumeResponseDto>builder()
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("")
    public ResponseEntity<Response<ResumeListResponseDto>> readResumeList(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(Response.<ResumeListResponseDto>builder()
                        .data(resumeService.readResumeList(userDetails))
                        .build());

    }
}
