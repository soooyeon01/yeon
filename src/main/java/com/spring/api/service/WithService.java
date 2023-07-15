package com.spring.api.service;

import com.spring.api.domain.WithpetDTO;


public interface WithService {
	public int registerWithData(WithpetDTO wdto);
	
	public String makeApiCall(String apiUrl, String serviceKey);
	public WithpetDTO parseData(String response);
	
}
