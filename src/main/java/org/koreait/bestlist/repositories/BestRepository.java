package org.koreait.bestlist.repositories;

import org.koreait.bestlist.entities.Best;
import org.koreait.bestlist.entities.BestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BestRepository extends JpaRepository<Best, BestId>, QuerydslPredicateExecutor<Best> {
}
