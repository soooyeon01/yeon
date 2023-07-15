package com.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;
import com.spring.api.mapper.ApiMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShelServiceImpl {

    @Autowired
    private ApiMapper mapper;

    public String saveData(String apiUrl, String serviceKey) {
        // API 호출 및 데이터 파싱하는 로직을 이곳으로 이동
        String response = makeApiCall(apiUrl, serviceKey); // API 호출
        ShelterDTO data = parseData(response); // 데이터 파싱 및 변환
        
        mapper.insertShelData(data); // 데이터 저장

        return "데이터가 성공적으로 저장되었습니다."; // 응답 반환
    }

    private String makeApiCall(String apiUrl, String serviceKey) {
        // 입력된 API URL과 Service Key로 실제 API 호출을 수행하는 코드
        // 반환된 데이터를 문자열 형태로 반환

        // 이 부분은 실제로는 URL 연결 및 API 호출을 수행하는 코드
        String responseData = "API 호출 결과 데이터"; // 임시 데이터 추가
        return responseData;
    }

    private ShelterDTO parseData(String responseData) {
    	JsonParser parser = new JsonParser();
    	JsonObject jsonObject = parser.parse(responseData).getAsJsonObject();

    	ShelterDTO shelterDTO = new ShelterDTO();
    	shelterDTO.setCareNm(jsonObject.has("careNm") ? jsonObject.get("careNm").getAsString() : "");
    	shelterDTO.setDivisionNm(jsonObject.has("divisionNm") ? jsonObject.get("divisionNm").getAsString() : "");
    	shelterDTO.setSaveTrgtAnimal(jsonObject.has("saveTrgtAnimal") ? jsonObject.get("saveTrgtAnimal").getAsString() : "");
    	shelterDTO.setCareAddr(jsonObject.has("careAddr") ? jsonObject.get("careAddr").getAsString() : "");
    	shelterDTO.setWeekOprStime(jsonObject.has("weekOprStime") ? jsonObject.get("weekOprStime").getAsString() : "");
    	shelterDTO.setWeekOprEtime(jsonObject.has("weekOprEtime") ? jsonObject.get("weekOprEtime").getAsString() : "");
    	shelterDTO.setWeekCellStime(jsonObject.has("weekCellStime") ? jsonObject.get("weekCellStime").getAsString() : "");
    	shelterDTO.setWeekCellEtime(jsonObject.has("weekCellEtime") ? jsonObject.get("weekCellEtime").getAsString() : "");
    	shelterDTO.setWeekendOprStime(jsonObject.has("weekendOprStime") ? jsonObject.get("weekendOprStime").getAsString() : "");
    	shelterDTO.setWeekendOprEtime(jsonObject.has("weekendOprEtime") ? jsonObject.get("weekendOprEtime").getAsString() : "");
    	shelterDTO.setWeekendCellStime(jsonObject.has("weekendCellStime") ? jsonObject.get("weekendCellStime").getAsString() : "");
    	shelterDTO.setWeekendCellEtime(jsonObject.has("weekendCellEtime") ? jsonObject.get("weekendCellEtime").getAsString() : "");
    	shelterDTO.setCloseDay(jsonObject.has("closeDay") ? jsonObject.get("closeDay").getAsString() : "");
    	shelterDTO.setCareTel(jsonObject.has("careTel") ? jsonObject.get("careTel").getAsString() : "");
        return shelterDTO;
    }
}
