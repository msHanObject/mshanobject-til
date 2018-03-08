package trans_namoosori.sprout.java.base01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 동적 배열생성 기능 메소드화
public class Trans_Example19 {
	public Trans_Example19() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntArray ia = new IntArray();
		ia.setIndex();
		for(int i=1; i<=ia.index; i++) {
			ia.inputData(i);			
		}
		ia.sumArray();
		ia.averageArray();
		System.out.println("합계 : "+ia.sum);
		System.out.println("평균 : "+ia.average);
	}

}

class IntArray {
	int index;
	Scanner scanner;
	List<Integer> intList;
	int sum;
	double average;
	public IntArray() {
		super();
		// TODO Auto-generated constructor stub
		index =0; sum =0; average = 0;
		scanner = new Scanner(System.in);
		intList = new ArrayList<Integer>();
	}
	
	void setIndex() {
		System.out.print("계산할 정수의 갯수: ");
		index = scanner.nextInt();
	}
	
	void inputData(int n) {
		System.out.print(n+"번째 정수 입력: ");
		int data = scanner.nextInt();
		intList.add(data);
	}
	
	void sumArray() {
		for(int i : intList) {
			sum += i;
		}
	}
	
	void averageArray() {
		average = sum/index;
	}	
}