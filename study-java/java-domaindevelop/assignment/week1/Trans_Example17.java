package trans_namoosori.sprout.java.base01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 트리를 이중 ArrayList에 담아서 출력 및 알파벳 'A'부터 시작
public class Trans_Example17 {
	
	static alpabetTree_LURD at_lurd;
	public Trans_Example17() {
		super();
		// TODO Auto-generated constructor stub
		at_lurd = new alpabetTree_LURD();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example17 te17 = new Trans_Example17();
		at_lurd.init();
		at_lurd.makeTree();
		at_lurd.outputTree();
		
	}

}

class alpabetTree_LURD{//LURD:Left Up Right Down 
	String alpa;
	List<String> tree;
	List<List<String>> treeList;
	Scanner scanner;
	int size;
	public alpabetTree_LURD() {
		super();
		// TODO Auto-generated constructor stub
		alpa="A";
		tree = new ArrayList<String>();
		treeList = new ArrayList<List<String>>();
		scanner = new Scanner(System.in);
	}
	void init() {
		System.out.println("matrix의 크기를 정해주세요.");
		size = scanner.nextInt();
		for(int i=0; i<size; i++) {
			tree.add(" ");
		}
		for(int i=0; i<size; i++) {
			treeList.add(tree);
		}
	}
	void makeTree() {
		char ch;
		for(int col=0; col<size; col++) {
			for(int row=0; row<=col; row++) {
				List<String> temp = new ArrayList<String>();
				tree.set(row, alpa);
				temp.addAll(tree);
				ch =alpa.charAt(0);
				ch = (char) (ch+1);
				alpa =String.valueOf(ch);
				treeList.set(col, temp);
			}
			alpa ="A";
		}
	}
	void outputTree() {
		for(int i=0; i<treeList.size(); i++){
			for(int j=0; j<tree.size(); j++) {
				
				System.out.print(treeList.get(i).get(j));
			}
			System.out.println();
		}
	}
}