package org.zerock.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;
import org.zerock.sample.Restaurant;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTest {
//	@Setter(onMethod_={@Autowired})
//	private Restaurant restaurant;
//	
//	@Test
//	public void testExist() {
//		assertNotNull(restaurant);
//		
//		log.info(restaurant);
//		log.info(restaurant.getChef());
	
//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testConnection() {
//		try(
//				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle"
//						,"ora_user","hong");
//				
//				){
//			log.info(con);
//			log.info("DB 연결됨");
//		}catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
	
//	@Setter(onMethod_= {@Autowired})
//	private DataSource dataSource;
//	
//	@Test
//	public void testConnection() {
//		try(
//				Connection con = dataSource.getConnection();
//				){
//			log.info(con);
//			log.info("db연결됨 (hikari dpcp)");
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
	
	@Setter(onMethod_=@Autowired)
	private TimeMapper TimeMapper;
	
	@Test
	public void testGetTime2() {
		log.info("getTime2");
		log.info(TimeMapper.getTime2());
	}
}
