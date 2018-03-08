package javastory.msClub2.stage3.step4.service;

import java.util.List;

import javastory.msClub2.stage3.step1.util.InvalidEmailException;
import javastory.msClub2.stage3.step4.service.dot.MemberDto;

public interface MemberService {
	//
	public void register(MemberDto member) throws InvalidEmailException;
	public MemberDto find(String memberId);
	public List<MemberDto> findByName(String memberName);
	public void modify(MemberDto member) throws InvalidEmailException;
	public void remove(String memberId);
}
