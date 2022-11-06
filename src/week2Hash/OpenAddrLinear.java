package week2Hash;
/**
 * OpenAddrLinear�� ���� Hash �˰���
 * @author ������
 *
 */
public class OpenAddrLinear { // Linear Probing

	/**
	 * ���� üũ�� ���� nOfHops
	 * �ؽ��� �������� threshold�� ������ hash�� �ø��� ����
	 */
	int nOfHops =0;
	double threshold = 0.99;
	/**
	 * hash�� �⺻ ����
	 */
	int [] table;
	int tableSize;
	int numberOfItems;
	
	/**
	 * OpenAddrLinear�� �ʱ� ����
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
	 * ���ϱ� ����� hashFuction
	 * �ռ� chaning�� ������ ����� ����ߴٸ�
	 * ���ϱ� ����� ����Ͽ� hashCode�� ���ϴ� ���
	 * h(x) = (xA mod 1) * m�� ǥ�� �̶� A�� 0.6180339887 ����
	 * @param d
	 * @return
	 */
	private int hashFunction(int d) {
	// ���ϱ���
	double temp = (double)d * 0.6180339887;
	double res = temp - Math.floor(temp);
	return (int)(res*tableSize);
	}
	/**
	 * hashtable�� ä��� Insert �޼���
	 * 
	 * loadfactor()�� ���� ���� �������� threshold���� ū ���
	 * hash�� �÷��ִ� enlargeTable()�޼��带 �����Ѵ�. 
	 * 
	 * 
	 * table���� -1�� �ڸ� �� ����ִ� �ڸ��� ã�� data d�� �ִ´�.
	 * ���� hashcode�� �ڸ��� -1�� ���ڸ��� �ƴ� ���
	 * probeIndex�� ���� ���ο� hashcode�� �����Ѵ�.
	 * �̶� probeIndex�� LinearProbing����� ����Ͽ� 1�� �����ش�.
	 * �� �� -1 Ȥ�� -999�� ����ְų� �����Ǿ ����ִ� �ڸ��� data d�� �־��ش�.
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
	 * data d�� ��ġ�� hash table���� ã�� �޼���
	 * hashcode�� ���� table���� hashcode �ڸ��� d�� ���� ���
	 * ù search���� ã�����Ƿ� nOfHops = 1 �� return
	 * �� ã�Ҵٸ� probeIndex�� ���� ���ο� hashcode�� �����Ѵ�.
	 * �� �� table[probeIndex]!=-1 ���� ��ã�����Ƿ� -nOfHops�� return �Ѵ�.
	 * ���� table[probeIndex]�� data d�� ������ ã�µ��� ���� nOfHops�� rerturn�Ѵ�.
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
	 * data d�� ��ġ�� hash table���� ã�� �� �����ϴ� �޼���
	 * 
	 * �ռ� search �޼���� ���Ͻ� �ϰ� ã���� ã�Ҵٸ� -999�� table�� ����ְ�
	 * �� ã�Ҵٸ� probeIndex�� �ٽ� ���� ã�� -999 ���� Ȥ�� -nOfHops�� return�Ѵ�
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
	 * �������� threshold�� �Ѿ��ٸ� table ũ�⸦ �ø��� �޼���
	 * 
	 * ���� table�� ũ�⺸�� �ι踦 �Ͽ�
	 * �ٽ� �� hashing�� ���ش�.
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
	 * �������� ���ϴ� �޼���
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
