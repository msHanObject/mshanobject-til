package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	 T) 입력값의 범위를 파악하는 것으로 변경 (범위제한 없음)
public class Trans_Example07 {
	
	static Scanner scanner;
	
	public Trans_Example07() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example07 te07 = new Trans_Example07();
		
		System.out.println("파악하고 싶은 문자,숫자를 입력해주세요.");
		String inputData = scanner.nextLine();
		if(isDouble(inputData)) {
			if(isInteger(inputData)) {
				System.out.println("정수입니다.");
			}else {
				System.out.println("실수입니다.");
			}
		}else {
			System.out.println("문자입니다.");
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

}
