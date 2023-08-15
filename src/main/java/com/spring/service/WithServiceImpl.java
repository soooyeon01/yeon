package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.WithDTO;
import com.spring.mapper.WithMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WithServiceImpl implements WithService {
	private final WithMapper mapper;

	@Override
	public List<WithDTO> getWithList(String type, String keyword, String region, String category3, PageMaker pageMaker) {
		return mapper.selectWithList(type, keyword, region, category3, pageMaker);
	}

	@Override
	public int getWithCount(String type, String keyword, String region, String category3) {
		return mapper.selectWithCount(type, keyword, region, category3);
	}

	@Override
	public List<WithDTO> getWithDetail(int with_pet_no) {
		return mapper.selectWithDetail(with_pet_no);
	}

	@Override
	public void registerWithData(String nickname, WithDTO dto) {

		mapper.insertWithData(nickname, dto);
	}

	@Override
	public int removeWithData(int with_pet_no) {

		return mapper.deleteWithData(with_pet_no);
	}

}
