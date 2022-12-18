package com.exam.project.Controller;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
import com.exam.project.Service.ArticleService;
import com.exam.project.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    private final ArticleService articleService;
    private final SearchService searchService;

    @Autowired
    public ArticleController(ArticleService articleService, SearchService searchService) {
        this.articleService = articleService;
        this.searchService = searchService;
    }

    @PostMapping(value="/")
    public ResponseDTO<?> createArticle(@RequestBody ArticleRequestDTO articleRequestDTO){
        return articleService.createArticle(articleRequestDTO);
    }

    @GetMapping(value = "/{articleId}")
    public ResponseDTO<?> retrieveArticle(@PathVariable long articleId){
        return articleService.retrieveArticle(articleId);
    }

    @GetMapping(value = "/list")
    public ResponseDTO<?> listArticles(){
        return articleService.listArticles();
    }

    @DeleteMapping(value = "/{articleId}")
    public ResponseDTO<?> deleteArticle(@PathVariable long articleId){
        return articleService.deleteArticle(articleId);
    }

    @GetMapping(value = "/search")
    public ResponseDTO<?> searchArticleByBoardName(@RequestParam String boardName){
        return searchService.searchArticleByBoardName(boardName);
    }

    @GetMapping(value = "/period")
    public ResponseDTO<?> searchArticleByDate(@RequestParam String startDate,@RequestParam String endDate){
        return searchService.searchArticleByDate(startDate,endDate);
    }
}
