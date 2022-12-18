package com.exam.project.Mapper;

import com.exam.project.Controller.DTO.ResponseDTO.ArticlesListDTO;
import com.exam.project.Entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    void createArticle(ArticleEntity articleEntity);

    HashMap retrieveArticle(long articleId);

    void viewCountUp(long articleId);

    int deleteArticle(long articleId);

    List<ArticlesListDTO> listArticles();

    List<ArticlesListDTO> searchArticleByBoardName(String boardName);

    List<ArticlesListDTO> searchArticleByDate(Map<String, LocalDate> dateMap);
}
