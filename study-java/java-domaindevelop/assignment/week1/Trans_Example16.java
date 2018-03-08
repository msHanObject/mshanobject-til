package trans_namoosori.sprout.java.base01;

/**
 * @author nulgraces
 *
 */
//	T) 별 트리를 각각 클래스화 -> 각 클래스를 합쳐서 대칭화
public class Trans_Example16 {

	static Startree_LURD lurd;
	static Startree_LDRU ldru;
	String tree[][];
	public Trans_Example16() {
		super();
		// TODO Auto-generated constructor stub
		lurd = new Startree_LURD();
		ldru = new Startree_LDRU();
		tree = new String[7][14];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example16 te16 = new Trans_Example16();
		lurd.init();
		lurd.makeTree();
		
		ldru.init();
		ldru.makeTree();
		
		te16.sumTree(lurd.tree, ldru.tree);
		te16.outputTree(te16.tree);
	}
	
	void sumTree(String tree1[][], String tree2[][]) {
		for(int i=0; i<this.tree.length; i++) {
			for (int j=0; j<tree1[i].length; j++) {
				this.tree[i][j]=tree1[i][j];
			}
			for( int z=tree1.length; z<tree1.length+tree2.length; z++) {
				this.tree[i][z]=tree2[i][z-7];
			}
		}
	}
	void outputTree(String tree[][]) {
		for(int i=0; i<7; i++) {
			for(int j=0; j<14; j++) {
				System.out.print(tree[i][j]);
			}
			System.out.println();
		}
	}

}
class Startree_LURD{//LURD:Left Up Right Down 
	String star;
	String tree[][];
	
	public Startree_LURD() {
		super();
		// TODO Auto-generated constructor stub
		star="*";
		tree = new String[7][7];
	}
	void init() {
		for (int i=0; i<7; i++) {
			for(int j=0; j<7; j++) {
				tree[i][j]=" ";
			}
		}
	}
	void makeTree() {
		for(int col=0; col<7; col++) {
			for(int row=0; row<col+1; row++) {
				
				tree[col][row]=star;
			}
			
		}
	}
	void outputTree() {
		for(int i=0; i<7; i++) {
			for(int j=0; j<7; j++) {
				System.out.print(tree[i][j]);
			}
			System.out.println();
		}
	}
}
class Startree_LDRU{//LDRU:Left Down Right Up 
	String star;
	String tree[][];
	
	public Startree_LDRU() {
		super();
		// TODO Auto-generated constructor stub
		star ="*";
		tree = new String[7][7];
	}

	void init() {
		for (int i=0; i<7; i++) {
			for(int j=0; j<7; j++) {
				tree[i][j]=" ";
			}
		}
	}
	
	void makeTree() {
		for(int col=0; col<7; col++) {
			for(int row=7-col-1; row<7; row++) {
				tree[col][row]=star;
			}
		}
	}
	void outputTree() {
		for(int i=0; i<7; i++) {
			for(int j=0; j<7; j++) {
				System.out.print(tree[i][j]);
			}
			System.out.println();
		}
	}
}