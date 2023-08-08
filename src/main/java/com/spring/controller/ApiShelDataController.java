package com.spring.controller;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.spring.domain.S_DTO;
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 보호소 API 데이터를 처리하는데 사용되는 컨트롤러 클래스 입니다.
 * 
 * @author 김민주 
 */
@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiShelDataController {
	// 주소 - http://localhost:8080/4jojo/api/sheldata

	/**
	 * 매일 오전 1시에 실행되는 스케줄러입니다. apiShelData() 메서드를 호출합니다.
	 * 
	 * @see com.spring.controller.ApiShelDataController#apiShelData()
	 */

	@Scheduled(cron = "0 0 1 * * ?")
	void apiPetDataScheduled() {
		apiShelData();
	}

	/**
	 * 보호소에 대한 API 데이터를 가져옵니다.
	 * 
	 * @see com.spring.service.ApiService#regitsterShelData(S_DTO) - 데이터 삽입
	 * @see com.spring.service.ApiService#removeShelData(S_DTO) - 중복 제거
	 * @return API 처리 결과를 나타내는 jsp 페이지
	 */

	private final ApiService service;
	private static String serviceKey = "vPYPuEmQxsTmZx%2BMhGPBNw5QXD9P1oWLThzGQjTSlEg%2FNBb05bVez9RVHAYkGwXcAfJHD43kuDJf81MUKBJq4A%3D%3D";

	@RequestMapping("/sheldata")
	public String apiShelData() {

		ArrayList<S_DTO> list = new ArrayList<>();
		S_DTO sdto = new S_DTO();
		int max = 0;

		try {
			String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?" + "pageNo=1&"
					+ "numOfRows=1000&" + "serviceKey=" + serviceKey;
			
			
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
				url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?" + "pageNo=" + page + "&"
						+ "numOfRows=1000&" + "serviceKey=" + serviceKey;
				doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("item");

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						sdto.setCareNm(getTagValue("careNm", eElement));
						sdto.setDivisionNm(getTagValue("divisionNm", eElement));
						sdto.setSaveTrgtAnimal(getTagValue("saveTrgtAnimal", eElement));
						sdto.setCareAddr(getTagValue("careAddr", eElement));
						sdto.setWeekOprStime(getTagValue("weekOprStime", eElement));
						sdto.setWeekOprEtime(getTagValue("weekOprEtime", eElement));
						sdto.setWeekCellStime(getTagValue("weekCellStime", eElement));
						sdto.setWeekCellEtime(getTagValue("weekCellEtime", eElement));
						sdto.setWeekendOprStime(getTagValue("weekendOprStime", eElement));
						sdto.setWeekendOprEtime(getTagValue("weekendOprEtime", eElement));
						sdto.setWeekendCellStime(getTagValue("weekendCellStime", eElement));
						sdto.setWeekendCellEtime(getTagValue("weekendCellEtime", eElement));
						sdto.setCloseDay(getTagValue("closeDay", eElement));
						sdto.setCareTel(getTagValue("careTel", eElement));
						list.add(sdto);
						service.regitsterShelData(sdto);
					}
				}
			}
			service.removeShelData(sdto);
			log.info("end");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/api/api";
	}

	/**
	 * 주어진 XML 요소(eElement)에서 태그(tag)의 값을 가져옵니다.
	 * 
	 * @param tag      태그 이름
	 * @param eElement 태그 값을 가져올 XML 요소
	 * @return 추출된 태그의 값 또는 찾지 못한 경우 null
	 */
	public static String getTagValue(String tag, Element eElement) {
		Node nlList = eElement.getElementsByTagName(tag).item(0);
		if (nlList != null) {
			Node nValue = nlList.getChildNodes().item(0);
			if (nValue != null) {
				return nValue.getNodeValue();
			}
		}
		return null;
	}
}