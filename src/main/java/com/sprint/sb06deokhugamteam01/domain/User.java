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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column
    private UUID id;

    private String email;

    private String nickname;
    private String password;

    private LocalDateTime createdAt;
    private boolean isActive;

}
