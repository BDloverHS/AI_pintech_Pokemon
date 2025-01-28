package org.port.wishlist.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.port.member.entities.Member;
import org.port.wishlist.constants.WishType;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WishId {
    private Long seq;
    private WishType type;
    private Member member;
}
