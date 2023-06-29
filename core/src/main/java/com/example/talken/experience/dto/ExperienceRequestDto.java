package com.example.talken.experience.dto;

import com.example.talken.experience.entity.Experience;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExperienceRequestDto {

    private String companyName;
    private String work;
    private LocalDate startedAt;
    private LocalDate finishedAt;

    public Experience toEntity() {
        return Experience.builder()
                .companyName(companyName)
                .work(work)
                .startedAt(startedAt)
                .finishedAt(finishedAt)
                .build();
    }
}
