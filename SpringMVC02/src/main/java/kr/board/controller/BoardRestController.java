package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@RequestMapping("/board") //공통 경로 설정
@RestController //controller + responseBody, 모든 API를 JSON반환
public class BoardRestController {
	
	@Autowired
	private BoardMapper mapper;
	
	@GetMapping("/all")
	public List<Board> boardList() {		
		List<Board> list = mapper.getLists();		
		return list; //JSON 형식으로 응답
	}
	
	@PostMapping("/new")
	public void boardInsert(Board vo) {
		mapper.boardInsert(vo); //등록 성공
	}
	
	@DeleteMapping("/{idx}")
	public void boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
	}
	
	@PutMapping("/update")
	public void boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
	}
	
	@GetMapping("/{idx}")
	public Board boardContent(@PathVariable("idx") int idx) {	
		Board vo = mapper.boardContent(idx);
		
		return vo;
	}
	
	@PutMapping("/count/{idx}")
	public Board boardCount(@PathVariable("idx") int idx) {
		mapper.boardCount(idx); //조회수 증가 호출
		Board vo = mapper.boardContent(idx);		
		return vo;
	}
}