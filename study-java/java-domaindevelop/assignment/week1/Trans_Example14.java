package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 입력받은 값에 따른 *트리 출력
public class Trans_Example14 {

	static Scanner scanner;
	static int row,column;
	
	public Trans_Example14() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example14  te14 = new Trans_Example14();
		inputData();
		matrix();

	}
	
	public static void inputData() {
		System.out.print("가로(행)값을 입력하세요: ");
		row = scanner.nextInt();
		System.out.print("세로(열)값을 입력하세요: ");
		column = scanner.nextInt();
	}
	
	public static void matrix() {
		char star='*';
		for(int i=0; i<column; i++) {
			for(int j=0; j<row; j++) {
				System.out.print(star);
			}
			System.out.println();
		}
	}

}
