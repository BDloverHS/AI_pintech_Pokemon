package org.port.board.services.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.port.board.controllers.RequestComment;
import org.port.board.entities.BoardData;
import org.port.board.entities.CommentData;
import org.port.board.entities.QCommentData;
import org.port.board.exceptions.CommentNotFoundException;
import org.port.board.repositories.CommentDataRepository;
import org.port.member.entities.Member;
import org.port.member.libs.MemberUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Lazy
@Service
@RequiredArgsConstructor
public class CommentInfoService {
    private final CommentDataRepository commentDataRepository;
    private final ModelMapper modelMapper;
    private final JPAQueryFactory queryFactory;
    private final MemberUtil memberUtil;

    /**
     * 댓글 한 개 조회
     *
     * @param seq
     * @return
     */
    public CommentData get(Long seq) {
        CommentData item = commentDataRepository.findById(seq).orElseThrow(CommentNotFoundException::new);

        return item;
    }

    public RequestComment getForm(Long seq) {
        CommentData item = get(seq);
        BoardData data = item.getData();
        RequestComment form = modelMapper.map(item, RequestComment.class);
        form.setMode("edit");
        form.setBoardDataSeq(data.getSeq());

        return form;
    }

    /**
     * 게시글 번호로 작성된 댓글 목록 조회
     *
     * @param seq
     * @return
     */
    public List<CommentData> getList(Long seq) {
        QCommentData commentData = QCommentData.commentData;

        List<CommentData> items = queryFactory.selectFrom(commentData)
                .leftJoin(commentData.member)
                .fetchJoin()
                .where(commentData.data.seq.eq(seq))
                .orderBy(commentData.createdAt.asc())
                .fetch();

        items.forEach(this::addInfo); // 추가 데이터 처리

        return items;
    }

    // 추가 데이터 처리
    private void addInfo(CommentData item) {
        Member member = memberUtil.getMember();
        Member commentMember = item.getMember();

        boolean editable = memberUtil.isAdmin() || item.getMember() == null || (item.getMember() != null && memberUtil.isLogin() && member.getEmail().equals(commentMember.getEmail()));

        item.setEditable(editable);
    }
}
