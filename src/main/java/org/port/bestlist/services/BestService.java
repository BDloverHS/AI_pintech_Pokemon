package org.port.bestlist.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.port.bestlist.entities.Best;
import org.port.bestlist.entities.BestId;
import org.port.bestlist.entities.QBest;
import org.port.bestlist.repositories.BestRepository;
import org.port.member.entities.Member;
import org.port.member.libs.MemberUtil;
import org.port.member.repositories.MemberRepository;
import org.port.pokemon.entities.Pokemon;
import org.port.pokemon.entities.QPokemon;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;

@Lazy
@Service
@Transactional
@RequiredArgsConstructor
public class BestService {
    private final MemberUtil memberUtil;
    private final BestRepository repository;
    private final JPAQueryFactory queryFactory;
    private final MemberRepository memberRepository;
    private final SpringTemplateEngine templateEngine;

    public void process(String mode, Long seq, Pokemon pokemon) {
        if (!memberUtil.isLogin()) {
            return;
        }
        mode = StringUtils.hasText(mode) ? mode : "add";
        Member member = memberUtil.getMember();
        member = memberRepository.findByEmail(member.getEmail()).orElse(null);

        try {
            if (mode.equals("remove")) {
                BestId bestId = new BestId(seq, member);
                repository.deleteById(bestId);
            } else {
                Best best = new Best();
                best.setSeq(seq);
                best.setMember(member);
                repository.save(best);
            }

            repository.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public List<Pokemon> getBest(Long seq) {
        if (!memberUtil.isLogin()) {
            return List.of();
        }

        QPokemon pokemon = QPokemon.pokemon;
        QBest best = QBest.best;
        BooleanBuilder builder = new BooleanBuilder();

        if (repository.count() < 6) {
            builder.and(best.member.eq(memberUtil.getMember())).and(pokemon.seq.eq(seq));
        }

        /*List<Pokemon> bests = queryFactory.select(pokemon.seq.eq(seq))
                .from(best)
                .where(builder)
                .fetch();*/

        return null;
    }
}
