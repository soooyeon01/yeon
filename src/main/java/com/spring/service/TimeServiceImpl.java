package com.spring.service;



import org.springframework.stereotype.Service;
import com.spring.mapper.TimeMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {
	private final TimeMapper mapper;
	@Override
	public String getTime2() {
		
		return mapper.getTime2();
	}

}


