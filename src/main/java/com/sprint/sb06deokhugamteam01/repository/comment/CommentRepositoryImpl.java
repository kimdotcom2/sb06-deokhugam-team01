package com.sprint.sb06deokhugamteam01.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprint.sb06deokhugamteam01.domain.Comment;
import com.sprint.sb06deokhugamteam01.domain.QComment;
import com.sprint.sb06deokhugamteam01.dto.comment.request.CommentSearchCondition;
import com.sprint.sb06deokhugamteam01.dto.comment.response.CommentSliceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;


    @Override
    public CommentSliceResult searchComments(CommentSearchCondition condition) {
        List<Comment> comments = queryFactory
                .selectFrom(comment)
                .where()
                .orderBy()
                .limit(condition.limit() + 1)
                .fetch();

        boolean hasNext = false;
        if(comments.size() > condition.limit()) {
            hasNext = true;
            comments.remove(condition.limit());
        }

        Long totalElements = queryFactory
                .select(comment.count())
                .from(comment)
                .where()
                .fetchOne();

        return new CommentSliceResult(comments, hasNext, totalElements);
    }
}
