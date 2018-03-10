package trans_namoosori.sprout.java.base01;

/**
 * @author nulgraces
 *
 */
//	T) 1~10,20...100 중에 짝수의 합, 홀수의 합을 따로 구하기
public class Trans_Example15 {

	static int evenSum;
	static int oddSum;
	public Trans_Example15() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1; i<=100; i++) {
			sumEven(i);
			sumOdd(i);
			if(i%10==0) {
				System.out.println("1 ~ "+i+" : 짝수합 = "+evenSum+", 홀수합 = "+oddSum);
			}
		}
	}
	
	static void sumEven(int num) {
		if(num%2==0) {			
			evenSum += num;
		}
	}
	
	static void sumOdd(int num) {
		if(num%2!=0) {			
			oddSum += num;
		}
	}

}
