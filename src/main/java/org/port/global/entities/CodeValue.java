package org.port.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CodeValue {

    @Id
    @Column(name="_CODE", length=45) // db에서 code가 예약어이므로 디비에서의 이름을 지어즘
    private String code;

    @Lob
    @Column(name="_VALUE")
    private String value;
}
