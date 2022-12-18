package com.exam.project.Controller.DTO.ResponseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArticlesListDTO {

    private long id;
    private String title;
    private int viewCount;
    private boolean isPinned;
    private String createdDatetime;
    private String image;

}
