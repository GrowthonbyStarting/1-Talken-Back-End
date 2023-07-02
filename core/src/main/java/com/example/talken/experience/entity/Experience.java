package com.example.talken.experience.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DecimalStyle;

@Schema(description = "업무 경력")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "근무한 회사 이름")
    @Column(nullable = false)
    private String companyName;

    @Schema(description = "담당 직무")
    @Column(nullable = false)
    private String work;

    @Schema(description = "근무 시작 날짜")
    @Column(nullable = false)
    private LocalDate startedAt;

    @Schema(description = "근무 종료 날짜")
    @Column(nullable = false)
    private LocalDate finishedAt;

    @Builder
    public Experience(String companyName, String work, LocalDate startedAt, LocalDate finishedAt) {
        this.companyName = companyName;
        this.work = work;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

}
