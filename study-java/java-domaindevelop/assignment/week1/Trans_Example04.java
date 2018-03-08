package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 타클래스 함수 인용
public class Trans_Example04 {

	static Scanner scanner;
	
	public Trans_Example04() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example04 te04 = new Trans_Example04();

		double distance = 0;
		double speed =0;
		String unit = "km";
		System.out.print("#주행거리("+unit+"단위) : ");
		distance = scanner.nextDouble();
		System.out.print("#주행시속("+unit+"/h단위) : ");
		speed = scanner.nextDouble();
		
		double time = distance/speed*3600;
		
		Trans_Example01 te01 = new Trans_Example01();
		te01.divideTime(time);
		System.out.println(" 소요됨");

	}

}
