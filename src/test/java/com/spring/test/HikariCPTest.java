package com.spring.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@RequiredArgsConstructor
public class HikariCPTest {
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	//@Test
	public void test() throws SQLException {
		assertNotNull(dataSource);
		log.info(dataSource);
		log.info(dataSource.getConnection());
	}
	
//	@Setter(onMethod_ = @Autowired)
//	private SqlSessionFactory sqlSessionFactory;
//	//@Test
//	public void testMyBatis() {
//		try(
//				SqlSession session = sqlSessionFactory.openSession();
//				Connection conn = session.getConnection();
//				){
//			assertNotNull(conn);
//			log.info("conn-"+conn);
//		}catch(Exception e) {
//			fail(e.getMessage());
//		}
//	}

}
