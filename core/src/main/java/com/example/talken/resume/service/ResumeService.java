package com.example.talken.resume.service;

import com.example.talken.common.Status;
import com.example.talken.common.security.UserDetailsImpl;
import com.example.talken.common.util.S3Uploader;
import com.example.talken.image.entity.Image;
import com.example.talken.image.repository.ImageRepository;
import com.example.talken.resume.dto.response.ResumeDetailResponseDto;
import com.example.talken.resume.dto.response.ResumeListResponseDto;
import com.example.talken.resume.dto.request.ResumeRequestDto;
import com.example.talken.resume.dto.response.ResumeResponseDto;
import com.example.talken.resume.entity.Resume;
import com.example.talken.resume.exception.ResumeError;
import com.example.talken.resume.exception.ResumeException;
import com.example.talken.resume.repository.ResumeRepository;
import com.example.talken.resumeImage.entity.ResumeImage;
import com.example.talken.resumeImage.repository.ResumeImageRepository;
import com.example.talken.user.entity.User;
import com.example.talken.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private static Status.Resume publicStatus = Status.Resume.PUBLIC;
    private static Status.Feedback feedbackStatus = Status.Feedback.WANTED;
    private final String dirName = "dev/resume";

    private final S3Uploader s3Uploader;

    private final ResumeRepository resumeRepository;
    private final ResumeImageRepository resumeImageRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public ResumeResponseDto createResume(ResumeRequestDto request, UserDetailsImpl userDetails, List<MultipartFile> files) {
        User user = validateUser(userDetails.getUser());

        Resume resume = request.toEntity(publicStatus, feedbackStatus, user);
        resumeRepository.save(resume);

        if(files != null) {
            uploadResumeImage(resume, files);
        }

        return ResumeResponseDto.fromEntity(resume);
    }

    public ResumeListResponseDto readResumeList(UserDetailsImpl userDetails) {
        validateUser(userDetails.getUser());

        List<Resume> resumeList = resumeRepository.findAllByPublicStatus(publicStatus);

        List<ResumeResponseDto> resumeResponseList = new ArrayList<>();
        for(Resume resume : resumeList) {
            resumeResponseList.add(ResumeResponseDto.fromEntity(resume));
        }

        return ResumeListResponseDto.builder()
                .resumeResponseList(resumeResponseList)
                .build();
    }

    public ResumeDetailResponseDto readResumeByUserId(Long resumeId, UserDetailsImpl userDetails) {
        validateUser(userDetails.getUser());

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeException(ResumeError.RESUME_NOT_FOUND));
        ResumeResponseDto resumeResponse = ResumeResponseDto.fromEntity(resume);

        List<ResumeImage> imageList = resumeImageRepository.findByResumeId(resume.getId());
        List<String> imageUrls = new ArrayList<>();

        for(ResumeImage resumeImage : imageList) {
            imageUrls.add(resumeImage.getImage().getImageUrl());
        }

        return ResumeDetailResponseDto.builder()
                .resumeResponse(resumeResponse)
                .imageUrls(imageUrls)
                .build();
    }

    private User validateUser(User user) {
        return userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResumeException(ResumeError.INVALID_USER));
    }

    private void uploadResumeImage(Resume resume, List<MultipartFile> files) {
        List<String> imageUrlList = s3Uploader.uploadFiles(files, dirName);
        List<Image> newImageList = imageUrlList.stream()
                .map(url -> new Image(url))
                .collect(Collectors.toList());

        for(Image image : newImageList) {
            imageRepository.save(image);
            ResumeImage resumeImage = ResumeImage.builder()
                    .resume(resume)
                    .image(image)
                    .build();

            resumeImageRepository.save(resumeImage);
        }
    }

}
