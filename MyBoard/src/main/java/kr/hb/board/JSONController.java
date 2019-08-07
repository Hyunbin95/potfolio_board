package kr.hb.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.hb.board.domain.Reply;
import kr.hb.board.service.ReplyService;
import kr.hb.board.service.UserService;

@RestController
public class JSONController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "user/emailcheck", method = RequestMethod.GET)
	public Map<String, Object> emailcheck(@RequestParam("email") String email) {
		String result = userService.emailcheck(email);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("result", result == null);

		return map;
	}

	@RequestMapping(value = "user/nicknamecheck", method = RequestMethod.GET)
	public Map<String, Object> nicknamecheck(@RequestParam("nickname") String nickname) {
		String nicknameResult = userService.nicknamecheck(nickname);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nicknameResult", nicknameResult == null);

		return map;
	}

	// 위도와 경도 문자열을 받아서 주소를 리턴하는 요청을 처리하는 메소드
	@RequestMapping(value = "address", method = RequestMethod.GET)
	public Map<String, String> address(@RequestParam("param") String param) {
		Map<String, String> map = new HashMap<>();
		String address = userService.convertAddress(param);
		map.put("address", address);

		return map;

	}
	
	@RequestMapping(value = "/reply/register", method=RequestMethod.GET)
	public Map<String, Object> replyRegister(
			HttpServletRequest request){
		//System.out.println("reply 호출");
		int result = replyService.register(request);
		//System.err.println("result:" + result);
		Map<String,Object> map = new HashMap<>();
		if(result>0) {
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		
		return map;
	}

	@RequestMapping(value="/reply/list", method=RequestMethod.GET)
	public List<Reply> replyList(@RequestParam("bno") int bno){
		return replyService.list(bno);
	}
	
	@RequestMapping(value="/reply/delete", method=RequestMethod.GET)
	public Map<String, Object> delete(@RequestParam("rno") int rno){
		int result=replyService.delete(rno);
		Map<String,Object> map = new HashMap<>();
		map.put("result", result>0);
		return map;
	}
	
	@RequestMapping(value="/reply/update", method=RequestMethod.GET)
	public Map<String,Object> update(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		int result = replyService.update(request);
		if(result>0) {
			map.put("result",true);
		}else {
			map.put("result",false);
		}
		return map;
	}
	

}
