package com.spring.api.domain;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetnoticeDTO {
	private int pet_notice_no;
	private String happenDt; 
	private String happenPlace; 
	private String kindCd; 
	private String colorCd; 
	private String age; 
	private String weight;
	private String noticeNo; 
	private String noticeSdt; 
	private String noticeEdt; 
	private String popfile; 
	private String processState; 
	private String sexCd; 
	private String neuterYn;
	private String specialMark; 
	private String careNm; 
	private String careAddr; 
	private String careTel; 
}
