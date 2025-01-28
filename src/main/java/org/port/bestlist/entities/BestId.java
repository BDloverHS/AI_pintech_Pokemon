package org.port.bestlist.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.port.member.entities.Member;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BestId {
    private Long seq;
    private Member member;
}
