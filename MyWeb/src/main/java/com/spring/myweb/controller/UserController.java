package com.spring.myweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.MailSenderService;
import com.spring.myweb.util.PageCreator;
import com.spring.myweb.util.PageVO;

import lombok.extern.slf4j.Slf4j;

@Controller // 빈등록
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	@Autowired
	private IUserService service; //서비스에 자동으로 주입해줘~
	@Autowired
	private IFreeBoardService boardService;
	@Autowired
	private MailSenderService mailService;
	
	//회원가입 페이지로 이동
	@GetMapping("/userJoin")
	public void userJoin() {}
	
	//아이디 중복 확인(비동기)
	@ResponseBody
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String userId) {
		log.info("화면단으로부터 전달된 값:" + userId);
		
		int result = service.idCheck(userId);
		if(result == 1) return "duplicated";
		else return "ok";
	}
	
	//이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		log.info("이메일 인증 요청 들어옴: " + email);
		return mailService.joinEmail(email); //메일서비스한테 email 보내주고 joinemail해줘~
	}
	
	//회원 가입 처리
	@PostMapping("/join")
	public String join(UserVO vo, RedirectAttributes ra) {
		log.info("param; {}", vo.toString());
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess"); //일회성으로 리다이렉트할 때 보내는값
		return "redirect:/user/userLogin";
	}
	
	//로그인 페이지로 이동 요청
	@GetMapping("/userLogin")
	public void login() {}
	
	//로그인 요청
	@PostMapping("/userLogin")
	public void login(String userId, String userPw, Model model) {
		log.info("나는 UserController의 login이다~");
		model.addAttribute("user", service.login(userId, userPw));  //user라는 이름으로 서비스불러서 id pw login에 태우고 모델에 추가
	}
	
	//마이페이지 이동 요청
	@GetMapping("/userMypage")
	public void userMypage(HttpSession session, Model model, PageVO vo) { //userMypage url요청이 들어오면 세션을 주세요. 모델도.
		
		//세션 데이터에서 id를 뽑아야 sql을 돌릴 수 있지요??
		String id = (String) session.getAttribute("login"); //세션에서 로그인값을 받아옴.
		vo.setLoginId(id);
		PageCreator pc = new PageCreator(vo, boardService.getTotal(vo));
		model.addAttribute("userInfo", service.getInfo(id, vo));
		model.addAttribute("pc", pc);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}