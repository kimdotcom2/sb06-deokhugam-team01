package com.sprint.sb06deokhugamteam01.dto.User.request;

import java.time.LocalDateTime;
import lombok.Builder;

public record PowerUserRequest(
    String period,
    String direction,
    String cursor,
    LocalDateTime after,
    Integer limit
) {

    @Builder
    public PowerUserRequest(String period, String direction, String cursor, LocalDateTime after, Integer limit) {
        this.period = period;
        this.direction = direction;
        this.cursor = cursor;
        this.after = after;
        this.limit = limit;
    }

}
