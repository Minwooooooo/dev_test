package com.exam.project.Controller.DTO.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDTO {

    // 게시판 FK
    private int boardId;

    // 제목
    private String title;

    // 게시글
    private String content;
}
