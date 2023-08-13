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

import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiDataController {
	// 주소 - http://localhost:8080/4jojo/api/petdata

	
	@Scheduled(cron = "0 0 */6 * * ?")
	void apiPetDataScheduled() {
		apiPetData();

	}
	@Scheduled(cron = "0 0 */6 * * ?")
	void apiShelDataScheduled() {
		apiShelData();

	}
	@Scheduled(cron = "0 0 0 * * ?") 
	void apiWithDataScheduled() {
		apiWithData();

	}
	
	private final ApiService service;
	private static String servicePetKey = "8mI5YJHYDClBCO0nVGTefXN%2FNRNDL4R68OP9EmufvlXPqdTKQSDm%2BsFUOYWKMuHHs%2Bi%2B1wxPQXr5HDnyjtr%2B8A%3D%3D";
	private static String serviceShelKey = "vPYPuEmQxsTmZx%2BMhGPBNw5QXD9P1oWLThzGQjTSlEg%2FNBb05bVez9RVHAYkGwXcAfJHD43kuDJf81MUKBJq4A%3D%3D";
	private static String serviceWithKey = "lqV979qx%2FDA%2B1cQUXxSoNyaRUMaI%2FEqK3ciILfmuyqdaKb%2FYPDZXwlePD6PoB77pXNWwtFVp4Y39BifgXv9%2BJg%3D%3D";

	@RequestMapping("/petdata")
	public String apiPetData() {
		ArrayList<P_DTO> list = new ArrayList<>();
		P_DTO pdto = new P_DTO();
		int max = 0;

		try {
			String url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" + "pageNo=1&"
					+ "numOfRows=1000&" + "serviceKey=" + servicePetKey;
			
			//xml 데이터를 파싱하기 위해 documentBuilder와 document 객체를 사용
			//DocumentBuilderFactory는 xml 파서를 생성하기 위한 팩토리 클래스, DocumentBuilderFactory의 인스턴스를 생성해서 dbFactory에 저장
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance(); 
			
			//DocumentBuilder는 xml문서를 파싱하는데 사용되는 클래스, DocumentBuilder의 인스턴스를 생성해서 dBuilder에 저장
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			
			//parse(url)메서드는 주어진 url에서 xml데이터를 가져와 파싱하여 doc에 저장
			Document doc = dBuilder.parse(url);
			
			//xml 문서의 루트 요소를 얻고 정규화
			doc.getDocumentElement().normalize();
			
			//doc.getElementsByTagName("totalCount") 을 호출하여 totalCount 태그를 가진 노드 목록을 가져와 totalNode에 저장.
			NodeList totalNode = doc.getElementsByTagName("totalCount");
			
			// totalCount 태그의 이름을 가진 첫 번째 노드를 가져오는 과정을 설명
			Element totalElement = (Element) totalNode.item(0);
			// totalCount 태그의 값을 가져와 totalCount 변수에 저장합니다.
			int totalCount = Integer.parseInt(totalElement.getTextContent());

			int numOfRows = 1000;
			
			// 전체 페이지 개수(max)를 계산합니다.
			max = (int) Math.ceil((double) totalCount / numOfRows);

			// 바깥 for문을 추가하여 각 페이지의 데이터를 가져옵니다.
			for (int page = 1; page <= max; page++) {
				url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" + "pageNo=" + page
						+ "&" + "numOfRows=1000&" + "serviceKey=" + servicePetKey;
				doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();
				
				//doc.getElementsByTagName("item") 을 호출하여 item 태그를 가진 노드 목록을 가져와 nList에 저장한다.
				NodeList nList = doc.getElementsByTagName("item");
				
				//temp 변수를 0부터 nList의 길이보다 작을 때까지 반복	
				for (int temp = 0; temp < nList.getLength(); temp++) {
					//nList에서 인덱스 temp에 해당하는 요소를 가져와서 nNode에 저장
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						
						// 현재 nNode를 eElement로 캐스팅합니다.
						Element eElement = (Element) nNode;
						
						// P_DTO 객체 pdto를 생성하고 각 태그의 값을 추출하여 설정합니다.
						pdto.setHappenDt(getTagValue("happenDt", eElement));
						pdto.setHappenPlace(getTagValue("happenPlace", eElement));
						pdto.setKindCd(getTagValue("kindCd", eElement));
						pdto.setColorCd(getTagValue("colorCd", eElement));
						pdto.setAge(getTagValue("age", eElement));
						pdto.setWeight(getTagValue("weight", eElement));
						pdto.setNoticeNo(getTagValue("noticeNo", eElement));
						pdto.setNoticeSdt(getTagValue("noticeSdt", eElement));
						pdto.setNoticeEdt(getTagValue("noticeEdt", eElement));
						pdto.setPopfile(getTagValue("popfile", eElement));
						pdto.setProcessState(getTagValue("processState", eElement));
						pdto.setSexCd(getTagValue("sexCd", eElement));
						pdto.setNeuterYn(getTagValue("neuterYn", eElement));
						pdto.setSpecialMark(getTagValue("specialMark", eElement));
						pdto.setCareNm(getTagValue("careNm", eElement));
						pdto.setCareAddr(getTagValue("careAddr", eElement));
						pdto.setCareTel(getTagValue("careTel", eElement));
						list.add(pdto);
						
						// 유기동물 공고 api 데이터 등록 메소드 실행
						service.regitsterPetData(pdto);
					}
				}
			}
			// 유기동물 공고 api 중복 데이터 삭제 메소드 실행
			service.removePetData(pdto);
			// 유기동물 공고 api 종료된 데이터 삭제 메소드 실행
			service.removePetEnd(pdto);
			log.info("end");

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 실행 완료되면 api.jsp 페이지로 이동
		return "/api/api";
	}
	@RequestMapping("/sheldata")
	public String apiShelData() {

		ArrayList<S_DTO> list = new ArrayList<>();
		S_DTO sdto = new S_DTO();
		int max = 0;

		try {
			String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo?" + "pageNo=1&"
					+ "numOfRows=1000&" + "serviceKey=" + serviceShelKey;
			
			
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
						+ "numOfRows=1000&" + "serviceKey=" + serviceShelKey;
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
	@RequestMapping("/withdata")
	public String apiWithData() {

		ArrayList<W_DTO> list = new ArrayList<>();
		W_DTO wdto = new W_DTO();
		int max = 0;

		try {
			String url = "https://api.odcloud.kr/api/15111389/v1/uddi:41944402-8249-4e45-9e9d-a52d0a7db1cc" + "?page=1"
					+ "&perPage=1000&returnType=XML&serviceKey=" + serviceWithKey;

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
						+ page + "&perPage=1000&returnType=XML&serviceKey=" + serviceWithKey;
				doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("item");

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						wdto.setBuilding(getWithTagValue("시설명", eElement));
						wdto.setCategory3(getWithTagValue("카테고리3", eElement));
						wdto.setRoad(getWithTagValue("도로명주소", eElement));
						wdto.setTel(getWithTagValue("전화번호", eElement));
						wdto.setHomepage(getWithTagValue("홈페이지", eElement));
						wdto.setDay_off(getWithTagValue("휴무일", eElement));
						wdto.setHour(getWithTagValue("운영시간", eElement));
						wdto.setParking(getWithTagValue("주차 가능여부", eElement));
						wdto.setWith_pet_info(getWithTagValue("반려동물 동반 가능정보", eElement));
						wdto.setOnly_pet_info(getWithTagValue("반려동물 전용 정보", eElement));
						wdto.setPet_size(getWithTagValue("입장 가능 동물 크기", eElement));
						wdto.setPet_limit(getWithTagValue("반려동물 제한사항", eElement));
						wdto.setInside(getWithTagValue("장소(실내) 여부", eElement));
						wdto.setOutside(getWithTagValue("장소(실외)여부", eElement));
						wdto.setExtra(getWithTagValue("애견 동반 추가 요금", eElement));
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

	
	
	
	//pet_notice, shelter tag
	public static String getTagValue(String tag, Element eElement) {
		// eElement에서 tag에 해당하는 NodeList의 첫 번째 요소에 접근합니다.
		Node nlList = eElement.getElementsByTagName(tag).item(0);
		
		// 태그가 찾아졌는지 확인하고, 값이 있는 경우 그 값을 반환합니다.
		if (nlList != null) {
			Node nValue = nlList.getChildNodes().item(0);
			if (nValue != null) {
				return nValue.getNodeValue();
			}
		}
		// 일치하는 태그 값이 없는 경우 null을 반환합니다.
		return null;
	}
	
	
	// with_pet tag
	public static String getWithTagValue(String name, Element eElement) {
		//eElement에서 "col" 태그를 가진 모든 노드를 가져와 nodeList에 저장합니다.
		NodeList nodeList = eElement.getElementsByTagName("col");
		//nodeList에 있는 모든 노드를 순회하기 위한 for문입니다.
		for (int i = 0; i < nodeList.getLength(); i++) {
			//nodeList에서 i번째 노드를 가져와 node 변수에 저장합니다.
			Node node = nodeList.item(i);
			//node의 타입이 ELEMENT_NODE인 경우만 처리합니다.
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//node를 Element 객체로 캐스팅하여 element 변수에 저장합니다.
				Element element = (Element) node;
				//element의 "name" 속성 값을 가져와 attributeName 변수에 저장합니다.
				String attributeName = element.getAttribute("name");
				//attributeName이 찾고자 하는 name과 일치하는 경우에만 처리합니다.
				if (attributeName.equals(name)) {
					//일치하는 "col" 태그의 텍스트 값을 반환합니다.
					return element.getTextContent();
				}
			}
		}
		//값이 없으면 null을 반환
		return null;
	}
}