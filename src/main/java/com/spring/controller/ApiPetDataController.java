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
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 
 * @author 김민주 유기동물 공고 API 데이터를 처리하는데 사용되는 컨트롤러 클래스 입니다.
 * 
 */
@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiPetDataController {
	// 주소 - http://localhost:8080/4jojo/api/petdata

	/**
	 * 6시간마다 실행되는 스케줄러입니다. apiPetData() 메서드를 호출합니다.
	 * 
	 * @see com.spring.controller.ApiPetDataController#apiPetData()
	 */
	@Scheduled(cron = "0 0 */6 * * ?")
	void apiPetDataScheduled() {
		apiPetData();

	}

	/**
	 * 유기동물 공고에 대한 API 데이터를 가져옵니다.
	 * 
	 * @see com.spring.service.ApiService#regitsterPetData(P_DTO) - 데이터 삽입
	 * @see com.spring.service.ApiService#removePetData(P_DTO) - 중복 제거
	 * @see com.spring.service.ApiService#removePetEnd(P_DTO) - 종료된 공고 제거
	 * @return API 처리 결과를 나타내는 jsp 페이지
	 */
	private final ApiService service;
	private static String serviceKey = "8mI5YJHYDClBCO0nVGTefXN%2FNRNDL4R68OP9EmufvlXPqdTKQSDm%2BsFUOYWKMuHHs%2Bi%2B1wxPQXr5HDnyjtr%2B8A%3D%3D";

	@RequestMapping("/petdata")
	public String apiPetData() {
		ArrayList<P_DTO> list = new ArrayList<>();
		P_DTO pdto = new P_DTO();
		int max = 0;

		try {
			String url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" + "pageNo=1&"
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
				url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" + "pageNo=" + page
						+ "&" + "numOfRows=1000&" + "serviceKey=" + serviceKey;
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