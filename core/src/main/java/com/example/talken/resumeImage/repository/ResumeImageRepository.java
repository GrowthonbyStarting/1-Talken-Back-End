package com.example.talken.resumeImage.repository;

import com.example.talken.resumeImage.entity.ResumeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeImageRepository extends JpaRepository<ResumeImage, Long> {
    List<ResumeImage> findByResumeId(Long resumeId);
}
