package com.spring.service;

import org.springframework.stereotype.Service;


import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.mapper.ApiMapper;
import lombok.RequiredArgsConstructor;

/**
 * ApiServiceImpl 클래스는 ApiService 인터페이스를 구현한 클래스입니다.
 * 이 클래스는 Api 데이터 처리 관련된 기능을 제공합니다.
 *
 * @author 김민주
 */
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
	
	private final ApiMapper mapper;
	
	 /**
     * P_DTO 객체를 받아서 유기동물 공고 데이터를 등록합니다.
     * 
     * @param pdto - P_DTO(유기동물 공고) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#insertPetData(P_DTO)
     * @return int 등록 성공 결과
     */
	@Override
	public int regitsterPetData(P_DTO pdto) {
		
		return mapper.insertPetData(pdto);
	}
	
	 /**
     * P_DTO 객체를 받아서 유기동물 공고 중복 데이터를 삭제합니다.
     * 
     * @param pdto - P_DTO(유기동물 공고) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#deletePetData(P_DTO)
     * @return int 삭제 성공 결과
     */
	@Override
	public int removePetData(P_DTO pdto) {
		
		return mapper.deletePetData(pdto);
	}

	 /**
     * P_DTO 객체를 받아서 공고가 종료된 유기동물 공고 데이터를 삭제합니다.
     * 
     * @param pdto - P_DTO(유기동물 공고) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#deletePetEnd(P_DTO)
     * @return int 삭제 성공 결과
     */
	@Override
	public int removePetEnd(P_DTO pdto) {
		
		return mapper.deletePetEnd(pdto);
	}
	
	
	 /**
     * S_DTO 객체를 받아서 보호소 데이터를 등록합니다.
     * 
     * @param sdto - S_DTO(보호소) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#insertShelData(S_DTO)
     * @return int 등록 성공 결과
     */
	@Override
	public int regitsterShelData(S_DTO sdto) {
		
		return mapper.insertShelData(sdto);
	}
	
	 /**
     * S_DTO 객체를 받아서 중복된 보호소 데이터를 삭제합니다.
     * 
     * @param sdto - S_DTO(보호소) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#deleteShelData(S_DTO)
     * @return int 삭제 성공 결과
     */
	@Override
	public int removeShelData(S_DTO sdto) {
		
		return mapper.deleteShelData(sdto);
	}
	
	
	/**
     * W_DTO 객체를 받아서 반려동물 문화시설 데이터를 등록합니다.
     * 
     * @param wdto - W_DTO(보호소) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#insertWithData(W_DTO)
     * @return int 등록 성공 결과
     */
	@Override
	public int regitsterWithData(W_DTO wdto) {
		
		return mapper.insertWithData(wdto);
	}

	/**
     * W_DTO 객체를 받아서 중복된 반려동물 문화시설 데이터를 삭제합니다.
     * 
     * @param wdto - W_DTO(보호소) 데이터를 담고있는 객체
     * @see com.spring.mapper.ApiMapper#deleteWithData(W_DTO)
     * @return int 삭제 성공 결과
     */
	@Override
	public int removeWithData(W_DTO wdto) {
		
		return mapper.deleteWithData(wdto);
	}

	

}
