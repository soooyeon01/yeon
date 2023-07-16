package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import com.spring.domain.W_DTO;
import com.spring.service.ApiService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiWithDataController{
	//  http://localhost:8080/4jojo/api/withdata
	private final ApiService service;
	private static final int max = 30;
	private static String serviceKey = "lqV979qx%2FDA%2B1cQUXxSoNyaRUMaI%2FEqK3ciILfmuyqdaKb%2FYPDZXwlePD6PoB77pXNWwtFVp4Y39BifgXv9%2BJg%3D%3D";
	@RequestMapping("/withdata")
	public String fetchWithData() {
   // wdto 객체들을 저장할 list
	   ArrayList<W_DTO> list = new ArrayList<>();
	
	   try {
	      // parsing할 url 지정(API 키 포함해서)
	      for (int i = 1; i < max; i++) {
	     	 String url = "https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc"
	     		+ "?page=" + i + "&perPage=1000&returnType=XML&serviceKey=" + serviceKey;
	
	     	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	     	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(url);
	
	         // root tag
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
	
	         // 파싱할 tag
	         NodeList nList = doc.getElementsByTagName("item");
	         System.out.println("파싱할 리스트 수: " + nList.getLength());
	         System.out.println("여기1");
	
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
	               Element eElement = (Element) nNode;
	
	               // wdtotage vo를 저장할 객체
	               W_DTO wdto = new W_DTO();
	
	               // 종목코드
	               wdto.setBuilding(getTagValue("시설명", eElement)); // 지정번호
	               wdto.setCategory3(getTagValue("카테고리3", eElement));
	               wdto.setRoad(getTagValue("도로명주소", eElement)); // 시도코드
	               wdto.setTel(getTagValue("전화번호", eElement)); // 위도
	               wdto.setHomepage(getTagValue("홈페이지", eElement)); // 경도
	               wdto.setDay_off(getTagValue("휴무일", eElement));
	               wdto.setHour(getTagValue("운영시간", eElement));
	               wdto.setParking(getTagValue("주차 가능여부", eElement));
	               wdto.setWith_pet_info(getTagValue("반려동물 동반 가능정보", eElement));
	               wdto.setOnly_pet_info(getTagValue("반려동물 전용 정보", eElement));
	               wdto.setPet_size(getTagValue("입장 가능 동물 크기", eElement));
	               wdto.setPet_limit(getTagValue("반려동물 제한사항", eElement));
	               wdto.setInside(getTagValue("장소(실내) 여부", eElement));
	               wdto.setOutside(getTagValue("장소(실외)여부", eElement));
	               wdto.setExtra(getTagValue("애견 동반 추가 요금", eElement));
	               list.add(wdto);
	
	               // 서비스 시작!
	               
	               service.regitsterWithData(wdto);
	            }
	            System.out.println("들어가는중");
	         }
	      } // for end
	   } catch (Exception e) {
	      e.printStackTrace();
	   }
	   return "/api/api";
	} // try~catch end
	
	// tag값의 정보를 가져오는 메소드
	 public static String getTagValue(String name, Element eElement) {
		   NodeList nodeList = eElement.getElementsByTagName("col");
		   for (int i = 0; i < nodeList.getLength(); i++) {
		      Node node = nodeList.item(i);
		      if (node.getNodeType() == Node.ELEMENT_NODE) {
		         Element element = (Element) node;
		         String attributeName = element.getAttribute("name");
		         if (attributeName.equals(name)) {
		            return element.getTextContent();
		         }
		      }
		   }
		   return null;
		}

}