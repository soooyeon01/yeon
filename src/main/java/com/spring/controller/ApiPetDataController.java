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
 * 유기동물 공고 API 데이터를 처리하는데 사용되는 컨트롤러 클래스 입니다.
 * 
 * @author 김민주 
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
						+ "&" + "numOfRows=1000&" + "serviceKey=" + serviceKey;
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

	/**
	 * 주어진 XML 요소(eElement)에서 태그(tag)의 값을 가져옵니다.
	 * 
	 * @param tag      태그 이름
	 * @param eElement 태그 값을 가져올 XML 요소
	 * @return 추출된 태그의 값 또는 찾지 못한 경우 null
	 */
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
}