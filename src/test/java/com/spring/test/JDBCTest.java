package com.spring.test;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;
import lombok.extern.log4j.Log4j;
@Log4j
public class JDBCTest {
	static {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testConnection() {
		try(
				Connection conn = 
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
											,"C##SCOTT"
											,"tiger")
				){
			assertNotNull(conn);
			log.info("conn -" +conn);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
