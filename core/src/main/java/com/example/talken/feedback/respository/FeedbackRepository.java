package com.example.talken.feedback.respository;

import com.example.talken.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByResumeId(Long resumeId);
}
