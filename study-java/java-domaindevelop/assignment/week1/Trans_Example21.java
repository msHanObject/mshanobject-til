package trans_namoosori.sprout.java.base01;

import java.util.Random;

/**
 * @author nulgraces
 *
 */
//	T) scanner 입력 대신 random 클래스로 대신
public class Trans_Example21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] intAry = new int[3][4];
		int sum = 0;

		Random random = new Random();
		

		for (int i = 0; i < intAry.length; i++) {
			for (int j = 0; j < intAry[0].length; j++) {
				System.out.print("intAry[" + i + "][" + j + "] = ");
				int r = random.nextInt(100);
				intAry[i][j] = r;
				System.out.println(intAry[i][j]);
			}
		}

		for (int i = 0; i < intAry.length; i++) {
			System.out.printf("%2d row  :", i);
			sum = 0;
			for (int j = 0; j < intAry[0].length; j++) {
				System.out.printf("%5d", intAry[i][j]);
				sum += intAry[i][j];
			}
			System.out.printf("\tSum = %d\n", sum);
		}

		System.out.print("Col Sum :");
		for (int i = 0; i < intAry[0].length; i++) { /* 열을 제어하는 반복문 */
			sum = 0;
			for (int j = 0; j < intAry.length; j++) { /* 행을 제어하는 반복문 */
				sum += intAry[j][i];
			}
			System.out.printf("%5d", sum);
		}
		System.out.println();
	}

}
