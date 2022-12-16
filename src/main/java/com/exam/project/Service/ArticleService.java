package com.exam.project.Service;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ArticleRetrieveResponseDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
import com.exam.project.Entity.ArticleEntity;
import com.exam.project.Mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ArticleService {
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    //Create : title, content를 입력으로 받아 생성, content_html, content_string 둘다 content 받아서 넣는다.
    public ResponseDTO<?> createArticle(ArticleRequestDTO articleDTO){
        LocalDateTime localDateTime = LocalDateTime.now();
        ArticleEntity articleEntity= ArticleEntity.builder()
                .boardId(articleDTO.getBoardId())
                .createdDatetime(localDateTime)
                .isPinned(false)
                .viewCount(0)
                .title(articleDTO.getTitle())
                .contentHtml("Html")
                .contentString("String")
                .build();
        articleMapper.createArticle(articleEntity);

        return ResponseDTO.success("success");
    }

    //Retrieve : id, title, content_html, view_count, is_pinned, created_datetime, 같은 게시글의 모든 이미지, 게시판 명 (board->name_ko)
    public ResponseDTO<ArticleRetrieveResponseDTO> retrieveArticle(long articleId){
        ArticleRetrieveResponseDTO articleRetrieveResponseDTO=responseMapping(articleMapper.retrieveArticle(articleId));

        System.out.println(articleRetrieveResponseDTO.getId());
        System.out.println(articleRetrieveResponseDTO.getTitle());
        System.out.println(articleRetrieveResponseDTO.getViewCount());
        System.out.println(articleRetrieveResponseDTO.isPinned());
        System.out.println(articleRetrieveResponseDTO.getCreatedDatetime());
        System.out.println(articleRetrieveResponseDTO.getBoard());
        System.out.println(articleRetrieveResponseDTO.getImage());

        return ResponseDTO.success(articleRetrieveResponseDTO);

    }

    public ArticleRetrieveResponseDTO responseMapping(HashMap hashMap){

        // 게시판이름 조회 by BoardId
        String board = "boardEX";

        // 이미지 List화
        List<String> images = new ArrayList<>();
        images.add("imageEX");


        ArticleRetrieveResponseDTO responseDTO = ArticleRetrieveResponseDTO.builder()
                .id(Long.parseLong(hashMap.get("article_id").toString()))
                .title(hashMap.get("title").toString())
                .viewCount(Integer.parseInt(hashMap.get("view_count").toString()))
                .createdDatetime(hashMap.get("created_datetime").toString())
                .board(board)
                .image(images)
                .build();
        return responseDTO;
    }
}
