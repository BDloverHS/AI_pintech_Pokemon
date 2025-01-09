package org.koreait.pokemon.repositories;

import org.koreait.pokemon.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

// QuerydslPredicateExecutor : Querydsl을 사용하여 동적 쿼리를 생성할 수 있도록 도와줌
public interface PokemonRepository extends JpaRepository<Pokemon, Long>, QuerydslPredicateExecutor<Pokemon> {

}
