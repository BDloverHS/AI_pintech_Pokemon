package org.port.wishlist.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.port.member.entities.Member;
import org.port.wishlist.constants.WishType;

@Data
@Entity
@IdClass(WishId.class)
public class Wish {
    @Id
    private Long seq;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(length=15, name="_type")
    private WishType type;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; // 로그인 되어있는지 확인하기 위함
}
