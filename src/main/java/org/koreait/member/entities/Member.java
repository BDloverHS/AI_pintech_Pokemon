package org.koreait.member.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.koreait.global.entities.BaseEntity;

import java.time.LocalDate;

@Data
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long seq; // 회원 번호

    @Column(length = 65, nullable = false, unique = true)
    private String email;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String nickName;

    @Column(nullable = false)
    private LocalDate birthDt;
}
