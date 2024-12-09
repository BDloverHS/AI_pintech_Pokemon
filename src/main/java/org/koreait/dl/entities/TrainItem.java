package org.koreait.dl.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.koreait.global.entities.BaseEntity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // 빌더 패턴일 때 기본 생성자가 접근 가능해야 하는 경우. NoArgsConstructor와 같이 사용하여야 함.
public class TrainItem extends BaseEntity {
    @Id @Generated
    private Long seq;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int item7;
    private int item8;
    private int item9;
    private int item10;
    private int result; // 무조건 정수 범위.


}
