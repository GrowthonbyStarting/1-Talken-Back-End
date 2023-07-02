package com.example.talken.image.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "이미지")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "s3 url")
    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
