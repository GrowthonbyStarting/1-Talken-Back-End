package com.example.talken.image.dto;

import com.example.talken.image.entity.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageRequestDto {
    private String url;

    public Image toEntity() {
        return Image.builder()
                .imageUrl(url)
                .build();
    }
}
