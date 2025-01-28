package org.port.member.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.port.member.constants.Authority;

import java.io.Serializable;

@Data
@Entity
@IdClass(AuthoritiesId.class) // 복합키
@NoArgsConstructor
@AllArgsConstructor
public class Authorities implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(length=15)
    private Authority authority;
}
