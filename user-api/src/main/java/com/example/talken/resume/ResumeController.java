package com.example.talken.resume;

import com.example.talken.resume.dto.ResumeRequestDto;
import com.example.talken.resume.dto.ResumeResponseDto;
import com.example.talken.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("")
    public ResponseEntity<ResumeResponseDto> createResume(@RequestBody ResumeRequestDto request) {
        return ResponseEntity.ok(resumeService.createResume(request));
    }
}
