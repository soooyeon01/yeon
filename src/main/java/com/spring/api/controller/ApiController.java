package com.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.service.PetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    @Autowired
    private PetService petservice;

    @PostMapping("/saveData")
    public String saveData(@RequestParam("apiUrl") String apiUrl, @RequestParam("serviceKey") String serviceKey) {
        // AJAX 요청으로부터 받은 API URL과 Service Key를 사용하여 데이터를 가져옴
        // 데이터를 파싱하여 PetnoticeDTO 클래스에 맞게 변환
        // 변환된 데이터를 DB에 저장
        // 저장 성공 여부에 따라 응답 반환

        // API URL과 Service Key를 사용하여 실제 API 호출을 수행하는 makeApiCall 메소드 호출
        String response = petservice.makeApiCall(apiUrl, serviceKey); // API 호출
        // API 호출 결과를 파싱하여 PetnoticeDTO 객체에 맞게 변환하는 parseData 메소드 호출
        PetnoticeDTO data = petservice.parseData(response); // 데이터 파싱 및 변환
        // 변환된 PetnoticeDTO 객체를 데이터베이스에 저장
        petservice.registerPetData(data); // 데이터 저장

        return "데이터가 성공적으로 저장되었습니다."; // 저장 성공 여부에 따라 반환할 문구
    }
}
