package com.spring.domain;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class F_P_DTO {
	private int favoritep_no;
    private String nickname;
    private Date favoritep_reg_date;
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
