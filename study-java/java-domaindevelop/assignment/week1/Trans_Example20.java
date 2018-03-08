package trans_namoosori.sprout.java.base01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) ArrayList 동적생성 및 기능 메소드화
public class Trans_Example20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntegerArr ia = new IntegerArr();
		ia.setIndex();
		for(int i=0; i<ia.index; i++) {
			ia.inputData();			
		}
		ia.setAscendingOrder();
		ia.showAscendingOrder();
		ia.setDescendingOrder();
		ia.showDescendingOrder();
	}

}
class IntegerArr {
	List<Integer> intList;
	int index;
	Scanner scanner;
	public IntegerArr() {
		super();
		// TODO Auto-generated constructor stub
		index=0;
		intList = new ArrayList<Integer>();
		scanner = new Scanner(System.in);
	}
	void setIndex() {
		System.out.println("입력할 정수 갯수: ");
		index = scanner.nextInt();
	}
	void inputData() {
		System.out.print("정수 입력 : ");
		int data = scanner.nextInt();
		intList.add(data);
	}
	void setAscendingOrder() {
		int temp;
		for (int i = 0; i < intList.size()- 1; i++) {
			for (int j = i + 1; j < intList.size(); j++) {
				if (intList.get(i)> intList.get(j)) {
					temp = intList.get(i);
					intList.set(i,intList.get(j));
					intList.set(j,temp);
				}
			}
		}
	}
	void showAscendingOrder() {
		System.out.println("== 오름차순 정렬 ==");
		for (int i = 0; i < intList.size(); i++) {
			System.out.print(intList.get(i) + "\t");
		}
		System.out.println();
	}
	void setDescendingOrder() {
		int temp;
		for (int i = 0; i < intList.size()- 1; i++) {
			for (int j = i + 1; j < intList.size(); j++) {
				if (intList.get(i)< intList.get(j)) {
					temp = intList.get(i);
					intList.set(i,intList.get(j));
					intList.set(j,temp);
				}
			}
		}
	}
	void showDescendingOrder() {
		System.out.println("== 내림차순 정렬 ==");
		for (int i = 0; i < intList.size(); i++) {
			System.out.print(intList.get(i) + "\t");
		}
		System.out.println();
	}
}