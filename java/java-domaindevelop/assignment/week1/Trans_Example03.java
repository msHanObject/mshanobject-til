package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
// T) 입력받은 값의 타입변화 출력
public class Trans_Example03 {

	static Scanner scanner;
	static String strValue;
	static int intValue;
	static double doubleValue;
	
	public Trans_Example03() {
		scanner = new Scanner(System.in);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example03 te03 = new Trans_Example03();
		
		System.out.println("ex)\n\t입력할 타입형: string");
		System.out.println("\t값 입력: 123.45");
		System.out.println("\t전환할 타입형: int");
		System.out.println("\tString=>Integer: 123\n");
		
		String inputType = inputType();
		int valueType = inputValue(inputType);
		String transType = transType();		
		dataOutput(valueType, transType);
		
	}
	
	public static String inputType() {
		
		System.out.print("입력할 타입형을 입력해주세요: ");
		String type = scanner.nextLine();
		
		return type;
	}
	
	public static int inputValue(String inputType) {
		System.out.print("값을 입력하세요 : ");
		if(typeStringCheck(inputType)) {
			strValue = scanner.nextLine();
			return 1;
		}else if(typeIntegerCheck(inputType)) {
			
			intValue = scanner.nextInt();
			scanner.nextLine();//버퍼 비우기
			return 2;
		}else if(typeRealNumberCheck(inputType)) {
			doubleValue = scanner.nextDouble();
			scanner.nextLine();
			return 3;
		}else {
			return 0;
		}
		
	}
	
	public static String transType() {
		System.out.print("전환할 타입형을 입력해주세요: ");
		String type = scanner.nextLine();
		
		return type;
	}
	
	
	public static boolean typeStringCheck(String type) {
		
		if(type.equals("String")|| type.equals("string") || type.equals("str")) {
			return true;
		}else {
			return false;
		}
		
	}
	public static boolean typeIntegerCheck(String type) {
		if(type.equals("Integer")|| type.equals("integer") || type.equals("int")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean typeRealNumberCheck(String type) {
		if(type.equals("Real Number")|| type.equals("real number") || type.equals("realnum")
				|| type.equals("double") || type.equals("Double")) {
			return true;
		}else {
			return false;
		}
	}
		
	public static void dataOutput(int valueType, String type) {
		
		if(valueType==1 && typeIntegerCheck(type)) {
			System.out.print("String=>Integer : " + (int) Double.valueOf(strValue).doubleValue());
		}else if (valueType==1 && typeRealNumberCheck(type)) {
			System.out.print("String=>RealNumber" + Double.valueOf(strValue));
		}
		else if (valueType==2 && typeStringCheck(type)) {
			System.out.print("Integer=>String : " + Integer.toString(intValue));
		}else if (valueType==2 && typeRealNumberCheck(type)) {
			System.out.print("Integer=>RealNumber : "+ (double)intValue);
		}
		else if (valueType==3 && typeStringCheck(type)) {
			System.out.print("Real Number=>String: " + Double.toString(doubleValue));
		}else if(valueType==3 && typeIntegerCheck(type)) {
			System.out.print("Real Number=>Integer : " + (int) doubleValue);
		}
		else if (valueType==0) {
			System.out.print("입력값이 없습니다.");
		}else {
			System.out.print("errors...");
		}
	}

}
