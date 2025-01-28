package org.port.file.services;

import lombok.RequiredArgsConstructor;
import org.port.file.controllers.RequestUpload;
import org.port.file.entities.FileInfo;
import org.port.file.repositories.FileInfoRepository;
import org.port.global.configs.FileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Lazy
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileUploadService {
    private final FileInfoRepository fileInfoRepository;
    private final FileProperties properties;
    private final FileInfoService infoService;
    private final FileDeleteService deleteService;


    public List<FileInfo> upload(RequestUpload form) {
        String gid = form.getGid();
        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();

        String location = form.getLocation();
        MultipartFile[] files = form.getFiles();

        String rootPath = properties.getPath();
        // System.out.println(properties);

        // 파일 업로드 성공 파일 정보
        List<FileInfo> uploadedItems = new ArrayList<>();

        for (MultipartFile file : files) {
            String contentType = file.getContentType();

            // 이미지 형식의 파일만 허용하는 경우 - 이미지가 아닌 파일은 건너뛰기
            if (form.isImageOnly() && contentType.indexOf("image/") == -1) {
                continue;
            }

            // 1. 파일 업로드 정보 - DB에 기록 S
            // 파일명.확장자
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));

            FileInfo item = new FileInfo();
            item.setGid(gid);
            item.setLocation(location);
            item.setFileName(fileName);
            item.setExtension(extension);
            item.setContentType(contentType);

            fileInfoRepository.saveAndFlush(item);
            // 1. 파일 업로드 정보 - DB에 기록 E

            // 2. 파일 업로드 처리 S
            long seq = item.getSeq();
            String uploadFileName = seq + extension;
            long folder = seq % 10L; // 0~9

            File dir = new File(rootPath + folder);

            // 디렉토리가 존재하지 않거나 파일로만 있는 경우 생성
            if(!dir.exists() || !dir.isDirectory()) {
                dir.mkdirs();
            }

            File _file = new File(dir, uploadFileName);

            try {
                file.transferTo(_file); // 오류 시 DB엔 저장은 되지만 파일은 저장되지 않음(오류 시 catch문으로 가 DB에서도 삭제가 됨)

                // 추가 정보 처리
                infoService.addInfo(item);

                uploadedItems.add(item);
            } catch (IOException e) {
                // 파일 업로드 실패 -> DB에 저장된 데이터를 삭제
                fileInfoRepository.delete(item);
                fileInfoRepository.flush();
            }
            // 2. 파일 업로드 처리 E
        }

        return uploadedItems;
    }
}
