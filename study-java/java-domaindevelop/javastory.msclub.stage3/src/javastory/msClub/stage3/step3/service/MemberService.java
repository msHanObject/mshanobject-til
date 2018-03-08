package javastory.msClub.stage3.step3.service;

import java.util.List;

import javastory.msClub.stage3.step1.util.InvalidEmailException;
import javastory.msClub.stage3.step3.service.dto.MemberDto;

public interface MemberService {
	//
	public void register(MemberDto member) throws InvalidEmailException;
	public MemberDto find(String memberId);
	public List<MemberDto> findByName(String memberName);
	public void modify(MemberDto member) throws InvalidEmailException;
	public void remove(String memberId);
}
