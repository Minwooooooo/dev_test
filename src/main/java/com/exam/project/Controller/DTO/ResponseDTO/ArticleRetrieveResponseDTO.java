package com.exam.project.Controller.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ArticleRetrieveResponseDTO {

    private long id;
    private String title;
    private int viewCount;
    private boolean isPinned;
    private String createdDatetime;
    private String board;
    private List<String> image;
}
