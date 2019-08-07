package kr.hb.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.hb.board.domain.User;
import kr.hb.board.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "user/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "user/register";
	}

	// RedirectAttributes는 리다이렉트로 이동할 때 1번만 사용할 수 있는 데이터를
	// 저장할 목적으로 만든 Spring의 클래스
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	public String register(Model model, MultipartHttpServletRequest request, RedirectAttributes attr) {

		int result = userService.register(request);
		System.err.println("회원가입 메소드 결과값:" + result);
		// 회원가입에 성공한 경우
		if (result > 0) {
			attr.addFlashAttribute("insert", "success");
			return "redirect:/";
		} else {
			return "redirect:login";
		}
	}

	@RequestMapping(value = "user/login", method = RequestMethod.GET)
	public String login(Model model) {

		return "user/login";
	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, RedirectAttributes attr, HttpSession session) {
		User user = userService.login(request);
		// System.out.println("User:" + user);

		if (user == null) {
			attr.addFlashAttribute("msg", "아이디 혹은 비밀번호가 올바르지 않습니다.");
			return "redirect:login";
		} else {
			// 로그인을 성공했을 때는 로그인 정보를 Session에 저장
			session.setAttribute("user", user);
			// dest가 없으면 시작페이지로 있으면 그 페이지로 리다이렉트
			if (session.getAttribute("dest") == null) {
				return "redirect:/";
			} else {
				return "redirect:/" + session.getAttribute("dest").toString();
			}
		}

	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("user");

		return "redirect:/";
	}

}
