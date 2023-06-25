package com.example.talken.resume.service;

import com.example.talken.common.Status;
import com.example.talken.resume.dto.ResumeRequestDto;
import com.example.talken.resume.dto.ResumeResponseDto;
import com.example.talken.resume.entity.Resume;
import com.example.talken.resume.repository.ResumeRepository;
import com.example.talken.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private static Status.Resume publicStatus = Status.Resume.PUBLIC;
    private static Status.Feedback feedbackStatus = Status.Feedback.WANTED;


    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeResponseDto createResume(ResumeRequestDto request) {
//        userRepository.findByEmail(user.getEmail())
//                .orElseThrow(() -> new ResumeException(ResumeError.INVALID_USER));

        Resume resume = resumeRepository.save(request.toEntity(publicStatus, feedbackStatus));

        return ResumeResponseDto.fromEntity(resume);
    }
}
