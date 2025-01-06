package kr.board.mapper;

import java.util.List;

import kr.board.entity.Board;

public interface BoardMapper {
	public List<Board> getLists(); //인터페이스 - 동일명칭의 MyBATIS 생성
	public void boardInsert(Board vo); //글쓰기 등록
	public Board boardContent(int idx); //게시글 상세조회
	public void boardDelete(int idx); //게시글 삭제
}