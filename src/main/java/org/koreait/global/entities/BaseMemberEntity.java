package org.koreait.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
// 엔티티 중에서 공유하는 공통 속성화로 사용할 상위클래스라는 것을 알려줌
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 엔티티 변화 감지
public abstract class BaseMemberEntity extends BaseEntity {
    @CreatedBy
    @Column(length = 65, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(length = 65, insertable = false)
    private String modifiedBy;
}
