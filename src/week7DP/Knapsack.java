package week7DP;
/**
 * 0/1 Knapsack�� recursive�� DP ��
 * @author ������
 *
 */
public class Knapsack {
	/**
	 * item�� ���ݰ� ���Ը� ������ item[][]����
	 * DP������� ������ ���� DPTable[][]����
	 * recursive�� DP�� ���ϱ����� count  ����
	 */
	int[][] item;
	int[][] DPTable;
	int count;
	
	/**
	 * item�� main���� ������ input���� �޴´�
	 * DPTable ���� item���� ��� ���� 1�� �����Ͽ� ���� (item�� ������ ������ weight�� 1���� ǥ���ϱ� ����)
	 * 
	 * @param input
	 * @param bagWeight
	 */
	public Knapsack(int[][] input, int bagWeight) {
		item = input;
		DPTable = new int [input.length+1][bagWeight+1];
	}
	/**
	 * count = 0���� �ʱ�ȭ
	 * item�� ������ ������ weight�� 1���� ǥ���ϱ� ���� 0��� 0���� ��� 0���� ǥ��
	 */
	public void reset() {
		count = 0;
		for(int i=0; i<DPTable[0].length; i++)
			DPTable[0][i] = 0;
		for(int j=0; j<DPTable.length; j++)
			DPTable[j][0] = 0;			
	}

	/**
	 * 0/1 Knapsack�� recursive�ϰ� ǥ��
	 * 
	 * 1. ������ ���Կ� �������� ������ ��� ������ 0���� return
	 * 2. price = item[index]�� ���� / itemWeight = item[index]�� ����
	 * ���� ���Կ� ������ �ȵɽ� ���� item�� ���ݰ� ���� item�� ������ ���ѰͰ� 
	 * �ڽ��� �������� �ʾ������ item�� ���� ���ݰ� ���Ͽ� ū���� return
	 * @param index
	 * @param bagWeight
	 * @return
	 */
	public int recursive( int index, int bagWeight) {
		count++;
		if(bagWeight <= 0 || index <0 )
			return 0;
		else {
			int price = item[index][0];
			int itemWeight = item[index][1];
			return Math.max((price+recursive(index-1, bagWeight-itemWeight)), recursive(index-1, bagWeight));
		}
	}
	
	/**
	 * 0/1 Knapsack�� DP�� ǥ��
	 * 
	 * DPTable���� 0 ��� 0���� ��� 0���� ǥ�������Ƿ� 1��� 1������ ����
	 *  j�� 1���� ������ ���Ա����� ǥ��
	 *  j-itemWeight => ���濡 item�� ���� ������� DPTable���� j-itemWeight �� ���� ���� �ִ��� ���� ������ ���� ���� ����
	 *  �ش� ������ ���� j���� ���� item���� ���� ���Ե� ������ ���Ͽ� �ִ밪�� ����
	 * @param index
	 * @param bagWeight
	 * @return
	 */
	public int DP( int index, int bagWeight) {

		for(int i=1; i<=index; i++) {
			for(int j=1; j<=bagWeight; j++) {
				count++;
				int price = item[i-1][0];
				int itemWeight = item[i-1][1];
				if(j-itemWeight >=0 )
					DPTable[i][j] = Math.max(price+DPTable[i-1][j-itemWeight], DPTable[i-1][j]);
				else 
					DPTable[i][j] = DPTable[i-1][j];
			}
		}
		return DPTable[index][bagWeight];				
	}

	public static void main(String[] args) {
		int [][] input = {{3, 1}, {2, 2}, {5, 3}, {3, 4},{2,5},{4,6},{2,2},{1,6},{5,2},{3,3},{4,2},{5,5},{1,6}}; 
		int bagWeight = 10;
		
		Knapsack k = new Knapsack(input, bagWeight);
		System.out.println("  ==== 0 / 1 Knapsack ==== ");
		k.reset();
		System.out.print("==> recursive : "+k.recursive(input.length-1, bagWeight)+" count : "+k.count);
		k.reset();
		System.out.print("  ==> DP : "+k.DP(input.length, bagWeight)+" count : "+k.count);

	}

}
