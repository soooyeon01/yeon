package com.spring.api.domain;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.ToString;

@Getter
@Service
@ToString
public class WithpetDTO {
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
}
