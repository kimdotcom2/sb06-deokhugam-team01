package com.sprint.sb06deokhugamteam01.repository.user;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprint.sb06deokhugamteam01.domain.batch.BatchUserRating;
import com.sprint.sb06deokhugamteam01.domain.batch.PeriodType;
import com.sprint.sb06deokhugamteam01.domain.batch.QBatchUserRating;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Repository
@RequiredArgsConstructor
public class BatchUserRatingRepositoryImpl implements BatchUserRatingRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QBatchUserRating bur = QBatchUserRating.batchUserRating;

    @Override
    public Slice<BatchUserRating> getBatchUserRatingList(String period, String direction, String cursor, LocalDateTime after, Integer limit) {
        PeriodType periodType = PeriodType.valueOf(period.toUpperCase());
        boolean ascending = direction != null && direction.equalsIgnoreCase("ASC");
        int size = limit != null && limit > 0 ? limit : 20;

        LocalDate periodEnd = after != null ? after.toLocalDate() : LocalDate.now();
        LocalDate start = getPeriodStart(periodType, periodEnd);

        var results = queryFactory
            .selectFrom(bur)
            .where(
                bur.periodType.eq(periodType),
                bur.periodStart.eq(start),
                bur.periodEnd.eq(periodEnd),
                cursorCondition(cursor, after, ascending)
            )
            .orderBy(scoreOrder(ascending), idOrder(ascending))
            .limit(size + 1L)
            .fetch();

        boolean hasNext = results.size() > size;
        if (hasNext) {
            results = results.subList(0, size);
        }
        Pageable paging = Pageable.ofSize(size);
        return new SliceImpl<>(results, paging, hasNext);
    }

    private LocalDate getPeriodStart(PeriodType periodType, LocalDate periodEnd) {
        return switch (periodType) {
            case WEEKLY -> periodEnd.minusDays(6);
            case MONTHLY -> periodEnd.minusDays(29);
            case DAILY -> periodEnd;
            case ALL_TIME -> LocalDate.MIN;
        };
    }

    private OrderSpecifier<?> scoreOrder(boolean ascending) {
        Order order = ascending ? Order.ASC : Order.DESC;
        return new OrderSpecifier<>(order, bur.score);
    }

    private OrderSpecifier<?> idOrder(boolean ascending) {
        Order order = ascending ? Order.ASC : Order.DESC;
        return new OrderSpecifier<>(order, bur.id);
    }

    private com.querydsl.core.types.Predicate cursorCondition(String cursor, LocalDateTime after, boolean ascending) {
        if (cursor == null || after == null) {
            return null;
        }
        // cursor는 score 기준이 아니라 UUID 기반 tie-breaker로 사용
        return ascending
                ? bur.createdAt.gt(after).or(bur.createdAt.eq(after).and(bur.id.gt(java.util.UUID.fromString(cursor))))
                : bur.createdAt.lt(after).or(bur.createdAt.eq(after).and(bur.id.lt(java.util.UUID.fromString(cursor))));
    }
}
