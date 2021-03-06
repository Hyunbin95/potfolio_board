package kr.hb.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hb.board.dao.ReplyDao;
import kr.hb.board.domain.Reply;
import kr.hb.board.domain.User;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	
	@Override
	public int register(HttpServletRequest request) {
		int result = -1;
		//파라미터 읽어오기
		String replyText = request.getParameter("replyText");
		//System.err.println("replyText:" + replyText);
		int bno = Integer.parseInt(request.getParameter("bno"));
		//접속중인 유저의 email과 nickname 가져오기
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String email = user.getEmail();
		String nickname = user.getNickname();
		//DAO 매개 변수 만들기
		Reply reply = new Reply();
		reply.setEmail(email);
		reply.setNickname(nickname);
		reply.setBno(bno);
		reply.setReplyText(replyText);
		//System.err.println("reply:" + reply);
		result = replyDao.register(reply);
		return result;
	}


	@Override
	public List<Reply> list(int bno) {
		
		return replyDao.list(bno);
	}


	@Override
	public int delete(int rno) {
		return replyDao.delete(rno);
	}
	
	@Override
	public int update(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int rno = Integer.parseInt(request.getParameter("rno"));
		String replyText = request.getParameter("replytext");
		User user = (User)session.getAttribute("user");
		String nickname = user.getNickname();
		//DAO 매개변수 만들기
		Reply reply = new Reply();
		reply.setNickname(nickname);
		reply.setReplyText(replyText);
		reply.setRno(rno);
		System.err.println("reply:" + reply);
		
		return replyDao.update(reply);
	}

}
