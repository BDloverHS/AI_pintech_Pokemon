package org.port.wishlist.repositories;


import org.port.wishlist.entities.Wish;
import org.port.wishlist.entities.WishId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WishRepository extends JpaRepository<Wish, WishId>, QuerydslPredicateExecutor<Wish> {

}
