package org.koreait.file.services;

import lombok.RequiredArgsConstructor;
import org.koreait.file.controllers.RequestUpload;
import org.koreait.file.entities.FileInfo;
import org.koreait.file.repositories.FileInfoRepository;
import org.koreait.global.configs.FileProperties;
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

    public List<FileInfo> upload(RequestUpload form) {
        String gid = form.getGid();
        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();

        String location = form.getLocation();
        MultipartFile[] files = form.getFiles();

        String rootPath = properties.getPath();
        System.out.println(properties);
        // 파일 업로드 성공 파일 정보
        List<FileInfo> uploadedItems = new ArrayList<>();

        for (MultipartFile file : files) {
            // 1. 파일 업로드 정보 - DB에 기록 S
            // 파일명.확장자
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));

            FileInfo item = new FileInfo();
            item.setGid(gid);
            item.setLocation(location);
            item.setFileName(fileName);
            item.setContentType(file.getContentType());

            fileInfoRepository.saveAndFlush(item);
            // 1. 파일 업로드 정보 - DB에 기록 E

            // 2. 파일 업로드 처리 S
            long seq = item.getSeq();
            String uploadFileName = seq + extension;
            long folder = seq % 10L; // 0~9

            System.out.println("유입1");

            // File dir = new File(rootPath + folder);
            File dir = new File(rootPath + folder);
            System.out.println(rootPath + folder);
            // 디렉토리가 존재하지 않거나 파일로만 있는 경우 생성
            if(!dir.exists() || !dir.isDirectory()) {
                System.out.println("유입2");
                dir.mkdirs();
                System.out.println("유입3");
            }

            File _file = new File(dir, uploadFileName);
            try {
                file.transferTo(_file);


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