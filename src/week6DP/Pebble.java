package week6DP;
/**
 * Dynamic Programming ���� 3(���൹ �ֱ�)
 * Dynamic Programming�� �̿��Ͽ� ���൹ �ֱ� ������ recursive ����� ���� ���ϱ� 
 * @author ������
 *
 */
public class Pebble {
	/**
	 * table => main���� ���� data�� ������ table
	 * memo => Dynamic Programming�� ����Ͽ� �����ϰ� �� ����� ������ table
	 * nextPatter => ���൹ �ֱ⿡�� 1~4���� pattern�� ������ �� �ش� pattern ���� ���� pattern�� ������ ����
	 * nCols => table�� ��
	 * count => Dynamic Programming�� Recursive�� ���ϱ� ���� Ƚ��
	 */
	int [][] table;
	int [][] memo;
	int [][] nextPattern =  {{0,0,0},{2,3,0},{1,3,4},{1,2,0},{2,0,0}};
	int nCols;
	int count;
	
	/**
	 * main���� ���� data�� table�� ����
	 * nCols�� table�� �� ����
	 * memo �迭 ���� �� reset���� �ʱ�ȭ
	 * @param data
	 */
	public Pebble(int[][] data) {
		table = data;
		nCols = table[0].length;
		memo = new int [5][nCols];
		 reset();
	}
	
	/**
	 * count�� memo�� �ʱ�ȭ�ϴ� �޼���
	 * count = 0 ���� �ʱ�ȭ
	 * memo�� -99999�� �ʱ�ȭ
	 */
	void reset() {
		count = 0;
		for(int i = 0; i<5; i++) 
			for(int j=0; j<nCols; j++)
				memo[i][j] = -99999;
	}
	/**
	 * count�� return �޴� �޼���
	 * @return
	 */
	int getCount() {
		return count;
	}
	/**
	 * Recursive ������� ���൹ �ֱ⿡ �ִ� ���� ���ϴ� �޼��带 ȣ���ϴ� �޼���
	 * n => ���� index
	 * j => pattern (1~4)
	 * �ִ밪�� ���� max�� ��� pattern���� ���ü� �ִ� �������� ���Ͽ� ����
	 * @param n
	 * @return
	 */
	public int maxPebble(int n) {
		int max = -99999;
		for(int j=1; j<=4; j++)
			max = Math.max(pebble(n,j), max);
		return max;
	}
	
	/**
	 * Dynamic Programming������� ���൹ �ֱ⿡ �ִ� ���� ���ϴ� �޼��带 ȣ���ϴ� �޼���
	 * n => ���� index
	 * j => pattern (1~4)
	 * ���� memo�� ���Ǿ� ����Ǿ��ִ� ���� max�� ���Ͽ� max�� ����
	 * @param n
	 * @return
	 */
	public int maxPebbleMemo(int n) {
		int max = -99999;
		for(int j=1; j<=4; j++)
			max = Math.max(pebbleMemo(n,j), max);
		return max;
	}

	/**
	 *  Recursive ������� ���൹ �ֱ⿡ �ִ� ���� ���ϴ� �޼���
	 *  
	 *  i==1�϶� table�� ǥ���ϴ� data�� ���̹Ƿ� aPatternValue �޼��带 ����Ͽ� pattern�� ���� table�� return 
	 *  
	 *  Recursive ����� ����Ͽ� ���� pattern���� ���� �࿡�� ���ü� �ִ� pattern �� nextPattern���� ���Ͽ� 
	 *  ���� �࿡�� ���ü� �ִ� ������ ��� ���Ͽ� �ִ밪�� ã���� �ڽ��� ���� ���Ͽ� return  
	 *  
	 * @param i
	 * @param p
	 * @return
	 */
	private int pebble(int i, int p) {
		count++;
		if(i==1)
			return aPatternValue(i, p);
		else {
			int max = -9999;
			int k = 0;
			while(k<3 && nextPattern[p][k]!=0) {
				int q = nextPattern[p][k];
				max = Math.max(pebble(i-1,q), max);
				k++;
			}
			return max + aPatternValue(i, p);
		}		
	}
	/**
	 * Dynamic Programming������� ���൹ �ֱ⿡ �ִ� ���� ���ϴ� �޼���
	 * 
	 * memo[p][i]�� ����� ���� ��� �ش� ���� return
	 * i == 1�� ��� ���� 1�̹Ƿ� aPatternValue�� ���� �� ����
	 * 	
	 * ���� 2���� ��찡 �ƴҰ�� nextPattern�� �˾Ƽ� �ش� pattern���� ���ü� �ִ� ��
	 * �� ���Ǿ����� memo�� ���� ���Ͽ� �ڽ��� ���� memo���� ���� �ִ밪�� memo�� ���� 
	 * 
	 * @param i
	 * @param p
	 * @return
	 */
	private int pebbleMemo(int i, int p) {
		count++;
		if(memo[p][i]>-99999)
			return memo[p][i];
		if(i == 1) {
			memo[p][i] = aPatternValue(i, p);
			return memo[p][i];
		}
		else {
			int max = -99999;
			int k=0; 
			while(k<3 && nextPattern[p][k]!=0) {
				int q = nextPattern[p][k];
				if(memo[q][i-1]==-99999)
					memo[q][i-1] = pebbleMemo(i-1, q);
				max = Math.max(memo[q][i-1], max);
				k++;
			}
			memo[p][i] = max + aPatternValue(i, p);
			return memo[p][i];
		}
	}

	/**
	 * pattern(1~4)�� case�� ���� table���� �ش� pattern�� �°� value�� return���ִ� �޼��� 
	 * @param i
	 * @param p
	 * @return
	 */
	private int aPatternValue(int i, int p) {
		int retVal = 0;
		switch(p) {
		case 1 : retVal = table[0][i];
		break;
		case 2 : retVal = table[1][i];
		break;
		case 3 : retVal = table[2][i];
		break;
		case 4 : retVal = table[0][i]+ table[2][i];
		break;
		}
		return retVal;
	}

	public static void main(String args[]) {
		int [][] data = {
				{0,6,7,12,-5,5,3,11,3,7,-2,5,4},
				{0,-8,10,14,9,7,13,8,5,6,1,3,9},
				{0,11,12,7,4,8,-2,9,4,-4,3,7,10}
		};
		Pebble p = new Pebble(data);
		
		for(int i=1; i<data[0].length; i++) {
			p.reset();
			System.out.print(" >>> "+ i+ "  : [Recursion]  "
					+p.maxPebble(i) + "  Count = "+p.getCount() );
			p.reset();
			System.out.println(" >>> "+ i+ "  : [DP]  "
					+p.maxPebbleMemo(i) + "  Count = "+p.getCount() );
		}
		
	}

}
