package trans_namoosori.sprout.java.base01;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) +, -, *, / , 배수, 제곱 동적 생성
public class Trans_Example05 {

	public int resultInt;
	public double resultDouble;
	public static Scanner scanner;
	public int[] arrayInt;
	
	public Trans_Example05() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example05 te05 = new Trans_Example05();
		System.out.print("입력할 정수의 갯수 : ");
		int number = scanner.nextInt();
		te05.inputValue(number);
		te05.showResult();

	}

	public void inputValue(int count){
		//배열 동적생성
		arrayInt = new int[count];
		System.out.println("(입력형태)");
		for(int i=0; i<count; i++){
			int n = i+1;
			System.out.print( n+" 번째 정수 입력 : ");
			arrayInt[i] = scanner.nextInt();
			scanner.nextLine();//버퍼 비우기
		}
	}
	
	public void showResult(){
		
		System.out.println("(출력결과)");
		addInteger(arrayInt.length);
		subInteger(arrayInt.length);
		mulInteger(arrayInt.length);
		divInteger(arrayInt.length);
		multiplesOfNum(arrayInt.length);
		squareOfNum(arrayInt.length);
	}
	
	public void addInteger(int num){
		
		for(int i=0; i<num-1; i++){
			resultInt += arrayInt[i];
			System.out.print(arrayInt[i]+ "+"); 
		}
		if(num == 1){
			System.out.println("입력하신 정수의 갯수가 1개 이므로 계산 결과값은 없습니다.");
		}
		resultInt += arrayInt[num-1];
		System.out.println(arrayInt[num-1]+"="+resultInt);
		
	}
	
	public void subInteger(int num){
		//초기결과값 첫번째 항목으로 지정
		resultInt = arrayInt[0];
		System.out.print(arrayInt[0]+ "-"); 
		for(int i=1; i<num-1; i++){
			resultInt = resultInt - arrayInt[i];
			System.out.print(arrayInt[i]+ "-"); 
		}
		if(num == 1){
			System.out.println("입력하신 정수의 갯수가 1개 이므로 계산 결과값은 없습니다.");
		}
		resultInt = resultInt - arrayInt[num-1];
		System.out.println(arrayInt[num-1]+"="+resultInt);
	}
	
	public void mulInteger(int num){
		//초기결과값 첫번째 항목으로 지정
		resultInt = arrayInt[0];
		System.out.print(arrayInt[0]+ "*"); 
		for(int i=1; i<num-1; i++){
			resultInt *= arrayInt[i];
			System.out.print(arrayInt[i]+ "*"); 
		}
		if(num == 1){
			System.out.println("입력하신 정수의 갯수가 1개 이므로 계산 결과값은 없습니다.");
		}
		resultInt *= arrayInt[num-1];
		System.out.println(arrayInt[num-1]+"="+resultInt);
	}
	
	public void divInteger(int num){
		//초기결과값 첫번째 항목으로 지정
		resultDouble = (double)arrayInt[0];
		System.out.print(arrayInt[0]+ "/"); 
		String pattern = "0.00";
		DecimalFormat dformat = new DecimalFormat(pattern);
		for(int i=1; i<num-1; i++){
			resultDouble /=(double)arrayInt[i];
			System.out.print(arrayInt[i]+ "/"); 
		}
		if(num == 1){
			System.out.println("입력하신 정수의 갯수가 1개 이므로 계산 결과값은 없습니다.");
		}
		resultDouble /= (double)arrayInt[num-1];
		System.out.println(arrayInt[num-1]+"="+ dformat.format(resultDouble));
		
	}
	
	public void multiplesOfNum(int count){
		System.out.print("계산할 배수 입력 : ");
		int mul = scanner.nextInt();
		for(int i=0; i<count; i++){
			resultInt = arrayInt[i] * mul;
			System.out.println(arrayInt[i]+ "의 "+mul+"배수 => "+resultInt);
		}
	}
	
	public void squareOfNum(int count){
		double n=2;
		for(int i=0; i<count; i++){
			resultDouble = Math.pow((double)arrayInt[i], n);
			System.out.println(arrayInt[i]+ "의 제곱 => "+resultDouble);
		}
	}
	
}
