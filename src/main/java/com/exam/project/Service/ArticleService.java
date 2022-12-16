package com.exam.project.Service;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ArticleRetrieveResponseDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
import com.exam.project.Entity.ArticleEntity;
import com.exam.project.Mapper.ArticleMapper;
import com.exam.project.Mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ArticleService {
    private final ArticleMapper articleMapper;
    private final BoardMapper boardMapper;

    @Autowired
    public ArticleService(ArticleMapper articleMapper, BoardMapper boardMapper) {
        this.articleMapper = articleMapper;
        this.boardMapper = boardMapper;
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
    @Transactional
    public ResponseDTO<ArticleRetrieveResponseDTO> retrieveArticle(long articleId){

        try {

            HashMap tempDTO=articleMapper.retrieveArticle(articleId);
            if(tempDTO==null){
                System.out.println("tempDTO==null");
                throw new NullArticleException();
            }

            // View Count 증가
            articleMapper.viewCountUp(articleId);

            int newViewCount= Integer.parseInt(tempDTO.get("view_count").toString())+1;
            tempDTO.replace("view_count",newViewCount);


            ArticleRetrieveResponseDTO articleRetrieveResponseDTO=responseMapping(tempDTO);

            return ResponseDTO.success(articleRetrieveResponseDTO);
        }
        catch (NullArticleException e){
            return ResponseDTO.fail("None Exist Article","해당 ID에 존재하는 게시글이 없습니다.");
        }




    }

    public ArticleRetrieveResponseDTO responseMapping(HashMap hashMap){

        // 게시판이름 조회 by BoardId
        String board = boardMapper.boardIdToName(Long.parseLong(hashMap.get("board_id").toString()));

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

    static class NullArticleException extends RuntimeException{
    }
}
