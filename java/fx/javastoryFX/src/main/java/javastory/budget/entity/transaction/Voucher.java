package javastory.budget.entity.transaction;

import javastory.budget.share.IdName;

public class Voucher {
	//
	private IdName voucher;
	private String businessRegistrationNumber;
	private IndustryType industryType;
	private int 	payment;
	private Paytype payType;
	private String foundationDate;				//(yyyy.mm.dd)
	
	public Voucher() {
		//
	}
		
	public IdName getVoucher() {
		return voucher;
	}

	public void setVoucher(IdName voucher) {
		this.voucher = voucher;
	}

	public String getBusinessRegistrationNumber() {
		return businessRegistrationNumber;
	}
	public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
		this.businessRegistrationNumber = businessRegistrationNumber;
	}
	public IndustryType getIndustryType() {
		return industryType;
	}
	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public Paytype getPayType() {
		return payType;
	}
	public void setPayType(Paytype payType) {
		this.payType = payType;
	}
	public String getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}
}
