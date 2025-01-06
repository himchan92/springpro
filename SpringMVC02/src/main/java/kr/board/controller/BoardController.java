package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired //이미 만들어진 객체를 주입(스프링 컨테이너가 BoardMapper 객체를 메모리에서 찾아 연결하여 구현체를 사용)
	private BoardMapper mapper;
	
	//게시글 조회
	@GetMapping("/boardList.do")
	public String boardList(Model model) {
		
		List<Board> list = mapper.getLists(); //DAO통해서 DB 결과값 가져와 list에 담기
		model.addAttribute("list", list); //view단으로 데이터 전달하기위한 객체
		
		return "boardList"; // 내부 기본경로(출처: servlet-context.xml -> viewResolver) : WEB-INF/views/
	}
	
	//글쓰기 양식화면
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	
	//글쓰기 등록처리
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { //파라미터 일괄 수집 후 전달
		
		mapper.boardInsert(vo); //DB 등록처리 호출
		return "redirect:/boardList.do"; //해당 페이지로 이동(redirect:/)
	}
	
	//게시글 상세 조회
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) {		
		Board vo = mapper.boardContent(idx);
		
		//클릭 시 조회수 증가 처리
		mapper.boardCount(idx);
		
		model.addAttribute("vo", vo);
		return "boardContent"; //상세보기화면
	}
	
	//게시글 삭제처리
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		
		mapper.boardDelete(idx); //삭제처리
		return "redirect:/boardList.do";		
	}
	
	//게시글 수정 폼
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		
		return "boardUpdate";
	}
	
	//게시글 수정 처리
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		
		mapper.boardUpdate(vo); //수정 처리
		return "redirect:/boardList.do";
	}
}