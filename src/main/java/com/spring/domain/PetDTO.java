package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PetDTO {
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
	private String region;
}
