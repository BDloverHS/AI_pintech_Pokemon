package org.koreait.message.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.koreait.file.entities.FileInfo;

import java.util.List;

@Data
public class RequestMessage {

    private boolean notice; // 공지사항인지 아닌지

    /**
     * 메일을 받는 쪽 이메일
     *
     * 필수가 되는 조건 : 회원이 다른 회원에게 쪽지를 보내는 경우(notice가 false)
     * 필수가 아닌 조건 : 관리자가 공지사항으로 쪽지를 보내는 경우(notice가 true)
     */
    @Email // 필수는 아니더라도 입력되었을 때 형식을 체크하기 위함
    private String email; // 메일을 받는 쪽 이메일

    @NotBlank
    private String gid;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;

    private List<FileInfo> editorImages;
    private List<FileInfo> attachFiles;
}
