package com.spring.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class F_S_DTO {
	private int favorites_no;
    private String nickname;
    private Date favorites_reg_date;
    private int shelter_no;
    private String careNm; 
	private String careAddr; 
	private String careTel;
}
