package com.exam.project.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArticleEntity {

    // 게시글 PK
    @Id
    private long articleId;

    // 게시판 FK
    @Column
    private long boardId;

    // 생성날짜
    @Column
    private LocalDateTime createdDatetime;

    // 게시판 내 고정여부
    @Column
    private boolean isPinned;

    // 조회수
    @Column
    private int viewCount;

    // 제목
    @Column
    private String title;

    // 본문
    @Column
    private String contentHtml;

    // 본문 내용만 저장, 검색용
    @Column
    private String contentString;

    public void updateViewCount(){
        this.viewCount+=1;
    }

}

