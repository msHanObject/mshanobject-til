package javastory.budget.entity.account;

import java.util.ArrayList;
import java.util.List;

import javastory.budget.share.IdName;

public class YearlyDue {
	//
	private static final String DEFAULT_DELIMITER = "||";
	private IdName member;
	private String year;
	private List<MonthlyDue> monthlyDues;
	
	public YearlyDue() {
		//
		monthlyDues = new ArrayList<>();
	}
	
	public YearlyDue(IdName member, String year) {
		//
		this();
		this.member = member;
		this.year = year;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		
		builder.append("YearlyDue").append(DEFAULT_DELIMITER);
		builder.append("memberId").append(DEFAULT_DELIMITER);
		builder.append(getMemberId()).append(DEFAULT_DELIMITER);
		builder.append("clubName").append(DEFAULT_DELIMITER);
		builder.append(getMemberName()).append(DEFAULT_DELIMITER);
		builder.append("year").append(DEFAULT_DELIMITER);
		builder.append(getYear()).append(DEFAULT_DELIMITER);
		builder.append("monthlyDues").append(DEFAULT_DELIMITER);
		builder.append(getMonthlyDues());
		
		return builder.toString();
	}
	
	public String getMemberId() {
		//
		return getMember().getId();
	}
	
	public void setMemberId(String id) {
		//
		member.setId(id);
	}
	
	public String getMemberName() {
		//
		return getMember().getName();
	}
	
	public void setMemberName(String name) {
		//
		member.setName(name);
	}
	
	public IdName getMember() {
		//
		return member;
	}
	
	public void setMember(IdName member) {
		//
		this.member = member;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public List<MonthlyDue> getMonthlyDues(){
		return monthlyDues;
	}
	
	public void setMonthlyDues(List<MonthlyDue> monthlyDues) {
		//
		this.monthlyDues = monthlyDues;
	}
}