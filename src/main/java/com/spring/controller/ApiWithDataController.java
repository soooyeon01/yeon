package com.spring.controller;

import org.springframework.scheduling.annotation.Scheduled;

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
import lombok.extern.log4j.Log4j;

/**
 * 반려동물 문화시설(위드펫) API 데이터를 처리하는데 사용되는 컨트롤러 클래스 입니다.
 * 
 * @author 김민주
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
@Log4j
public class ApiWithDataController {
	//주소 - http://localhost:8080/4jojo/api/withdata

	/**
	 * 매일 자정에 실행되는 스케줄러입니다. apiWithData() 메서드를 호출합니다.
	 */
	@Scheduled(cron = "0 0 0 * * ?") 
	void apiPetDataScheduled() {
		apiWithData();

	}

	/**
	 * 보호소에 대한 API 데이터를 가져옵니다.
	 * 
	 * @see com.spring.service.ApiService#regitsterWithData(W_DTO) - 데이터 삽입
	 * @see com.spring.service.ApiService#removeWithData(W_DTO) - 중복 제거
	 * @return API 처리 결과를 나타내는 jsp 페이지
	 */
	private final ApiService service;
	private static String serviceKey = "lqV979qx%2FDA%2B1cQUXxSoNyaRUMaI%2FEqK3ciILfmuyqdaKb%2FYPDZXwlePD6PoB77pXNWwtFVp4Y39BifgXv9%2BJg%3D%3D";

	@RequestMapping("/withdata")
	public String apiWithData() {

		ArrayList<W_DTO> list = new ArrayList<>();
		W_DTO wdto = new W_DTO();
		int max = 0;

		try {
			String url = "https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc" + "?page=1"
					+ "&perPage=1000&returnType=XML&serviceKey=" + serviceKey;

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();

			NodeList totalNode = doc.getElementsByTagName("totalCount");
			Element totalElement = (Element) totalNode.item(0);
			int totalCount = Integer.parseInt(totalElement.getTextContent());

			int numOfRows = 1000;
			max = (int) Math.ceil((double) totalCount / numOfRows);

			// 바깥 for문을 추가하여 각 페이지의 데이터를 가져옵니다.
			for (int page = 1; page <= max; page++) {
				url = "https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc" + "?page="
						+ page + "&perPage=1000&returnType=XML&serviceKey=" + serviceKey;
				doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("item");

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						wdto.setBuilding(getTagValue("시설명", eElement));
						wdto.setCategory3(getTagValue("카테고리3", eElement));
						wdto.setRoad(getTagValue("도로명주소", eElement));
						wdto.setTel(getTagValue("전화번호", eElement));
						wdto.setHomepage(getTagValue("홈페이지", eElement));
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
						service.regitsterWithData(wdto);
					}
				}
			}
			service.removeWithData(wdto);
			log.info("end");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/api/api";
	}

	/**
	 * 주어진 XML 요소(eElement)에서 특정 태그(name)의 값을 추출합니다.
	 *
	 * @param name     태그 이름
	 * @param eElement 태그 값을 가져올 XML 요소
	 * @return 추출된 태그의 값, 태그를 찾지 못한 경우 null을 반환합니다.
	 */
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