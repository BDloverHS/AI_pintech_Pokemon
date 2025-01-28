package org.port.file.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestUpload {
    @NotBlank
    private String gid;
    private String location;

    private boolean single; // 단일 파일인가?(단일 파일이면 true)
    private boolean imageOnly; // 파일이 이미지 형식인가?(이미지일 때만 true)

    private boolean done; // 업로드 하자마자 완료 처리

    public MultipartFile[] files;
}
