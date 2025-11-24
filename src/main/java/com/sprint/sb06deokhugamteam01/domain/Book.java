package com.sprint.sb06deokhugamteam01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @Column
    private UUID id;

    private String title;

    private String author;

    private String description;

    private String publisher;

    private LocalDateTime publishedDate;

    private String isbn;

    private String thumbnailUrl;

    private int reviewCount;

    private double rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean isActive;

}
