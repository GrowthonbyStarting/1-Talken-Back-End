package com.example.talken.resume.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ProfileImageId implements Serializable {

    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "image_id")
    private Long imageId;

    public ProfileImageId(Long resumeId, Long imageId) {
        this.resumeId = resumeId;
        this.imageId = imageId;
    }
}
