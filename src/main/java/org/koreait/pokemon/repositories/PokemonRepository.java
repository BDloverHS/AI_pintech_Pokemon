package org.koreait.pokemon.repositories;

import com.querydsl.core.types.dsl.NumberPath;
import org.koreait.pokemon.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

// QuerydslPredicateExecutor : 데이터가 많을 때
public interface PokemonRepository extends JpaRepository<Pokemon, Long>, QuerydslPredicateExecutor<Pokemon> {

    Optional<Pokemon> findById(NumberPath<Long> seq);
}
