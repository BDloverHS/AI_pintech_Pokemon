package org.koreait.bestlist.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.koreait.member.entities.Member;

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
