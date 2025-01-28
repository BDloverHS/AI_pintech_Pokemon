package org.port.bestlist.repositories;

import org.port.bestlist.entities.Best;
import org.port.bestlist.entities.BestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BestRepository extends JpaRepository<Best, BestId>, QuerydslPredicateExecutor<Best> {
}
