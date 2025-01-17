package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	
	@RequestMapping("/memJoin.do")
	public String memJoin() {
		return "member/join";
	}
	
	//아이디 중복 체크
	@RequestMapping("/memRegisterCheck.do")
	public @ResponseBody int memRegisterCheck(@RequestParam("memID") String memID) {
		
		Member m = mapper.registerCheck(memID);
		
		if(m != null || memID.equals("")) {
			return 0; //이미 존재하는 회원
		}
		
		return 1; //사용 가능한 회원
	}
	
	//회원가입처리
	@RequestMapping("/memRegister.do")
	public String memRegister(Member m, RedirectAttributes rttr, HttpSession session) {
		if(m.getMemID() == null || m.getMemID().equals("") ||
		   m.getMemPassword() == null || m.getMemPassword().equals("") ||
		   m.getMemName() == null || m.getMemName().equals("") ||
		   m.getMemAge() == 0 ||
		   m.getMemGender() == null || m.getMemGender().equals("") ||
		   m.getMemEmail() == null || m.getMemEmail().equals("")) {
			
			//누락메세지를 가지고 가기
			rttr.addFlashAttribute("msgType", "누락 메세지"); //메세지 처리
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요."); //메세지 처리
			return "redirect:/memJoin.do";
		}
		
		m.setMemProfile(""); //사진없을경우
		
		// 회원을 테이블에 저장하기
		int result = mapper.register(m);
		
		if(result == 1) { //성공 시 
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원 가입에 성공했습니다.");
			// 회원가입이 성공하면 => 로그인 처리하기
			session.setAttribute("m", m);
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "이미 존재하는 회원 입니다.");
			return "redirect:/memJoin.do";
		}				
	}
}
