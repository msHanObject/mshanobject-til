package trans_namoosori.sprout.java.base01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 입력받은 단수의 출력 범위 지정
public class Trans_Example13 {

	static Scanner scanner;
	static int firstDigits, secondDigits;
	static List<Integer> result;
	
	public Trans_Example13() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
		result = new ArrayList<Integer>();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example13 te13= new Trans_Example13();
		
		firstDigitsSetting();
		secondDigitsSetting();
		MultiplicationTable(firstDigits, secondDigits);
		showMultiplicationTable();
	}
	
	public static void firstDigitsSetting() {
		System.out.println("첫번째 수를 입력해주세요");
		firstDigits = scanner.nextInt();
	}
	
	public static void secondDigitsSetting() {
		System.out.println("두번째 수를 입력해주세요");
		secondDigits = scanner.nextInt();
	}
	
	public static void MultiplicationTable(int firstDigits, int secondDigits) {
		for(int i=0; i<secondDigits; i++) {
			result.add(firstDigits*(i+1));
		}
	}
	
	public static void showMultiplicationTable() {
		for(int i=0; i<result.size(); i++) {
			System.out.println(firstDigits+" * "+(i+1)+" = "+result.get(i));
		}
	}

}
