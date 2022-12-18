package com.exam.project.Mapper;

import com.exam.project.Entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    void createBoard(BoardEntity boardEntity);

    String boardIdToName(long boardId);
}
