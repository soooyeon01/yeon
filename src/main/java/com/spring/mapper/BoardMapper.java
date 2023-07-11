package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardDTO;
import com.spring.util.PageMaker;

public interface BoardMapper {
	List<BoardDTO> selectBoardList();

	List<BoardDTO> selectAllBoardByPage(PageMaker pageMaker);
}
