package org.koreait.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass // 공통 속성화. 상위 클래스임을 알려주는 어노테이션
@EntityListeners(AuditingEntityListener.class) // 엔티티 변화 감지
public abstract class BaseMemberEntity extends BaseEntity {
    @CreatedBy
    @Column(length = 65, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(length = 65, insertable = false)
    private String modifiedBy;
}
