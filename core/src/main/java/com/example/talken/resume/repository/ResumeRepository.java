package com.example.talken.resume.repository;

import com.example.talken.common.Status;
import com.example.talken.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findAllByPublicStatus(Status.Resume publicStatus);
    Optional<Resume> findByUserId(Long userId);
}
