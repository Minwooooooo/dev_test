package com.exam.project.Service;

import com.exam.project.Controller.DTO.ResponseDTO.ArticlesListDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
import com.exam.project.Mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private final ArticleMapper articleMapper;

    public SearchService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }


    // 게시판 이름으로 걸리는 모든 게시글 검색.(부분 검색 가능해야함)
    // 예시) '자유' 로 검색 시 자유라는 이름을 갖고 있는 모든 게시판의 게시글이 검색되어야 한다.
    public ResponseDTO<List> searchArticleByBoardName(String boardName){
        List<ArticlesListDTO> articlesListByBoardName = articleMapper.searchArticleByBoardName(boardName);
        if(articlesListByBoardName.size()==0){
            return ResponseDTO.fail("None Exist Article","해당 게시판에 작성된 게시글이 없습니다.");
        }else {
            return ResponseDTO.success(articlesListByBoardName);
        }
    }

    // 특정기간 내 모든 게시판의 게시글 검색.
    // 예시) 2022-01-01 ~ 2022-02-01로 검색 시 1월안에 있었던 모든 게시글이 검색되어야 한다.
    public ResponseDTO<List> searchArticleByDate(String startDate,String endDate){

        Map<String,LocalDate> dateMap=new HashMap<>();

        // String to LocalDate
        LocalDate sDate = LocalDate.parse(startDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate eDate = LocalDate.parse(endDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateMap.put("startDate",sDate);
        dateMap.put("endDate",eDate);

        List<ArticlesListDTO> articlesListByDate = articleMapper.searchArticleByDate(dateMap);
        if(articlesListByDate.size()==0){
            return ResponseDTO.fail("None Exist Article","해당 기간에 작성된 게시글이 없습니다.");
        }else {
            return ResponseDTO.success(articlesListByDate);
        }
    }



}
