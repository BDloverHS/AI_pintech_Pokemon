package org.port.message.controllers;

import lombok.Data;
import org.port.global.paging.CommonSearch;

import java.util.List;

@Data
public class MessageSearch extends CommonSearch {
    private List<String> sender; // 보낸 쪽 이메일
    private String mode; // receive이거나 값이 없으면 받은 쪽지, send : 보낸 쪽지
}
