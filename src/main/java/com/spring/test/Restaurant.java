package com.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Data
@RequiredArgsConstructor
public class Restaurant {
	@Setter(onMethod_ = @Autowired)
	//@Setter(onMethod = @__({@Autowired}))
	People people;
	
	@NonNull
	private Chef chef;
}


