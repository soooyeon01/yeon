package com.spring.api.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.spring.api.domain.OpenAPI_ServiceResponse;
import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.mapper.ApiMapper;

import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final ApiMapper mapper;

    @Override
    public void fetchDataAndSaveToDB() {
        int pageNo = 1;
        int numOfRows = 1000;
        int totalDataCount = 0;
        int currentDataCount = 0;
        String serviceKey = "8mI5YJHYDClBCO0nVGTefXN%2FNRNDL4R68OP9EmufvlXPqdTKQSDm%2BsFUOYWKMuHHs%2Bi%2B1wxPQXr5HDnyjtr%2B8A%3D%3D";
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.singletonList(new StringHttpMessageConverter(StandardCharsets.UTF_8)));

        while (true) {
        	  HttpUrl url = HttpUrl.parse("https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic")
                      .newBuilder()
                      .addQueryParameter("pageNo", String.valueOf(pageNo))
                      .addQueryParameter("numOfRows", String.valueOf(numOfRows))
                      .addQueryParameter("serviceKey", serviceKey)
                      .build();

        	  ResponseEntity<OpenAPI_ServiceResponse> response = restTemplate.getForEntity(url.toString(), OpenAPI_ServiceResponse.class);
        	  OpenAPI_ServiceResponse responseBody = response.getBody();

            // responseBody 데이터를 PetNoticeDTO 객체로 변환
            PetnoticeDTO pdto = convertToPetNoticeDTO(responseBody);

            // Null check for pdto
            if (pdto != null) {
                // MyBatis를 사용하여 DB에 저장
                mapper.insertPetData(pdto);

                // 현재 데이터 수와 전체 데이터 수 가져오기
                currentDataCount = pdto.getPetnoticeDTO().size(); // 현재 데이터 수
                totalDataCount = pdto.getTotalCount(); // 전체 데이터 수

                // 데이터 모으기 종료 조건 검사
                if (currentDataCount == 0 || currentDataCount >= totalDataCount) {
                    break;
                }
            } else {
                break;
            }

            pageNo++; // pageNo 증가
        }
    }

    private PetnoticeDTO convertToPetNoticeDTO(OpenAPI_ServiceResponse responseBody) {
    	 List<PetnoticeDTO> petnoticeList = responseBody.getResponse().getBody().getItems();
    	 int totalCount = responseBody.getResponse().getBody().getTotalCount();

        PetnoticeDTO petnoticeDTO = new PetnoticeDTO();
        petnoticeDTO.setPetnoticeDTO(petnoticeList);
        petnoticeDTO.setTotalCount(totalCount);

        return petnoticeDTO;
    }
}

 