package javastory.budget.ui.console;

import javastory.budget.logic.ServiceLogicLycler;
import javastory.budget.service.YearlyDueService;
import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.share.IdName;
import javastory.budget.util.ConsoleUtil;
import javastory.budget.util.NoSuchYearlyDueException;
import javastory.budget.util.YearlyDueDuplicationException;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.util.NoSuchMemberException;

public class YearlyDueConsole {
	//
	private ConsoleUtil consoleUtil;
	
	private YearlyDueService yearlyDueService;
	private MemberService memberService;
	
	private MemberDto currentMember;
	private YearlyDueDto currentYear;
	
	public YearlyDueConsole() {
		//
		memberService = ServiceLogicLycler.shareInstance().createMemberService();
		yearlyDueService = ServiceLogicLycler.shareInstance().createYearlyDueService();
		consoleUtil = new ConsoleUtil();
	}
	
	public void selectMember() {
		// 
		String memberEmail = consoleUtil.getValueOf("MemberId to select(0.AccountDueMenu) [Enter to Sample]");
		if (memberEmail.equals("0")) {
			return;
		}
		if (memberEmail.isEmpty()) {
			currentMember = MemberDto.getSample();
			return;
		}
		
		try {
			//
			currentMember = memberService.find(memberEmail);
			System.out.println("Selected member:  " + currentMember.toString());
		} catch (NoSuchMemberException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void add() {
		// 
		YearlyDueDto yearlyDueDto;
		
		String year = consoleUtil.getValueOf("Year to add(0.YearlyDue menu)");
		if (year.equals("0")) {
			return;
		}
		if (year.isEmpty()) {
			System.out.println("year can't be left blank.");
			return;
		}

		IdName member = new IdName(currentMember.getEmail(), currentMember.getName());
		
		try {
			yearlyDueDto = new YearlyDueDto(member, year);
			yearlyDueService.add(yearlyDueDto);
			System.out.println("Add yearly due: " + year);
		} catch (YearlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void find() {
		// 
		while(true) {
			//
			String yearFound = consoleUtil.getValueOf("Year to find(0.YearlyDue menu)");
			if (yearFound.equals("0")) {
				return;
			}
			if (yearFound.isEmpty()) {
				System.out.println("Year can't be a left blank.");
				return;
			}
			
			try {
				currentYear = yearlyDueService.retrieve(yearFound);
				System.out.println("Found yearlyDue: " + currentYear.toString());
			} catch (NoSuchYearlyDueException e ) {
				System.out.println(e.getMessage());
			}
		}
	}

	public YearlyDueDto findOne() {
		//
		YearlyDueDto targetYearlyDue = null;
		while (true)	{
			//
			String yearFound = consoleUtil.getValueOf("Year to select(0.YearlyDue menu)");
			if (yearFound.equals("0")) {
				break;
			}
			if (yearFound.isEmpty()) {
				System.out.println("Year can't be a left blank.");
				break;
			}
			
			try {
				targetYearlyDue= yearlyDueService.retrieve(yearFound);
				System.out.println("Found yearlyDue: " + targetYearlyDue.toString());
				break;
			} catch (NoSuchYearlyDueException e ) {
				System.out.println(e.getMessage());
			}				
		}
		return targetYearlyDue;
	}
	
	public void modify() {
		// 
		YearlyDueDto target = findOne();
		if (target == null) {
			System.out.println("There is no exist target.");
			return;
		}
		
		String newYear = consoleUtil.getValueOf("Year to modify(0.YearlyDue menu)");
		if (newYear.equals("0")) {
			return;
		}
		if (newYear.isEmpty()) {
			System.out.println("Year can't be left blank.");
		}
		
		try {
			target.setYear(newYear);
			yearlyDueService.modify(target);
			System.out.println("Modified year to --> " + newYear);
		} catch (YearlyDueDuplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	public void delete() {
		// 
		YearlyDueDto target = findOne();
		if (target == null) {
			System.out.println("There is no exist target.");
			return;
		}
		
		String confirmStr = consoleUtil.getValueOf("Do you want delete the target?(Yes: y, No: n)");
		if (confirmStr.toLowerCase().equals("yes") || confirmStr.toLowerCase().equals("y")) {
			yearlyDueService.delete(target.getYear());
			System.out.println("Deleted target: " + target.getYear());
		}
		if (confirmStr.toLowerCase().equals("no") || confirmStr.toLowerCase().equals("n")) {
			System.out.println("Cancled deleting target.");
		}
	}

	public MemberDto requestCurrentMember() {
		//
		if (currentMember == null) {
			selectMember();
		}
		return currentMember;
	}
	
	public boolean hasMember() {
		if (currentMember != null) {
			return true;
		}
		return false;
	}

	public YearlyDueDto requestCurrentYear() {
		// 
		return currentYear;
	}
}