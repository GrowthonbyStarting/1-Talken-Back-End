package com.example.talken.resume.service;

import com.example.talken.common.Status;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.resume.dto.ResumeListResponseDto;
import com.example.talken.resume.dto.ResumeRequestDto;
import com.example.talken.resume.dto.ResumeResponseDto;
import com.example.talken.resume.entity.Resume;
import com.example.talken.resume.exception.ResumeError;
import com.example.talken.resume.exception.ResumeException;
import com.example.talken.resume.repository.ResumeRepository;
import com.example.talken.user.entity.User;
import com.example.talken.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private static Status.Resume publicStatus = Status.Resume.PUBLIC;
    private static Status.Feedback feedbackStatus = Status.Feedback.WANTED;


    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeResponseDto createResume(ResumeRequestDto request, UserDetailsImpl userDetails) {
        User user = validateUser(userDetails);

        Resume resume = request.toEntity(publicStatus, feedbackStatus, user);
        resumeRepository.save(resume);

        return ResumeResponseDto.fromEntity(resume);
    }

    public ResumeListResponseDto readResumeList(UserDetailsImpl userDetails) {
        validateUser(userDetails);

        Status.Resume publicStatus = Status.Resume.PUBLIC;
        List<Resume> resumeList = resumeRepository.findAllByPublicStatus(publicStatus);

        List<ResumeResponseDto> resumeResponseList = new ArrayList<>();
        for(Resume resume : resumeList) {
            resumeResponseList.add(ResumeResponseDto.fromEntity(resume));
        }

        return ResumeListResponseDto.builder()
                .resumeResponseList(resumeResponseList)
                .build();
    }

    private User validateUser(UserDetailsImpl userDetails) {
        String email = userDetails.getUser().getEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResumeException(ResumeError.INVALID_USER));
    }

}
