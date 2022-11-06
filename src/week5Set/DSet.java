package week5Set;
/**
 * Disjoint Set�� Tree ���(Rank��� / ��ξ���)���� ����
 * 
 * @author ������
 *
 */
public class DSet {
	/**
	 * key = data
	 * rank = �ش� Node�� ����
	 * parent = �ش� Node�� �θ� Node
	 */
	int key;
	int rank;
	DSet parent;

	/**
	 * DSet Node ����
	 */
	public DSet() {
		key = -1;
		rank = -1;
		parent = null;
	}
	
	/*
	 * DSet Node�� key�� �ٸ� DSet Node�� key�� ��
	 * 
	 * key�� ���� ���� �� true��,  key�� ���� �ٸ� �� false�� return
	 */
	public boolean equals(DSet other) {
		if(key == other.key)
			return true;
		else
			return false;
	}
	/**
	 * DSet Node�� key / parent�� key / rank�� ���
	 */
	public String toString() {
		return ""+key+"["+parent.key+","+rank+"]";
	}
	/**
	 * DSet Node�� parent�� ��� Ÿ�� ��ǥ Node�� ã�� ����ϴ� �޼���
	 */
	public void showParent() {
		DSet p = this;
		System.out.print(p.toString());
		while(!p.equals(p.parent)) {
			p = p.parent;
			System.out.println("  ---->  "+p.toString());
		}
		System.out.println();
	}
	/**
	 * DSet Node�� �ʱ�ȭ �ϴ� �޼���
	 * data k�� key�� �ް� 
	 * ó�� �ڽ� ȥ�ڴ� rank = 0 / parent�� �ڱ� �ڽ����� �ʱ�ȭ
	 * @param k
	 * @return
	 */
	public DSet makeSet(int k ) {
		key = k;
		rank = 0;
		parent = this;
		return this;
	}
	/**
	 * ���Ƿ� Tree�� ����� ���� Node�� �ʱ�ȭ �ϴ� �޼���
	 * @param k
	 * @param r
	 * @param a
	 * @return
	 */
	public DSet makeSet(int k, int r, DSet a ) {
		key = k;
		rank = r;
		parent = a;
		return this;
	}
	/**
	 * ��ξ����� ���� find set�� �ϴ� �޼���
	 *  Node�� Node.parent�� ���� ��ǥ Node�� ã�� �� ���� �ݺ� 
	 *  
	 *  ���� ã�°��� �� Node�� Node.parent�� ���� �ȵǴ� ���
	 *  Node�� Array�� ���� �� Node�� Node.parent�� �����Ǵ� p�� �Բ� resetPointer�޼��忡 ����
	 *  
	 * @param node
	 * @return
	 */
	public static DSet findSet(DSet node) {
		DSet p = node;
		DSet[] array = new DSet[10];
		for(int i=0; i<10; i++) {
			array[i] = new DSet();
		}
		int j=0;
		while(!p.equals(p.parent)) {
			array[j++] = p;
			p = p.parent;
			}
		resetPointer(array, p);
		return p;
	}
	/**
	 * findset �� ������ ��� Node�� ���� root�� ����Ű���� �����͸� �ٲ��ִ� �޼���
	 * @param array
	 * @param node
	 */
	private static void resetPointer(DSet[] array, DSet node) {
		for(int i=0; i<array.length; i++) {
			if(array[i].key != -1) {
				array[i].parent = node;
			}
		}
	}

	/**
	 * Node�� Node(Ȥ�� Tree�� Tree)�� ��ġ�� �޼���
	 * 
	 * A UNION B �̶�� ���������� findSet(A), findSet(B)�� �����Ͽ� ��ǥNode�� ã��
	 * ��ǥNode�� rank�� ���Ͽ� rank�� ���� NodeȤ�� Tree�� rank�� ���� Node Ȥ�� Tree�� ��ġ�� ���
	 * rank�� ���� ���� �ƹ��ų� ��ģ �� ��ģ���� rank�� 1 ���� ������ 
	 * @param other
	 * @return
	 */
	public DSet union(DSet other) {
		DSet u = findSet(this);
		DSet v = findSet(other);
		
		if(u.rank> v.rank) {
			v.parent = u;
			return u;
		}
		else if(v.rank > u.rank) {
			u.parent = v;
			return v;
		}
		else {
			v.parent = u;
			u.rank++;
			return u;
		}
	}
	
/**
 * findset�� ���̱� ���� ���Ƿ� Node�� �����Ͽ� ���ο� Tree�� ����� �ۼ�
 * ���� ���� Node�� 7�� findset���� ���� ��ȭ�� ��
 * @param args
 */
	public static void main(String[] args) {
		int dataSize = 8;
		
		DSet [] elements = new DSet[dataSize];
		
		for(int i=0; i<dataSize; i++) {
			elements[i] = new DSet();
			if(i==0)
				elements[i].makeSet(i+1);
			else if(i==2)
				elements[i].makeSet(i+1,0,elements[1]);
			else if(i==4)
				elements[i].makeSet(i+1,1,elements[3]);
			else if(i>4)
				elements[i].makeSet(i+1,0,elements[4]);
			else
				elements[i].makeSet(i+1,1,elements[0]);
			System.out.println(elements[i].toString());
		}
		
		for(int i =0; i<dataSize; i++) {
			elements[i].showParent();
		}
		
		DSet p = findSet(elements[7]);
		
		System.out.println("=====find set(7)=====");
		for(int i =0; i<dataSize; i++) {
			elements[i].showParent();
		}
		
		
	}

}
