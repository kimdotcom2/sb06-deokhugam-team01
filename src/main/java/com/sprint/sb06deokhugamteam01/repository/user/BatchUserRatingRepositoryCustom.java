package com.sprint.sb06deokhugamteam01.repository.user;

import com.sprint.sb06deokhugamteam01.domain.batch.BatchUserRating;
import java.time.LocalDateTime;
import org.springframework.data.domain.Slice;

public interface BatchUserRatingRepositoryCustom {

    Slice<BatchUserRating> getBatchUserRatingList(String period, String direction, String cursor, LocalDateTime after, Integer limit);

}
