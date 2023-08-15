package com.spring.domain;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class FavoriteShelDTO {
	private int favorites_no;
    private String nickname;
    private Date favorites_reg_date;
    private int shelter_no;
	private String careNm; 
	private String divisionNm; 
	private String saveTrgtAnimal; 
	private String careAddr; 
	private String weekOprStime; 
	private String weekOprEtime;
	private String weekCellStime; 
	private String weekCellEtime; 
	private String weekendOprStime; 
	private String weekendOprEtime; 
	private String weekendCellStime; 
	private String weekendCellEtime; 
	private String closeDay;
	private String careTel;
	private String region;
}
