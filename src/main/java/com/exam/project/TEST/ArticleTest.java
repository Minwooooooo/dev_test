package com.exam.project.TEST;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ArticleRetrieveResponseDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
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


    // Green : articleId = 5
    // Red : articleId = 10
    @Test
    void retrieveArticleTest(){
        ResponseDTO responseDTO=articleService.retrieveArticle(5);
        if (responseDTO.getSuccess()){
            System.out.println("Test Success");
            ArticleRetrieveResponseDTO articleRetrieveResponseDTO = (ArticleRetrieveResponseDTO) responseDTO.getData();
            System.out.println("ID : "+articleRetrieveResponseDTO.getId());
            System.out.println("Title : "+articleRetrieveResponseDTO.getTitle());
            System.out.println("View Count : "+articleRetrieveResponseDTO.getViewCount());
            System.out.println("Is Pinned : "+articleRetrieveResponseDTO.isPinned());
            System.out.println("Created Datetime : "+articleRetrieveResponseDTO.getCreatedDatetime());
            System.out.println("Board Name(ko) : "+articleRetrieveResponseDTO.getBoard());
            System.out.println("Images List : "+articleRetrieveResponseDTO.getImage());
        } else if (!responseDTO.getSuccess()) {
            System.out.println("Test Fail");
            System.out.println(responseDTO.getError().getCode());
            System.out.println(responseDTO.getError().getMessage());
        }
    }

}
