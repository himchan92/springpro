package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.board.entity.Board;

@Controller
public class BoardController {
	
	@GetMapping("/boardList.do")
	public String boardList(Model model) {
		
		Board vo = new Board();
		vo.setIdx(1);
		vo.setTitle("게시판 실습");
		vo.setContent("게시판 테스트");
		vo.setWriter("정원준");
		vo.setIndate("2025-01-05");
		vo.setCount(0);
		
		List<Board> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
		
		model.addAttribute("list", list); //view단으로 데이터 전달하기위한 객체
		
		return "boardList"; // 내부 기본경로(출처: servlet-context.xml -> viewResolver) : WEB-INF/views/
	}
}