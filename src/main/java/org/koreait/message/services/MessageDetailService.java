package org.koreait.message.services;

import lombok.RequiredArgsConstructor;
import org.koreait.global.exceptions.UnAuthorizedException;
import org.koreait.member.libs.MemberUtil;
import org.koreait.message.entities.Message;
import org.koreait.message.repositories.MessageRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Lazy
@Service
@RequiredArgsConstructor
public class MessageDetailService {
    private final MessageInfoService infoService;
    private final MessageRepository repository;
    private final MemberUtil memberUtil;

    /**
     * 삭제 처리
     * 1. sender 쪽에서 삭제하는 경우 / mode - send
     *      deleteBySender 값을 true
     *
     * 2. receiver 쪽에서 삭제하는 경우 / mode - receive
     *      deletedByReceiver 값을 true
     *
     * 3. deletedBySender와 deletedByReceiver가 모두 true인 경우 실제 DB에서도 삭제
     *    (Message 쪽 삭제, 파일 데이터 함께 삭제)
     *
     *
     *
     * @param seq
     * @param mode
     */
    public void process(Long seq, String mode) {
        mode = StringUtils.hasText(mode) ? mode : "receive";

        Message item = infoService.get(seq);

        boolean isProceedDelete = false;

        if (item.isNotice()) {
            if (memberUtil.isAdmin()) { // 삭제 처리
                isProceedDelete = true;
            } else { // 공지이지만 관리자가 아닌 경우 - 권한 없음
                throw new UnAuthorizedException();
            }
        } // endif
    }
}
