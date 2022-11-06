package week2Hash;
/**
 * Hash 알고리즘
 * @author 차유상
 *
 */
public class Chaining {
	/**
	 * 성능 측정을 위한 세는 값
	 */
	int nOfHops=0;
	/**
	 * Hash알고리즘에 사용하는 Node
	 * node는 value인 key와 다음 node를 가르치는 next로 구성
	 *
	 */
	private class HashNode {
		int key;
		HashNode next;
		public HashNode(int k) {
			key=k;
			next=null;
		}
		public String toString() {
			return "->"+key;
		}	
	}
	/**
	 *Hash알고리즘에 사용하는 기본 정보
	 */
	HashNode [] table;
	int tableSize;
	int numberOfItems;
	
	public Chaining(int n) {
		tableSize = n;
		numberOfItems=0;
		table = new HashNode[tableSize];
		for (int i=0; i<tableSize; i++)
			table[i]=null;
	}
	/**
	 * 나누기 방법으로 사용한는 hash function
	 * @param d
	 * @return
	 */
	private int hashFunction(int d) {
		// 나누기 방법
		return d%tableSize;
	}
	/**
	 * 중복 체크를 하지 않고 새로운 Node를 만들어 HashTable에 순서대로 만드는 메서드
	 * hashCode를 hashFunction(d)로 받는다. => linked list로 데이터를 받기 위해 hashFunction에서 table의 순서를 정한다.
	 * 새로운 node를 만든 후 테이블에 linked list를 삽입한 후 
	 * node가 몇개 들어왔는지 세는 값인 numberOfItems을 1 더한 후 성능체크를 위해 nOfHops을 1로 반환한다.
	 * @param d
	 * @return
	 */
	public int hashInsert(int d) {
		int  hashCode = hashFunction(d);
		HashNode newNode = new HashNode(d);
		newNode.next = table[hashCode];
		table[hashCode] = newNode;
		numberOfItems++;
		nOfHops=1;
		return nOfHops;
	} 
	
	/**
	 * data d가 있는 위치를 알기 위한 메서드
	 * hashCode를 구한 후 table의 hashCode를 찾아간다.
	 * 그 후 반복을 통해 linked list의 연결을 타고 가서 data d와 일치하는 HashNode를 찾는다.
	 * 만약 찾지 못했을시 nOfHops에서 1을 빼준다.
	 * @param d
	 * @return
	 */
	public int hashSearch(int d) {
		int  hashCode = hashFunction(d);
		HashNode p = table[hashCode];
		nOfHops=1;
		while(p != null) {
			if (p.key == d)
				return nOfHops;
			else {
				nOfHops++;
				p = p.next;
			}
		}
		return -nOfHops;
	} 
	
	/**
	 * Hash 내 data d와 같은 linked list를 찾아가 삭제하는 메서드
	 * 기본적으로 search 메서드와 비슷하며 search에서
	 * d와 일치한 Node의 이전 Node 혹은 Hash를 일치한 Node의 nextNode로 받는다.
	 * node의 개수인 numberOfItems을 1 빼주고 nextNode로 한칸식 옮길시 nOfHops를 1씩 더한다.
	 * @param d
	 * @return
	 */
	public int hashDelete(int d) {
		int  hashCode = hashFunction(d);
		HashNode p = table[hashCode];
		nOfHops=1;
		
		if(p == null)
			return -nOfHops;
		else if(p.key == d) {
			table[hashCode] = p.next;
			numberOfItems--;
			return nOfHops;
		}
		HashNode q = p.next;
		nOfHops++;
		while(q!=null) {
			if(q.key == d) {
				p.next = q.next;
				numberOfItems--;
				return nOfHops;
			}
			else {
				p = q;
				q = q.next;
				nOfHops++;
			}
		}
		return -nOfHops;
	}
	
	/**
	 * 적재율 구하는 load factor 메서드
	 * 적재율이 높아지면 table의 효율이 저하되므로 계속 확인을 해야한다.
	 * chaning에서는 크게 의미가 없으며 
	 * Addrlinear에서 적재율이 높으면 table 개수를 늘린다.
	 * @return
	 */
	public double loadfactor() {
		return ((double) numberOfItems/tableSize);
	} 
	
	
	public void showTable() {
		System.out.println("\n\n<< Current Table Status >>");
		for (int i=0;i<tableSize; i++) {
			HashNode p = table[i];
			System.out.print("\n "+i+" : ");
			while(p!=null) {
				System.out.print(p.toString());
				p=p.next;
			}
		}
	}
	

	public static void main(String[] args) {
		int tableSize = 16;
		
		int [] data = {10, 12, 18, 20, 22, 23, 26, 27, 42, 57};
		int dataSize = data.length;
		
		System.out.println("\n *** Chaining ***");
		
		Chaining myHash = new Chaining(tableSize);
		// Insert
		int sumOfSuccess = 0;
		int sumOfFailure = 0;
		int maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashInsert(data[i]);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else
				sumOfFailure += count;
		}
		myHash.showTable();
		System.out.println("\n\n [Insert] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);
		System.out.println("\n Load Factors ="+myHash.loadfactor()); 
		
		// Search with existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashSearch(data[i]);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Search 1] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);

		// Search with non-existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashSearch(data[i]+1);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Search 2] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);

		// Delete with non-existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashDelete(data[i]+1);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Delete] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);


	}

}
