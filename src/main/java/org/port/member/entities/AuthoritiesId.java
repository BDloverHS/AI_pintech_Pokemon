package org.port.member.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.port.member.constants.Authority;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AuthoritiesId {
    private Member member;
    private Authority authority;
}
