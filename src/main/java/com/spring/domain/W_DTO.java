package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class W_DTO {
	private int with_pet_no;
	private String building; 
	private String category3; 
	private String road; 
	private String tel; 
	private String homepage; 
	private String day_off;
	private String hour; 
	private String parking; 
	private String with_pet_info; 
	private String only_pet_info; 
	private String pet_size; 
	private String pet_limit; 
	private String inside;
	private String outside; 
	private String extra;
	private String region;
	
	//검색필터
	private String type;
	private String keyword;
}
