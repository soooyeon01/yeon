package com.spring.service;

import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
/**
 * 유기동물 공고, 보호소, 반려동물 문화시설 Api 데이터 삽입, 삭제를 처리하는 메소드를 선언한 service 인터페이스 입니다.
 * 
 * @see com.spring.service.ApiServiceImpl
 * @author 김민주	
 */
public interface ApiService {
	/**
	 * regitsterPetData 메소드는 P_DTO 객체를 받아서 유기동물 공고 데이터를 등록합니다.
	 * 
	 * @param pdto - P_DTO(유기동물 공고) 데이터를 담고있는 객체
	 * @return int 등록 성공 결과
	 */
	int regitsterPetData(P_DTO pdto);
	
	/**
	 * removePetData 메소드는 P_DTO 객체를 받아서 중복된 유기동물 공고 데이터를 삭제합니다.
	 * 
	 * @param pdto - P_DTO(유기동물 공고) 데이터를 담고있는 객체
	 * @return int 삭제 성공 결과
	 */
	int removePetData(P_DTO pdto);
	
	/**
	 * removePetEnd 메소드는 P_DTO 객체를 받아서 공고가 종료된 유기동물 공고 데이터를 삭제합니다.
	 * 
	 * @param pdto - P_DTO(유기동물 공고) 데이터를 담고있는 객체
	 * @return int 삭제 성공 결과
	 */
	int removePetEnd(P_DTO pdto);
	
	/**
	 * regitsterShelData 메소드는 S_DTO 객체를 받아서 보호소 데이터를 등록합니다.
	 * 
	 * @param sdto - S_DTO(보호소) 데이터를 담고있는 객체
	 * @return int 등록 성공 결과
	 */
	int regitsterShelData(S_DTO sdto);
	
	/**
	 * removeShelData 메소드는 S_DTO 객체를 받아서 중복된 보호소 데이터를 삭제합니다.
	 * 
	 * @param sdto - S_DTO(보호소) 데이터를 담고있는 객체
	 * @return int 삭제 성공 결과
	 */
	int removeShelData(S_DTO sdto);
	
	/**
	 * regitsterWithData 메소드는 W_DTO 객체를 받아서 반려동물 문화시설 데이터를 등록합니다.
	 * 
	 * @param wdto - W_DTO(반려동물 문화시설) 데이터를 담고있는 객체
	 * @return int 등록 성공 결과
	 */
	int regitsterWithData(W_DTO wdto);
	
	/**
	 * removeWithData 메소드는 W_DTO 객체를 받아서 중복된 반려동물 문화시설 데이터를 삭제합니다.
	 * 
	 * @param wdto - W_DTO(반려동물 문화시설) 데이터를 담고있는 객체
	 * @return int 삭제 성공 결과
	 */
	int removeWithData(W_DTO wdto);

}
