package trans_namoosori.sprout.java.base01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 구구단 테이블의 동적 생성
public class Trans_Example18 {

	public Trans_Example18() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SettingTable settingTable = new SettingTable();
		settingTable.setting();
		settingTable.show();
	}

}
class MultiplicationTable{
	
	static int firstDigits;
	static int secondDigits;
	Scanner scanner;
	
	public MultiplicationTable() {
		super();
		// TODO Auto-generated constructor stub
		firstDigits = 0;
		secondDigits =0;
		scanner = new Scanner(System.in);
	}

	void firstDigitsSetting(int n) {
		firstDigits = n;
	}
	
	void secondDigitsSetting(int n) {
		secondDigits = n;
	}
	
	static List<Integer> makemT() {
		List<Integer> li = new ArrayList<Integer>();
		for(int i=0; i<secondDigits; i++) {
			li.add(firstDigits*(i+1));
		}
		return li;
	}
	
	String showmT() {
		
		List<String> ls = new ArrayList<String>();
		String table = "";
		for(int i=0; i<secondDigits; i++) {
			ls.add(firstDigits+" * "+(i+1)+" = "+MultiplicationTable.makemT().get(i)+"   ");
			table += ls.get(i)+"\n";
		}
		return table;
	}

}
class SettingTable {

	List<String> temp ;
	Scanner scanner;
	int num;
	MultiplicationTable mT;
	int standardNum,variableNum;
	public SettingTable() {
		super();
		// TODO Auto-generated constructor stub
		temp = new ArrayList<String>();
		scanner = new Scanner(System.in);
		mT = new MultiplicationTable();
	}
	
	void setting() {
		System.out.println("몇개의 구구단을 출력 하시겠습니까?");
		num = scanner.nextInt();
		
		for(int i=1; i<=num; i++) {
			System.out.print(i+"번째 구구단의 기준 숫자: ");
			standardNum = scanner.nextInt();
			mT.firstDigitsSetting(standardNum);
			
			System.out.print(i+"번째 구구단의 가변 숫자: ");
			variableNum = scanner.nextInt();
			System.out.println();
			mT.secondDigitsSetting(variableNum);
			
			temp.add(mT.showmT());
		}
	}
	void show() {
		for(int i=0; i<temp.size(); i++) {
			System.out.println(temp.get(i));
		}
	}
}