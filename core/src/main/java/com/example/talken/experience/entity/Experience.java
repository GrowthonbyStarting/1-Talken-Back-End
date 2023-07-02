package com.example.talken.experience.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String work;

    @Column(nullable = false)
    private LocalDate startedAt;

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
