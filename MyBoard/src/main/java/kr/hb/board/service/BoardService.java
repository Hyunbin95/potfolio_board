package kr.hb.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.hb.board.domain.Board;
import kr.hb.board.domain.SearchCriteria;

public interface BoardService {
	
	//게시판 글쓰기를 위한 메소드 선언
	public int register(HttpServletRequest request);

	//전체보기를 위한 메소드
	//public List<Board> list();
	
	//페이지를 출력해서 전체보기를 위한 메소드
	public Map<String, Object> list(SearchCriteria criteria);
	
	//글번호에 해당되는 글에 조회수를 1증가시켜주는 메소드
	//public int updateReadCnt(int bno);
	//DAO는 2개지만 사용자가 요청한 Service는 1개이므로 1개로 작업 
	//글번호에 해당되는 데이터를 전부가져오는 메소드
	public Board getData(int bno);
	
	//게시글 수정을 위한 메소드 
	public int update(Board board);
	
	//게시글 수정을 위해서 데이터를 찾아오는 메소드
	public Board updateView(int bno);
	
	//게시글을 삭제하기 위한 메소드
	public int delete(int bno);
}
