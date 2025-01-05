package org.koreait.bestlist.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.koreait.bestlist.entities.Best;
import org.koreait.bestlist.entities.BestId;
import org.koreait.bestlist.entities.QBest;
import org.koreait.bestlist.repositories.BestRepository;
import org.koreait.member.entities.Member;
import org.koreait.member.libs.MemberUtil;
import org.koreait.member.repositories.MemberRepository;
import org.koreait.pokemon.entities.Pokemon;
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
            if (mode.equals("remove")) { // 찜 해제
                BestId bestId = new BestId(seq, member, pokemon);
                repository.deleteById(bestId);
            } else { // 찜 추가
                Best best = new Best();
                best.setSeq(seq);
                best.setBestPokemon(pokemon);
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

        QBest best = QBest.best;
        BooleanBuilder builder = new BooleanBuilder();

        if (repository.count() < 6) {
            builder.and(best.member.eq(memberUtil.getMember())).and(best.bestPokemon.seq.eq(seq));
        }

        List<Pokemon> bests = queryFactory.select(best.bestPokemon)
                .from(best)
                .where(builder)
                .fetch();

        return bests;
    }
}
