package com.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.mapper.ApiMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private ApiMapper mapper;
    private final Gson gson;

    @Override
    public void registerPetData(PetnoticeDTO pdto) {
        mapper.insertPetData(pdto);
    }

    @Override
    public void saveData(String apiurl, String servicekey, int startpage, int numOfRows) {
    
        // ...

        int pageNo = startpage;  // 시작 페이지
        int totalCount = 0;      // 총 데이터 건수
        int requestCount = 0;    // 요청한 데이터 건수
        
        while (true) {
            String apiUrl = apiurl + "?pageno=" + pageNo + "&numOfRows=" + numOfRows + "&serviceKey=" + servicekey;
            // ...

            int resultCount = parseData(response);
            totalCount += resultCount;
            requestCount += numOfRows;

            if (resultCount < numOfRows) {
                // 요청한 건수보다 적게 들어온 경우, 마지막 페이지에 도달한 것으로 간주
                break;
            }
            pageNo++;
        }

        // ...
    }

    // ...
}

    public String makeApiCall(String apiUrl, String serviceKey) {
        // 입력된 API URL과 Service Key로 실제 API 호출을 수행하는 코드
        // 반환된 데이터를 문자열 형태로 반환

        // 이 부분은 실제로는 URL 연결 및 API 호출을 수행하는 코드
        String responseData = "API 호출 결과 데이터"; // 임시 데이터 추가
        return responseData;
    }

    public PetnoticeDTO parseData(String responseData) {
        JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();

        PetnoticeDTO petnoticeDTO = new PetnoticeDTO();
        petnoticeDTO.setHappenDt(jsonObject.has("happenDt") ? jsonObject.get("happenDt").getAsString() : "");
        petnoticeDTO.setHappenPlace(jsonObject.has("happenPlace") ? jsonObject.get("happenPlace").getAsString() : "");
        petnoticeDTO.setKindCd(jsonObject.has("kindCd") ? jsonObject.get("kindCd").getAsString() : "");
        petnoticeDTO.setColorCd(jsonObject.has("colorCd") ? jsonObject.get("colorCd").getAsString() : "");
        petnoticeDTO.setAge(jsonObject.has("age") ? jsonObject.get("age").getAsString() : "");
        petnoticeDTO.setWeight(jsonObject.has("weight") ? jsonObject.get("weight").getAsString() : "");
        petnoticeDTO.setNoticeNo(jsonObject.has("noticeNo") ? jsonObject.get("noticeNo").getAsString() : "");
        petnoticeDTO.setNoticeSdt(jsonObject.has("noticeSdt") ? jsonObject.get("noticeSdt").getAsString() : "");
        petnoticeDTO.setNoticeEdt(jsonObject.has("noticeEdt") ? jsonObject.get("noticeEdt").getAsString() : "");
        petnoticeDTO.setPopfile(jsonObject.has("popfile") ? jsonObject.get("popfile").getAsString() : "");
        petnoticeDTO.setProcessState(jsonObject.has("processState") ? jsonObject.get("processState").getAsString() : "");
        petnoticeDTO.setSexCd(jsonObject.has("sexCd") ? jsonObject.get("sexCd").getAsString() : "");
        petnoticeDTO.setNeuterYn(jsonObject.has("neuterYn") ? jsonObject.get("neuterYn").getAsString() : "");
        petnoticeDTO.setSpecialMark(jsonObject.has("specialMark") ? jsonObject.get("specialMark").getAsString() : "");
        petnoticeDTO.setCareNm(jsonObject.has("careNm") ? jsonObject.get("careNm").getAsString() : "");
        petnoticeDTO.setCareAddr(jsonObject.has("careAddr") ? jsonObject.get("careAddr").getAsString() : "");
        petnoticeDTO.setCareTel(jsonObject.has("careTel") ? jsonObject.get("careTel").getAsString() : "");
        return petnoticeDTO;
    }
}