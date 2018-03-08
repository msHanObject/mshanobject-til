package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 입력 값의 배수 찾기  
public class Trans_Example10 {

	public Trans_Example10() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Trans_Example10 te10 = new Trans_Example10();
		int inputData=0;
		System.out.println("정수를 입력해주세요.");
		inputData= scanner.nextInt();
		
		System.out.println(inputData+"의 최대 공약수는 "+findMaxMultiple(inputData));
		
	}	
	public static int findMaxMultiple(int inputData) {
		int aliquot=0;
		for(int i=1; i<inputData; i++) {			
			if(inputData%i == 0) {
				aliquot=i;
			}
		}
		return aliquot;
	}
}