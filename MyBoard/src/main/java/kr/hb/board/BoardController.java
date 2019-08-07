package kr.hb.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.hb.board.domain.Board;
import kr.hb.board.domain.SearchCriteria;
import kr.hb.board.service.BoardService;

@Controller
// 모든 요청 처리 메소드의 value 앞에 이 내용이 추가
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardService boardService;


	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {

		return "board/register";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request,
			RedirectAttributes attr, Model model) {
		
		int result = boardService.register(request);
		
		if(result >0) {
			attr.addFlashAttribute("msg", "글쓰기 성공");
			return "redirect:list";
		}else {
			attr.addFlashAttribute("msg", "글쓰기 실패");
			return "redirect:register";
		}
	}
	
	/*
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Board> list = boardService.list();
		model.addAttribute("list",list);
		return "board/list";
	}
	*/
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	//@ModelAttribute("cri") SearchCriteria
	//파라미터를 criteria로 받아서 사용하고
	//결과 페이지에 cri라는 이름으로 넘겨주는 설정
	//public String list(Model model, Criteria criteria) 
	public String list(@ModelAttribute("cri") SearchCriteria criteria, Model model) {
		Map<String, Object> map = boardService.list(criteria);
		model.addAttribute("map", map);
		
		return "board/list";
	}
	
	
	
	@RequestMapping(value ="detail/{bno}", method=RequestMethod.GET)
	public String detail(Model model,
			@PathVariable("bno") int bno ) {
		//System.err.println("detail 메소드 호출");
		Board board = boardService.getData(bno);
		model.addAttribute("vo", board);
		return "board/detail";
	}
	
	@RequestMapping(value="update/{bno}", method=RequestMethod.GET)
	public String updateView(Model model,
			@PathVariable("bno") int bno) {
		Board board = boardService.getData(bno);
		model.addAttribute("vo", board);
		
		return "board/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Board board, Model model,
			RedirectAttributes attr) {
		int result = boardService.update(board);
		if(result > 0) {
			attr.addFlashAttribute("msg", "수정 성공");
			return "redirect:/board/list";
		}else {
			return "redirect:/board/update";
		}
	}
	
	@RequestMapping(value="delete/{bno}", method=RequestMethod.GET)
	public String delete(Model model, RedirectAttributes attr,
			@PathVariable("bno") int bno) {
		int result = boardService.delete(bno);
		if(result > 0 ) {
			attr.addFlashAttribute("msg", "삭제 성공");
			return "redirect:/board/list";
		}else {
			return "redirect:/board/delete";
		}
		
	}
	
}
