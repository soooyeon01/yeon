package com.spring.service;

import java.util.List;

import com.spring.domain.W_DTO;

public interface SixService {
	List<W_DTO> getRC(String category3);
	List<W_DTO> getAC(String category3);
	List<W_DTO> getCU(String category3);
	List<W_DTO> getHP(String category3);
	List<W_DTO> getGO(String category3);
	List<W_DTO> getCA(String category3);
}
