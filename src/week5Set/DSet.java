package week5Set;
/**
 * Disjoint Set을 Tree 방식(Rank방식 / 경로압축)으로 구현
 * 
 * @author 차유상
 *
 */
public class DSet {
	/**
	 * key = data
	 * rank = 해당 Node의 높이
	 * parent = 해당 Node의 부모 Node
	 */
	int key;
	int rank;
	DSet parent;

	/**
	 * DSet Node 생성
	 */
	public DSet() {
		key = -1;
		rank = -1;
		parent = null;
	}
	
	/*
	 * DSet Node의 key와 다른 DSet Node의 key를 비교
	 * 
	 * key의 값이 같을 시 true를,  key의 값이 다를 시 false를 return
	 */
	public boolean equals(DSet other) {
		if(key == other.key)
			return true;
		else
			return false;
	}
	/**
	 * DSet Node의 key / parent의 key / rank를 출력
	 */
	public String toString() {
		return ""+key+"["+parent.key+","+rank+"]";
	}
	/**
	 * DSet Node의 parent를 계속 타고가 대표 Node를 찾고 출력하는 메서드
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
	 * DSet Node를 초기화 하는 메서드
	 * data k를 key로 받고 
	 * 처음 자신 혼자니 rank = 0 / parent는 자기 자신으로 초기화
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
	 * 임의로 Tree를 만들기 위해 Node를 초기화 하는 메서드
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
	 * 경로압축을 위해 find set을 하는 메서드
	 *  Node와 Node.parent가 같은 대표 Node를 찾을 때 까지 반복 
	 *  
	 *  만약 찾는과정 중 Node와 Node.parent가 성립 안되는 경우
	 *  Node를 Array에 저장 후 Node와 Node.parent가 성립되는 p와 함께 resetPointer메서드에 보냄
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
	 * findset 중 만나는 모든 Node를 직접 root를 가르키도록 포인터를 바꿔주는 메서드
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
	 * Node와 Node(혹은 Tree와 Tree)를 합치는 메서드
	 * 
	 * A UNION B 이라고 가정했을때 findSet(A), findSet(B)를 진행하여 대표Node를 찾음
	 * 대표Node의 rank를 비교하여 rank가 높은 Node혹은 Tree에 rank가 작은 Node 혹은 Tree를 합치는 방식
	 * rank가 같은 경우는 아무거나 합친 후 합친곳에 rank를 1 증가 시켜줌 
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
 * findset을 보이기 위해 임의로 Node를 재편성하여 새로운 Tree를 만들어 작성
 * 제일 밑의 Node인 7을 findset으로 인한 변화를 비교
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
