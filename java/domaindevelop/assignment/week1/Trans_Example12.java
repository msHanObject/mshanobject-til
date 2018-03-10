package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 아무거나 입력 모두 출력, 숫자는 합산결과로 전환
public class Trans_Example12 {

	static String inputData, resultString;
	static int resultInteger;
	static Scanner scanner;
	public Trans_Example12() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
		resultString="";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example12 te12 = new Trans_Example12();
		
		inputMethod();
		System.out.println("입력받은 값 중 문자집합은 " +resultString);
		System.out.println("입력받은 값 중 숫자들의 합은 "+ resultInteger);
		
	}
	
	public static void inputMethod() {
		while(true) {
			System.out.println("문자나 숫자를 입력해주세요.");
			System.out.println("음수를 입력하면 종료합니다.");
			inputData = scanner.nextLine();
			
			if(separateMethod(inputData)) {
				
			}else {
				break;
			}
			
		}
	}
	
	public static boolean separateMethod(String inputData) {
		if(isInteger(inputData)) {
			int num=Integer.parseInt(inputData);
			if(num>=0) {
				resultInteger += num;
				return true;
			}else {
				return false;
			}
		
		}else {
			saveMethod(inputData);
			return true;
		}
	}
	public static void saveMethod(String inputData) {
		resultString += inputData;
		
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
