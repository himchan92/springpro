package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;

@Controller //스프링컨테이너 컨트롤러 인식 명시
public class BoardController {
	
	@RequestMapping("/boardList.do") //핸들러 매핑이 화면단 요청을 컨트롤러에서 받아처리하게 연결다리 역할
	public String boardList(Model model) {
		
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판 실습");
		vo.setContent("게시판 실습");
		vo.setWriter("정원준");
		vo.setIndate("2026-01-25");
		vo.setCount(0);
		
		List<Board> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		
		//컨트롤러 데이터를 view 단으로 전달위해 model 객체이용해 바인딩처리
		model.addAttribute("list", list);
		return "boardList"; //뷰리졸버에 의해 연동(servlet-context.xml)되어 jsp로 포워딩 역할
	}
}