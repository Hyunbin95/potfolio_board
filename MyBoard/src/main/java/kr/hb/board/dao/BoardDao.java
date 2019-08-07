package kr.hb.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hb.board.domain.Board;
import kr.hb.board.domain.Criteria;
import kr.hb.board.domain.SearchCriteria;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//게시글 작성을 위한 메소드
	public int register(Board board) {
		return sqlSession.insert("board.register", board);
	}
	
	/*
	//전체목록보기를 가져오는 메소드
	public List<Board> list(){
		return sqlSession.selectList("board.list");
	}
	*/
	
	public List<Board> list(SearchCriteria cri){
		return sqlSession.selectList("board.list", cri);
	}
	
	//선택한 글의 조회수를 1증가시켜주는 메소드
	public int updateReadCnt(int bno) {
		return sqlSession.update("board.updatereadcnt", bno);
	}
	
	//글번호에 해당하는 데이터를 전부 가져오는 메소드
	public Board getData(int bno) {
		return sqlSession.selectOne("board.getdata", bno);
	}
	
	//작성한 글을 수정해주는 메소드
	public int update(Board board) {
		return sqlSession.update("board.update", board);
	}
	
	//데이터를 삭제해주는 메소드
	public int delete(int bno) {
		return sqlSession.delete("board.delete", bno);
	}
	
	//전체 데이터를 가져오는 메소드
	public int totalCount(SearchCriteria criteria) {
		return sqlSession.selectOne("board.totalcount", criteria);
	}
	
	//글 번호에 해당하는 댓글 개수를 가져오는 메소드
	public int replycnt(int bno) {
		return sqlSession.selectOne("board.replycnt", bno);
	}
	
	
}
