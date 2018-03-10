package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) loop 함수화
public class Trans_Example11 {

	static Scanner scanner;
	static int inputInt;
	public Trans_Example11() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example11 te11 = new Trans_Example11();
		inputLoop();		
		System.out.println("결과 : "+calculateLoop(inputInt));
	}
	public static void inputLoop() {
		while(true) {
			System.out.print("#양수 입력: ");
			inputInt = scanner.nextInt();
			
			if(inputInt >0) {
				break;
			}
		}
	}
	public static int calculateLoop(int inputInt) {
		int returnValue = 0;
		while (inputInt>0) {
			returnValue += inputInt--;
		}
		return returnValue;
	}

}
