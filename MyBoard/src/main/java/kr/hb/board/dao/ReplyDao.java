package kr.hb.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hb.board.domain.Reply;

@Repository
public class ReplyDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int register(Reply reply) {
		//System.err.println("replyDao 호출");
		int result = sqlSession.insert("reply.register", reply);
		//System.err.println("Dao결과:" + result);
		return result;
	}
	
	//글 번호에 해당하는 댓글 목록을 가져오는 메소드
	public List<Reply> list(int bno){
		return sqlSession.selectList("reply.list", bno);
	}
	
	//글 번호를 가지고 삭제하는 메소드 
	public int delete(int rno) {
		return sqlSession.delete("reply.delete", rno);
	}
	
	//댓글을 수정하는 메소드
	public int update(Reply reply) {
		return sqlSession.update("reply.update", reply);
	}
	
}
