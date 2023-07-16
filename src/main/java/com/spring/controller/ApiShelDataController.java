package com.spring.controller;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import com.spring.domain.S_DTO;
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiShelDataController {
	// http://localhost:8080/4jojo/api/sheldata
	private final ApiService service;
	private static final int max = 20;
	private static String serviceKey = "vPYPuEmQxsTmZx%2BMhGPBNw5QXD9P1oWLThzGQjTSlEg%2FNBb05bVez9RVHAYkGwXcAfJHD43kuDJf81MUKBJq4A%3D%3D";
	@RequestMapping("/sheldata")
	public String fetchShelData() {
      

      // sdto 객체들을 저장할 list
      ArrayList<S_DTO> list = new ArrayList<>();
      
      try {
            // parsing할 url 지정(API 키 포함해서)
            for(int i=1; i<max; i++) {
            	
            //	https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?serviceKey=vPYPuEmQxsTmZx%2BMhGPBNw5QXD9P1oWLThzGQjTSlEg%2FNBb05bVez9RVHAYkGwXcAfJHD43kuDJf81MUKBJq4A%3D%3D
	            String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?"
	            		+ "pageNo="+i+"&"
            				+ "numOfRows=1000&"
            				+ "serviceKey="+serviceKey;
	
	            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	            Document doc = dBuilder.parse(url);
	            
	            // root tag
	            doc.getDocumentElement().normalize();
	            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	
	            // 파싱할 tag
	            NodeList nList = doc.getElementsByTagName("item");
	            System.out.println("파싱할 리스트 수 : " + nList.getLength());
	            System.out.println("여기1");
	            S_DTO sdto = null;
	            
	            for (int temp = 0; temp < nList.getLength(); temp++) {
	               Node nNode = nList.item(temp);
	               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
	                  Element eElement = (Element) nNode;
	
	                  // sdtotage vo를 저장할 객체
	                  sdto = new S_DTO();
	                  sdto.setCareNm(getTagValue("careNm", eElement));
	                  sdto.setDivisionNm(getTagValue("divisionNm", eElement)); // 지정번호
	                  sdto.setSaveTrgtAnimal(getTagValue("saveTrgtAnimal", eElement));
	                  sdto.setCareAddr(getTagValue("careAddr", eElement)); // 시도코드
	                  sdto.setWeekOprStime(getTagValue("weekOprStime", eElement)); // 위도
	                  sdto.setWeekOprEtime(getTagValue("weekOprEtime", eElement)); // 경도
	                  sdto.setWeekCellStime(getTagValue("weekCellStime", eElement));
	                  sdto.setWeekCellEtime(getTagValue("weekCellEtime", eElement));
	                  sdto.setWeekendOprStime(getTagValue("weekendOprStime", eElement));
	                  sdto.setWeekendOprEtime(getTagValue("weekendOprEtime", eElement));
	                  sdto.setWeekendCellStime(getTagValue("weekendCellStime", eElement));
	                  sdto.setWeekendCellEtime(getTagValue("weekendCellEtime", eElement));
	                  sdto.setCloseDay(getTagValue("closeDay", eElement));
	                  sdto.setCareTel(getTagValue("careTel", eElement));
	              
	                  list.add(sdto);
	
	                  // 서비스 시작!
	                 
	
	                 service.regitsterShelData(sdto);
	               }
	               System.out.println("들어가는중");
               }
            } // if end
            

            
            
        }catch (Exception e) {
            e.printStackTrace(); // for end
      }
      return "/api/api";

} // try~catch end
	public static String getTagValue(String tag, Element eElement) {
		   Node nlList = eElement.getElementsByTagName(tag).item(0);
		   if (nlList != null) {
		      Node nValue = nlList.getChildNodes().item(0);
		      if (nValue != null)
		         return nValue.getNodeValue();
		   }
		   return null; // 또는 적절한 오류 처리
		}
}