package org.koreait.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
// 해당 어노테이션 오류 날 시 build.gradle에서 jakarta.persistence-api의 버전을 설정 후 인텔리제이 재구동
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt; // 등록일시

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime ModifiedAt; // 수정일시

    private LocalDateTime deletedAt; // 삭제일시
}