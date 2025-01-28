package org.port.message.services;

import lombok.RequiredArgsConstructor;
import org.port.file.services.FileDoneService;
import org.port.member.entities.Member;
import org.port.member.libs.MemberUtil;
import org.port.member.repositories.MemberRepository;
import org.port.message.constants.MessageStatus;
import org.port.message.controllers.RequestMessage;
import org.port.message.entities.Message;
import org.port.message.repositories.MessageRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
public class MessageSendService {
    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;
    private final MessageRepository repository;
    private final FileDoneService fileDoneService;

    public Message process(RequestMessage form) {
        String email = form.getEmail();
        Member receiver = !form.isNotice() ? memberRepository.findByEmail(email).orElse(null) : null;

        Message message = Message.builder()
                .gid(form.getGid())
                .notice(form.isNotice())
                .subject(form.getSubject())
                .content(form.getContent())
                .sender(memberUtil.getMember())
                .receiver(receiver)
                .status(MessageStatus.UNREAD)
                .build();

        repository.saveAndFlush(message);
        fileDoneService.process(form.getGid()); // 파일 업로드 완료 처리

        return message;
    }
}
