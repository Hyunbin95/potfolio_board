package kr.hb.board;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Spring 테스트 클래스를 만들어주는 어노테이션 설정
@RunWith(SpringJUnit4ClassRunner.class)
//스프링 설정 파일을 실행하기 위한 설정
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestBoard {


	@Autowired
	private SqlSession sqlSession;
	//private DataSource dataSource;
	
	/*
	@Test
	public void testConection() {
		try(Connection con = dataSource.getConnection()){
			System.out.println("connection:" + con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	@Test
	public void testSqlSession()throws Exception{
		System.err.println("sqlSession:" + sqlSession);
	}
	
}
