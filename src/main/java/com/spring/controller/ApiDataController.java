package com.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.spring.domain.MembersDTO;
import com.spring.domain.PetDTO;
import com.spring.domain.ShelDTO;
import com.spring.domain.WithDTO;
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiDataController {
	// 주소 - http://localhost:8080/4jojo/api/petdata
	// 주소 - http://localhost:8080/4jojo/api/sheldata
	// 주소 - http://localhost:8080/4jojo/api/withdata

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
		ArrayList<PetDTO> list = new ArrayList<>();
		PetDTO pdto = new PetDTO();
		int max = 0;

		try {
			String url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" + "pageNo=1&"
					+ "numOfRows=1000&" + "serviceKey=" + servicePetKey;
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();
			NodeList totalNode = doc.getElementsByTagName("totalCount");
			Element totalElement = (Element) totalNode.item(0);			
			int totalCount = Integer.parseInt(totalElement.getTextContent());
			int numOfRows = 1000;
			max = (int) Math.ceil((double) totalCount / numOfRows);

			for (int page = 1; page <= max; page++) {
				url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" + "pageNo=" + page
						+ "&" + "numOfRows=1000&" + "serviceKey=" + servicePetKey;
				doc = dBuilder.parse(url);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

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

			
						service.regitsterPetData(pdto);
					}
				}
			}
			service.removePetData(pdto);
			service.removePetEnd(pdto);
			log.info("end");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/api/api";
	}

	@RequestMapping("/sheldata")
	public String apiShelData() {

		ArrayList<ShelDTO> list = new ArrayList<>();
		ShelDTO sdto = new ShelDTO();
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

		ArrayList<WithDTO> list = new ArrayList<>();
		WithDTO wdto = new WithDTO();
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

	// pet_notice, shelter tag
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

	// with_pet tag
	public static String getWithTagValue(String name, Element eElement) {
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

	// admin api control
	@RequestMapping("/apilist")
	public String CommunityList(HttpSession session, Model model, MembersDTO mdto) {

		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");

		if (SESS_AUTH != null && SESS_AUTH) {
			String email = (String) session.getAttribute("SESS_EMAIL");
			String nickname = (String) session.getAttribute("SESS_NICKNAME");

			return "api/apilist";
		} else {
			return "redirect:/main/main";
		}

	}
}