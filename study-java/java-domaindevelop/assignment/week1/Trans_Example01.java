package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
// T) 시간 나누기 함수화 및 시간값 받기
public class Trans_Example01 {

	public static Scanner scanner;
	
	public Trans_Example01() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example01 te01 = new Trans_Example01();
		System.out.print("계산할 시간값을 넣어주세요 : ");
		double time = scanner.nextDouble();
		System.out.print(time + "초는 ");
		divideTime(time);
		System.out.println("입니다.");
	}
	
	public static void divideTime(double time) {
		
		String timeName[] = {"시간", "분", "초"};//시간 표현 포맷
				
		for(int i=0; i<timeName.length; i++) {
						
			double timeVal = Math.pow(60, 2-i);//timeVal는 시,분,초를 초기준으로 하였을때 값
			double quotient = time/timeVal;//quotient는 입력받은 시간을 시간값인 timeVal로 나누었을 때 몫
			int timeQuotient = (int)quotient;//double형인 quotient를 int형으로 바꿔줌
						
			time = time - ((timeQuotient)*timeVal);//계산된 시간포맷의 나머지 시간값
			if(i<timeName.length-1) {
				System.out.print(timeQuotient+timeName[i]+" ");
			}else {
				System.out.printf("%.2f",quotient);
				System.out.print(timeName[i]);
			}			
		}
	}

}


