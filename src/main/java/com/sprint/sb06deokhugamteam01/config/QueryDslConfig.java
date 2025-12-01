package com.sprint.sb06deokhugamteam01.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprint.sb06deokhugamteam01.Sb06DeokhugamTeam01Application;
import io.github.openfeign.querydsl.jpa.spring.repository.config.EnableQuerydslRepositories;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableQuerydslRepositories(basePackageClasses = Sb06DeokhugamTeam01Application.class)
public class QueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
