package kr.hb.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hb.board.domain.User;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public String emailcheck(String email) {
		return sqlSession.selectOne("user.emailcheck",email);
	}
	
	public String nicknamecheck(String nickname) {
		return sqlSession.selectOne("user.nicknamecheck",nickname);
	}
	
	//회원가입
	public int register(User user) {
		return sqlSession.insert("user.register", user);
	}
	
	//로그인
	public User login(String email) {
		//System.err.println("email:"+email);
		User user = sqlSession.selectOne("user.login", email);
		//System.err.println("User:" + user);
		return user;
	}
	
}
