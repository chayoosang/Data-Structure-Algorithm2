package week7DP;
/**
 * LCS (�ΰ��� String���� ��ġ�ϴ� ���� �� �� ã��)�� recursive�� DP ��
 * @author ������
 *
 */
public class LCS {
	/**
	 * String(input1, input2)�� ���� Data1�� Data2
	 * DP�� ���Ǿ��� DPTable
	 * recursive�� DP �񱳸� ���� count
	 */
	String[] Data1;
	String[] Data2;
	int[][] DPTable;
	int count; 
	
	/**
	 * main�� 2���� String(input1, input2)�� Data1, Data2�� ����
	 * DPTable ����
	 * @param input1
	 * @param input2
	 */
	public LCS(String[] input1, String[] input2) {
		Data1 = input1;
		Data2 = input2;
		DPTable = new int[Data1.length][Data2.length];
	
	}
	/**
	 * count �ʱ�ȭ �� DPTable ù ��� �� 0���� �ʱ�ȭ
	 */
	public void reset() {
		count = 0;
		for(int i=0; i<DPTable[0].length; i++)
			DPTable[0][i] = 0;
		for(int j=0; j<DPTable.length; j++)
			DPTable[j][0] = 0;			
	}
	/**
	 * LCS�� recursive ���
	 * 
	 * LCS�� ��ȭ�Ŀ� �ٰ��Ͽ� 3������ ���� ����
	 * 
	 * 1. index1(Data1�� index)�� index2(Data2�� index) �� �ϳ��� 0�� �� 0�� return 
	 * 2. Data1[index1] == Data2[index2] �� �� rLCS(index1-1, index2-1)�� ���� 1�� ���Ͽ� return 
	 * 3. Data1[index1] != Data2[index2] �� �� rLCS(index1-1, index2)�� rLCS(index1, index2-1) �� ���̰� �� ���� ���� �����Ͽ� return
	 * @param index1
	 * @param index2
	 * @return
	 */
	public int rLCS(int index1, int index2) {
		this.count++;
		if(index1==0 || index2==0)
			return 0;
		else if (Data1[index1].equals(Data2[index2])) 
			return rLCS(index1-1, index2-1) + 1;
		else 
			return Math.max(rLCS(index1-1,index2), rLCS(index1,index2-1));
	}
	
	/**
	 * LCS�� DP ���
	 * 
	 * DPTable�� ù�� ° ���� ���� ��� 0���� �ʱ�ȭ �Ͽ����Ƿ�
	 * ��ȭ�Ŀ� �ٰ��� 1�� ������ 3������ ���� �� ����������� 0���� ���� ���� ��� �����Ѵ�.
	 * 
	 * ���� 2�����ǰ� 3�����Ǹ� ������Ű�� �ǹǷ�
	 * 2�� ������ ������ �� index�� 1�� ���� ���� ���� 1�� ���ϰ�
	 * 3�� ������ DPTable�� index1-1, index2 �� index1, index2-1 �� �� �� ū ���� ���� �ȴ�.
	 * @param index1
	 * @param index2
	 * @return
	 */
	public int DPLCS(int index1, int index2) {
		for(int i=1; i<=index1; i++) {
			for(int j=1; j<=index2; j++) {
				this.count++;
				if(Data1[i].equals(Data2[j]))
					DPTable[i][j] = DPTable[i-1][j-1]+1;
				else
					DPTable[i][j] = Math.max(DPTable[i-1][j],DPTable[i][j-1]);
			}
		}
		return DPTable[index1][index2];
	}
	
	
	
	public static void main(String[] args) {
		String[] input1 = {"","a","b","c","b","d","a","b"};
		String[] input2 = {"","b","d","c","a","b","a"};
		
		LCS lcs = new LCS(input1, input2);
		System.out.println("  ==== LCS ==== ");
		lcs.reset();
		System.out.print("==> recursive : "+ lcs.rLCS(input1.length-1, input2.length-1)+" Count : "+lcs. count);
		lcs.reset();
		System.out.print("  ==> DP : "+ lcs.DPLCS(input1.length-1, input2.length-1)+" Count : "+lcs. count);
	}

}
