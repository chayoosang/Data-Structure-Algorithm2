package week2Hash;
/**
 * OpenAddrLinear을 통한 Hash 알고리즘
 * @author 차유상
 *
 */
public class OpenAddrLinear { // Linear Probing

	/**
	 * 성능 체크를 위한 nOfHops
	 * 해쉬의 적재율이 threshold을 넘으면 hash를 늘리게 설정
	 */
	int nOfHops =0;
	double threshold = 0.99;
	/**
	 * hash의 기본 설정
	 */
	int [] table;
	int tableSize;
	int numberOfItems;
	
	/**
	 * OpenAddrLinear의 초기 설정
	 * @param n
	 */
	public OpenAddrLinear (int n) {
		tableSize = n;
		table = new int[tableSize];
		numberOfItems = 0;
		// -1 : null, -999 : deleted
		for (int i=0; i<tableSize; i++)
			table[i]=-1;
	}
	
	/**
	 * 곱하기 방법인 hashFuction
	 * 앞서 chaning은 나누기 방법을 사용했다면
	 * 곱하기 방법을 사용하여 hashCode를 구하는 방법
	 * h(x) = (xA mod 1) * m를 표시 이때 A는 0.6180339887 고정
	 * @param d
	 * @return
	 */
	private int hashFunction(int d) {
	// 곱하기방법
	double temp = (double)d * 0.6180339887;
	double res = temp - Math.floor(temp);
	return (int)(res*tableSize);
	}
	/**
	 * hashtable을 채우는 Insert 메서드
	 * 
	 * loadfactor()를 통해 구한 적재율이 threshold보다 큰 경우
	 * hash를 늘려주는 enlargeTable()메서드를 실행한다. 
	 * 
	 * 
	 * table에서 -1인 자리 즉 비어있는 자리를 찾아 data d를 넣는다.
	 * 만약 hashcode의 자리가 -1인 빈자리가 아닐 경우
	 * probeIndex를 통해 새로운 hashcode를 생성한다.
	 * 이때 probeIndex는 LinearProbing방법을 사용하여 1을 더해준다.
	 * 그 후 -1 혹은 -999로 비어있거나 삭제되어서 비어있는 자리에 data d를 넣어준다.
	 * @param d
	 * @return
	 */
	public int hashInsert(int d) {
		if(loadfactor()>=threshold)
			enlargeTable();
		int hashCode = hashFunction(d);
		nOfHops=1;
		if(table[hashCode]==-1) {
			table[hashCode] = d;
			numberOfItems++;
			return nOfHops;
		}
		else {
			nOfHops++;
			int probeIndex = (hashCode+1)%tableSize;
			int i =3;
			while(table[probeIndex]!=-1&& table[probeIndex]!=-999) {
				nOfHops++;
				probeIndex = (probeIndex+(i*secondFucntion(d)))%tableSize;
				if(probeIndex==hashCode)
					return 0;
			}
			table[probeIndex]=d;
			numberOfItems++;
			return nOfHops;
		}
	} 
	
	private int secondFucntion(int d) {
		double temp = (double)d * 0.6180339887;
		double res = temp - Math.floor(temp);
		return (int)(res*(tableSize-3));
	}
	/**
	 * data d의 위치를 hash table에서 찾는 메서드
	 * hashcode를 구해 table에서 hashcode 자리에 d가 잇을 경우
	 * 첫 search만에 찾았으므로 nOfHops = 1 을 return
	 * 못 찾았다면 probeIndex를 통해 새로운 hashcode를 생성한다.
	 * 그 후 table[probeIndex]!=-1 경우는 못찾았으므로 -nOfHops을 return 한다.
	 * 만약 table[probeIndex]에 data d가 있으면 찾는동안 더한 nOfHops을 rerturn한다.
	 * @param d
	 * @return
	 */
	public int hashSearch(int d) {
		int hashCode = hashFunction(d);
		nOfHops=1;
		if(table[hashCode]==d) {
			return nOfHops;
		}
		else {
			nOfHops++;
			int probeIndex = (hashCode+1)%tableSize;
			while(table[probeIndex]!=-1&& table[probeIndex]!=d) {
				nOfHops++;
				probeIndex = (probeIndex+1)%tableSize;
				if(probeIndex==hashCode)
					return 0;
			}
			if(table[probeIndex]==d)
				return nOfHops;
			else
				return -nOfHops;
		}
	}
	
	/**
	 * data d의 위치를 hash table에서 찾은 후 삭제하는 메서드
	 * 
	 * 앞서 search 메서드와 동일시 하게 찾으나 찾았다면 -999를 table에 집어넣고
	 * 못 찾았다면 probeIndex을 다시 구해 찾아 -999 삽입 혹은 -nOfHops을 return한다
	 * @param d
	 * @return
	 */
	public int hashDelete(int d) {
		int hashCode = hashFunction(d);
		nOfHops=1;
		if(table[hashCode]==d) {
			table[hashCode] = -999;
			numberOfItems--;
			return nOfHops;
		}
		else {
			nOfHops++;
			int probeIndex = (hashCode+1)%tableSize;
			while(table[probeIndex]!=-1&& table[probeIndex]!=d) {
				nOfHops++;
				probeIndex = (probeIndex+1)%tableSize;
				if(probeIndex==hashCode)
					return 0;
			}
			if(table[probeIndex]==d) {
				table[probeIndex]=-999;
				numberOfItems--;
				return nOfHops;
			}
			else
				return -nOfHops;
		}
	}
	
	/**
	 * 적재율이 threshold을 넘었다면 table 크기를 늘리는 메서드
	 * 
	 * 기존 table의 크기보다 두배를 하여
	 * 다시 재 hashing을 해준다.
	 */
	private void enlargeTable() {
		int [] oldTable = table;
		int oldSize = tableSize;
		tableSize *= 2;
		table = new int [tableSize];
		for(int i =0; i<tableSize; i++)
			table[i] = -1;
		for(int i=0; i<oldSize; i++) {
			if(oldTable[i]>=0)
				hashInsert(oldTable[i]);
		}
	}
	
	/**
	 * 적재율을 구하는 메서드
	 * @return
	 */
	public double loadfactor() {
		return (double)numberOfItems/tableSize;
	}
	
	public void showTable() {
		System.out.println("Current Hash Table : ");
		for (int i = 0; i<tableSize; i++)
			System.out.print(table[i]+"  ");
		System.out.println();
	}

	
	public static void main(String[] args) {
		int tableSize = 17;
		
		int [] data = {10, 12, 18, 20, 22, 23, 26, 27, 42, 57};
		int dataSize = data.length;
		
		System.out.println("\n *** Open Addressing - Linear Probing ***");
		
		OpenAddrLinear myHash = new OpenAddrLinear(tableSize);
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
