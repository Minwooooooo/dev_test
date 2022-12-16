package com.exam.project.Mapper;

import com.exam.project.Entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface ArticleMapper {

    void createArticle(ArticleEntity articleEntity);

    HashMap retrieveArticle(long articleId);
}
