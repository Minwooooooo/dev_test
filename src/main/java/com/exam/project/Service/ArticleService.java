package com.exam.project.Service;

import com.exam.project.Controller.DTO.RequestDTO.ArticleRequestDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ArticleRetrieveResponseDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ArticlesListDTO;
import com.exam.project.Controller.DTO.ResponseDTO.ResponseDTO;
import com.exam.project.Entity.ArticleEntity;
import com.exam.project.Mapper.ArticleMapper;
import com.exam.project.Mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        // Content에서 content_string(본문 내용 만) 추출
        String content_string = extractString(articleDTO.getContent());

        ArticleEntity articleEntity= ArticleEntity.builder()
                .boardId(articleDTO.getBoardId())
                .isPinned(false)
                .viewCount(0)
                .title(articleDTO.getTitle())
                .contentHtml(articleDTO.getContent())
                .contentString(content_string)
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
        }catch (NullArticleException e){
            return ResponseDTO.fail("None Exist Article","해당 ID에 존재하는 게시글이 없습니다.");
        }
    }

    public ArticleRetrieveResponseDTO responseMapping(HashMap hashMap){

        // 게시판이름 조회 by BoardId
        String board = boardMapper.boardIdToName(Long.parseLong(hashMap.get("board_id").toString()));

        // 이미지 List화
        List<String> images = extractImg(hashMap.get("content_html").toString());

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

    public ResponseDTO<String> deleteArticle(long articleId){
        try {
            int deleteCheck=articleMapper.deleteArticle(articleId);
            if(deleteCheck==0){
                throw new NullArticleException();
            }
            return ResponseDTO.success(articleId+"번 게시물 삭제 완료");
        }catch (NullArticleException e){
            return ResponseDTO.fail("None Exist Article","해당 ID에 존재하는 게시글이 없습니다.");
        }catch (Exception e){
            return ResponseDTO.fail("Error","오류가 발생하였습니다.");
        }
    }

    public ResponseDTO<List> listArticles(){
        List<ArticlesListDTO> articlesList=articleMapper.listArticles();
        if(articlesList.size()==0){
            return ResponseDTO.fail("None Exist Article","작성된 게시글이 없습니다.");
        }else {
            for (int i = 0; i < articlesList.size(); i++) {
                List<String> imgsList=extractImg(articlesList.get(i).getImage());
                if(imgsList.isEmpty()){
                    articlesList.get(i).setImage(null);
                }
                else{
                    articlesList.get(i).setImage(imgsList.get(0));
                }
            }
            return ResponseDTO.success(articlesList);
        }
    }

    public String extractString(String content){
        Pattern stringRegex=Pattern.compile("(?s)(<h2>내용</h2>).*(<p>)(.*)(</p>)");
        Matcher stringMatcher=stringRegex.matcher(content);
        String contentString = null;
        if(stringMatcher.find()) {
            contentString = stringMatcher.group(3);
        }
        return contentString;
    }

    public List<String> extractImg(String content){
        List<String> contentImgs=new ArrayList<>();
        Pattern imgsPattern = Pattern.compile("(?s)(<h1>이미지</h1>)(.*)(<h2>)");
        Matcher imgsMatcher= imgsPattern.matcher(content);

        Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"'](.*)[\"']");

        if(imgsMatcher.find()){
            String temp_imgs= imgsMatcher.group(2);
            Matcher imgMatcher = imgPattern.matcher(temp_imgs);

            while (imgMatcher.find()){
                contentImgs.add(imgMatcher.group(1));
            }
        }

        return contentImgs;

    }



    static class NullArticleException extends RuntimeException{
    }
}
