package com.exam.project;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ArticleRetrieveResponseDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
import com.exam.project.Entity.BoardEntity;
import com.exam.project.Mapper.BoardMapper;
import com.exam.project.Service.ArticleService;
import com.exam.project.Service.SearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleTest {
        private final ArticleService articleService;
        private final SearchService searchService;
        private final BoardMapper boardMapper;

        @Autowired
        public ArticleTest(ArticleService articleService, SearchService searchService, BoardMapper boardMapper) {
            this.articleService = articleService;
            this.searchService = searchService;
            this.boardMapper = boardMapper;
        }
    // ALTER TABLE cms__board AUTO_INCREMENT = 1;
    // ALTER TABLE cms__article AUTO_INCREMENT = 1;

    @Test
    @Order(1)
    @DisplayName("최초 게시판 생성")
    void creatBoard(){
        BoardEntity boardEntity1=new BoardEntity(null,"자유게시판");
        BoardEntity boardEntity2=new BoardEntity(null,"자유게시판2");
        BoardEntity boardEntity3=new BoardEntity(null,"공지사항");
        BoardEntity boardEntity4=new BoardEntity(null,"QnA");

        boardMapper.createBoard(boardEntity1);
        boardMapper.createBoard(boardEntity2);
        boardMapper.createBoard(boardEntity3);
        boardMapper.createBoard(boardEntity4);

    }


    @Test
    @Order(2)
    @DisplayName("게시글 작성")
    void creatArticleTest(){
        ArticleRequestDTO articleRequestDTO1 = new ArticleRequestDTO();
        articleRequestDTO1.setBoardId(1);
        articleRequestDTO1.setTitle("게시글1(사진2장)");
        articleRequestDTO1.setContent("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Content</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p></p>\n" +
                "\n" +
                "    <h1>제목</h1>\n" +
                "    <h1>게시글1(사진2장)</h1>\n" +
                "\n" +
                "    <h1>이미지</h1>\n" +
                "    <img src=\"게시글1_1번사진\"/>\n" +
                "    <img src=\"게시글1_2번사진\"/>\n" +

                "\n" +
                "    <h2>내용</h2>\n" +
                "    <p>게시글1 test</p>\n" +
                "</body>\n" +
                "</html>");

        ArticleRequestDTO articleRequestDTO2 = new ArticleRequestDTO();
        articleRequestDTO2.setBoardId(2);
        articleRequestDTO2.setTitle("게시글2(사진0장)");
        articleRequestDTO2.setContent("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Content</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p></p>\n" +
                "\n" +
                "    <h1>제목</h1>\n" +
                "    <h1>게시글2(사진0장)</h1>\n" +
                "\n" +
                "    <h1>이미지</h1>\n" +

                "\n" +
                "    <h2>내용</h2>\n" +
                "    <p>게시글2 test</p>\n" +
                "</body>\n" +
                "</html>");

        ArticleRequestDTO articleRequestDTO3 = new ArticleRequestDTO();
        articleRequestDTO3.setBoardId(3);
        articleRequestDTO3.setTitle("게시글3(사진3장)");
        articleRequestDTO3.setContent("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Content</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p></p>\n" +
                "\n" +
                "    <h1>제목</h1>\n" +
                "    <h1>게시글3(사진3장)</h1>\n" +
                "\n" +
                "    <h1>이미지</h1>\n" +
                "    <img src=\"게시글3_1번사진\"/>\n" +
                "    <img src=\"게시글3_2번사진\"/>\n" +
                "    <img src=\"게시글3_3번사진\"/>\n" +

                "\n" +
                "    <h2>내용</h2>\n" +
                "    <p>게시글3 test</p>\n" +
                "</body>\n" +
                "</html>");


        articleService.createArticle(articleRequestDTO1);
        articleService.createArticle(articleRequestDTO2);
        articleService.createArticle(articleRequestDTO3);

    }


    @Test
    @Order(3)
    @DisplayName("1번 게시글 상세 조회")
    void retrieveArticleTest1(){
        ResponseDTO responseDTO=articleService.retrieveArticle(1);
        System.out.println("==============================================");
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
        System.out.println("==============================================");
    }

    @Test
    @Order(4)
    @DisplayName("2번 게시글 상세 조회")
    void retrieveArticleTest2(){
        ResponseDTO responseDTO=articleService.retrieveArticle(2);
        System.out.println("==============================================");
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
        System.out.println("==============================================");
    }

    @Test
    @Order(5)
    @DisplayName("게시글 전체 목록 조회")
    void listArticelsTest(){
        System.out.println("==============================================");
        articleService.listArticles();
        System.out.println("==============================================");
    }

    @Test
    void deleteArticleTest(){
        System.out.println("==============================================");
        ResponseDTO responseDTO=articleService.deleteArticle(10);
        if(responseDTO.getSuccess()){
            System.out.println("Test Success");
            System.out.println(responseDTO.getData().toString());
        } else if (!responseDTO.getSuccess()) {
            System.out.println("Test Fail");
            System.out.println(responseDTO.getError().getCode());
            System.out.println(responseDTO.getError().getMessage());
        }
        System.out.println("==============================================");
    }



    @Test
    void searchArticleByBoardNameTest(){
        System.out.println("==============================================");
        searchService.searchArticleByBoardName("자유");
        System.out.println("==============================================");
    }

    @Test
    void searchArticleByDateTest(){
        System.out.println("==============================================");
        searchService.searchArticleByDate("2022-12-15","2022-12-18");
        System.out.println("==============================================");
    }

}
