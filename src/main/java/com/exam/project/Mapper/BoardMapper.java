package com.exam.project.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    String boardIdToName(long boardId);
}
