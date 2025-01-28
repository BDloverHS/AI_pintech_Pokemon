package org.port.bestlist.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.port.member.entities.Member;

@Data
@Entity
@IdClass(BestId.class)
public class Best {
    @Id
    private Long seq;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
