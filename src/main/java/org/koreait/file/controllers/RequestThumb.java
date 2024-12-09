package org.koreait.file.controllers;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RequestThumb {
    @Id @GeneratedValue
    Long seq;

    private String url; // 원격 이미지 URL
    private int width;
    private int height;
}
