package com.example.talken.resumeImage.entity;

import com.example.talken.image.entity.Image;
import com.example.talken.resume.entity.Resume;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResumeImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public ResumeImage(Resume resume, Image image) {
        this.resume = resume;
        this.image = image;
    }
}
