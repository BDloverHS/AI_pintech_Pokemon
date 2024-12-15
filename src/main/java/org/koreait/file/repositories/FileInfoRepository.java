package org.koreait.file.repositories;

import org.koreait.file.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
//                                                                         ┌> 2개 이상의 판별식이 필요할 때 사용
public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QuerydslPredicateExecutor<FileInfo> {



}
