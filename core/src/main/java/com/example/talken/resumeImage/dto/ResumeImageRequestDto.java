package com.example.talken.resumeImage.dto;

import com.example.talken.image.entity.Image;
import com.example.talken.resume.entity.Resume;
import com.example.talken.resumeImage.entity.ResumeImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResumeImageRequestDto {

    public ResumeImage toEntity(Resume resume, Image image) {
        return ResumeImage.builder()
                .resume(resume)
                .image(image)
                .build();
    }
}
