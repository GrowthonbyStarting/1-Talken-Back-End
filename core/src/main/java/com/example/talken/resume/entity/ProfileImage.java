package com.example.talken.resume.entity;

import com.example.talken.image.entity.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ProfileImage implements Persistable<ProfileImageId> {

    @EmbeddedId
    private ProfileImageId id;

    @MapsId("resumeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @MapsId("imageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @CreatedDate
    private LocalDate createdAt;

    @Override
    public boolean isNew() {
        return createdAt == null;
    }
}
