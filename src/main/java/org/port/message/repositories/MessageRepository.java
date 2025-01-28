package org.port.message.repositories;

import org.port.message.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MessageRepository extends JpaRepository<Message, Long>, QuerydslPredicateExecutor<Message> {
}
