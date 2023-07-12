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
    private String popfile;
    private String careNm; 
	private String careAddr; 
	private String careTel;
}
