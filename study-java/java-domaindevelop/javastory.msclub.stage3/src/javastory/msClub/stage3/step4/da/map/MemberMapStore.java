package javastory.msClub.stage3.step4.da.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javastory.msClub.stage3.step1.entity.club.CommunityMember;
import javastory.msClub.stage3.step4.da.map.io.MemoryMap;
import javastory.msClub.stage3.step4.store.MemberStore;
import javastory.msClub.stage3.step4.util.MemberDuplicationException;

public class MemberMapStore implements MemberStore {
	//
	private Map<String,CommunityMember> memberMap;

	public MemberMapStore() {
		//
		this.memberMap = MemoryMap.getInstance().getMemberMap();
	}

	@Override
	public String create(CommunityMember member) {
		//
		if (memberMap.get(member.getId()) != null) {
			throw new MemberDuplicationException("Member already exists with email: " + member.getId());
		}

		memberMap.put(member.getId(), member);
		return member.getId();
	}

	@Override
	public CommunityMember retrieve(String memberId) {
		//
		return memberMap.get(memberId);
	}

	@Override
	public List<CommunityMember> retrieveByName(String name) {
		//
		List<CommunityMember> members = new ArrayList<>();
		Iterator<CommunityMember> memberIter = memberMap.values().iterator();
		while(memberIter.hasNext()) {
			CommunityMember member = memberIter.next();
			if (member.getName().equals(name)) {
				members.add(member);
			}
		}

		return members;
	}

	@Override
	public void update(CommunityMember member) {
		//
		if (memberMap.get(member.getId()) == null) {
			throw new NoSuchElementException("No such a member with email: " + member.getId());
		}

		memberMap.put(member.getId(), member);
	}

	@Override
	public void delete(String memberId) {
		//
		memberMap.remove(memberId);
	}

	@Override
	public boolean exists(String memberId) {
		//
		if (memberMap.get(memberId) != null) {
			return true;
		}

		return false;
	}
}
