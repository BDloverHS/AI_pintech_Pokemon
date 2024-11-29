package org.koreait.member.repositories;

import org.koreait.member.entities.Authorities;
import org.koreait.member.entities.AuthoritiesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthoritiesId> {

}
