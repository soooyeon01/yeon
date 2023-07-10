package com.spring.test;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RestaurantTest {
	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant;
	@Test 
	public void testRestarunt() {
		assertNotNull(restaurant.getPeople());
		log.info("restaurant -" + restaurant);
		log.info("restaurant.people -" +restaurant.getPeople() );
	}
	
	@Test
	public void testChef() {
		assertNotNull(restaurant.getChef());
		log.info("restaurant.chef - " + restaurant.getChef());
	}
	@Test
	public void testChefPeople() {
		assertNotNull(restaurant.getChef());
		log.info("restaurant.chef - " + restaurant.getChef().getPeople());
	}
}

