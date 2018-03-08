package trans_namoosori.sprout.java.base01;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author nulgraces
 *
 */
//	T) 양의 정수,실수 / 음의 정수,실수 / 0 /영어 / 한글을 구분
public class Trans_Example08 {

	static Scanner scanner;
	
	public Trans_Example08() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example08 te08 = new Trans_Example08();
		
		System.out.print("파악할 것을 입력해주세요: ");
		String inputData = scanner.nextLine();
		
		if(isDouble(inputData)) {
			if(isInteger(inputData)) {
				if(Integer.parseInt(inputData)==0) {
					System.out.println("0 입니다.");
				}else {
					System.out.println("정수입니다.");					
				}
			}else {
				System.out.println("실수입니다.");
			}
		}else if(isHangle(inputData)) {
			System.out.print("한글입니다.");
		}else if(isEnglish(inputData)){
			System.out.print("영문입니다.");	
		}		
	}

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isHangle(String str) {
		
		return Pattern.matches("[가-힣]", str);
		
	}
	
	public static boolean isEnglish(String str) {
		try {
			Pattern.matches("[a-z]", str.toLowerCase());
			return true;
		}catch(ClassCastException e){
			return false;
		}
	}
}
