package com.exam.project.TEST;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleTest {
        private final ArticleService articleService;

        @Autowired
        public ArticleTest(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Test
    void creatArticleTest(){
        ArticleRequestDTO articleRequestDTO = new ArticleRequestDTO();
        articleRequestDTO.setBoardId(123);
        articleRequestDTO.setTitle("title");
        articleRequestDTO.setContent("content");
        articleService.createArticle(articleRequestDTO);
    }

    @Test
    void retrieveArticleTest(){
        System.out.println(articleService.retrieveArticle(5).toString());
    }

}
