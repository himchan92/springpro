package kr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	
	public Member registerCheck(String memID); //아이디 중복검사
	public int register(Member m); //회원등록( 1, 0 )
}