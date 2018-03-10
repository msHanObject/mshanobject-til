package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
// T) 타 클래스 함수활용
public class Trans_Example02 {

	static Trans_Example01 te01;
	public Trans_Example02() {
		te01 = new Trans_Example01();		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example02 te02 = new Trans_Example02();
		double year = 365.2422;
		int day = (int) year;
		year = (year-day) *24*3600;	//시간단위를 '일'에서 '초'단위로 변경
		System.out.print("365.2422일은 " + day + " ");
		te01.divideTime(year);//소수점 둘째자리까지 출력되는 Trans_Example01 클래스의 divideTime함수

	}
	

}
