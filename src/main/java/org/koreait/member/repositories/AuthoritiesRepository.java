package org.koreait.member.repositories;

import org.koreait.member.entities.Authorities;
import org.koreait.member.entities.AuthoritiesId;
import org.koreait.member.entities.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthoritiesId>, QuerydslPredicateExecutor<Authorities> {
    // Query Method
    // 권한(Authority)은 @OneToMany라서 지연 로딩 상태지만
    // finByEmail 메서드 사용시에는 즉시 로딩되도록
    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findByEmail(String email);
}
