package com.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;
import com.spring.api.mapper.ApiMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WithServiceImpl {

    @Autowired
    private ApiMapper mapper;

    public String saveData(String apiUrl, String serviceKey) {
        // API 호출 및 데이터 파싱하는 로직을 이곳으로 이동
        String response = makeApiCall(apiUrl, serviceKey); // API 호출
        WithpetDTO data = parseData(response); // 데이터 파싱 및 변환
        
        mapper.insertWithData(data); // 데이터 저장

        return "데이터가 성공적으로 저장되었습니다."; // 응답 반환
    }

    private String makeApiCall(String apiUrl, String serviceKey) {
        // 입력된 API URL과 Service Key로 실제 API 호출을 수행하는 코드
        // 반환된 데이터를 문자열 형태로 반환

        // 이 부분은 실제로는 URL 연결 및 API 호출을 수행하는 코드
        String responseData = "API 호출 결과 데이터"; // 임시 데이터 추가
        return responseData;
    }

    private WithpetDTO parseData(String responseData) {
    	JsonParser parser = new JsonParser();
    	JsonObject jsonObject = parser.parse(responseData).getAsJsonObject();

    	WithpetDTO withpetDTO = new WithpetDTO();
    	withpetDTO.setBuilding(jsonObject.has("building") ? jsonObject.get("building").getAsString() : "");
    	withpetDTO.setCategory3(jsonObject.has("category3") ? jsonObject.get("category3").getAsString() : "");
    	withpetDTO.setRoad(jsonObject.has("road") ? jsonObject.get("road").getAsString() : "");
    	withpetDTO.setTel(jsonObject.has("tel") ? jsonObject.get("tel").getAsString() : "");
    	withpetDTO.setHomepage(jsonObject.has("homepage") ? jsonObject.get("homepage").getAsString() : "");
    	withpetDTO.setDay_off(jsonObject.has("day_off") ? jsonObject.get("day_off").getAsString() : "");
    	withpetDTO.setHour(jsonObject.has("hour") ? jsonObject.get("hour").getAsString() : "");
    	withpetDTO.setParking(jsonObject.has("parking") ? jsonObject.get("parking").getAsString() : "");
    	withpetDTO.setWith_pet_info(jsonObject.has("with_pet_info") ? jsonObject.get("with_pet_info").getAsString() : "");
    	withpetDTO.setOnly_pet_info(jsonObject.has("only_pet_info") ? jsonObject.get("only_pet_info").getAsString() : "");
    	withpetDTO.setPet_size(jsonObject.has("pet_size") ? jsonObject.get("pet_size").getAsString() : "");
    	withpetDTO.setPet_limit(jsonObject.has("pet_limit") ? jsonObject.get("pet_limit").getAsString() : "");
    	withpetDTO.setInside(jsonObject.has("inside") ? jsonObject.get("inside").getAsString() : "");
    	withpetDTO.setOutside(jsonObject.has("outside") ? jsonObject.get("outside").getAsString() : "");
    	withpetDTO.setExtra(jsonObject.has("extra") ? jsonObject.get("extra").getAsString() : "");
    	
        return withpetDTO;
    }
}
