package javastory.club.stage3.step41.da.file;

import java.util.List;
import java.util.NoSuchElementException;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step4.store.MemberStore;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step41.da.file.io.MemberFile;

public class MemberFileStore implements MemberStore{
	//
	private MemberFile memberFileDb;
	
	public MemberFileStore() {
		//
		this.memberFileDb = new MemberFile();
	}

	@Override
	public String create(CommunityMember member) {
		// 
		if (memberFileDb.exists(member.getId())) {
			throw new MemberDuplicationException("Member already exists with email: " + member.getId());
		}
		
		memberFileDb.write(member);
		return member.getId();
	}

	@Override
	public CommunityMember retrieve(String memberId) {
		// 
		return memberFileDb.read(memberId);
	}

	@Override
	public List<CommunityMember> retrieveByName(String name) {
		// 
		return memberFileDb.readByName(name);
	}

	@Override
	public void update(CommunityMember member) {
		// 
		if (!memberFileDb.exists(member.getId())) {
			throw new NoSuchElementException("No such a member with email: " + member.getId());
		}
		
		memberFileDb.update(member);
	}

	@Override
	public void delete(String memberId) {
		// 
		memberFileDb.delete(memberId);
	}

	@Override
	public boolean exists(String memberId) {
		// 
		return memberFileDb.exists(memberId);
	}
}