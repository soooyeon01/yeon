package com.spring.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchCriteria extends Criteria{
	
	private String type;
	private String keyword;	
	
}
