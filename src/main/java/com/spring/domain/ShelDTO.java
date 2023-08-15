package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShelDTO {
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
