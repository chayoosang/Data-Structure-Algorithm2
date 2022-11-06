package week6DP;

/**
 * Dynamic Programming ����4(��� ����)
 * Dynamic Programming�� �̿��Ͽ� ��� ���� ������ recursive ����� ���� ���ϱ� 
 * @author ������
 *
 */
public class Matrixchain {
	/**
	 * nOfMatrix => p�� ����
	 * p => main�� dimension�� ������ table
	 * count => Dynamic Programming�� Recursive�� ���ϱ� ���� Ƚ��
	 *  memo => Dynamic Programming�� ����Ͽ� �����ϰ� �� ����� ������ table
	 */
	int nOfMatrix;
	int [] p;
	int count;
	int [][] memo;
	
	/**
	 *  main���� ���� dimension�� p�� ����
	 * nOfMatrix�� p�� ���� ����
	 * memo �迭 ����
	 * @param dimension
	 */
	public Matrixchain(int [] dimension) {
		p = dimension;
		nOfMatrix = p.length-1;
		memo = new int[nOfMatrix+1][nOfMatrix+1];
	}
	/**
	 * count�� memo�� �ʱ�ȭ �ϴ� �޼���
	 * count = 0���� �ʱ�ȭ
	 * memo�� �밢���� 0���� �ʱ�ȭ�ϰ� ��� �κ��� -1�� �ʱ�ȭ
	 * (��, �밢������ �Ʒ��κ��� i > j�� �ǹǷ� ������ �ȵǹǷ� ��� -1�� ���) 
	 */
	void reset() {
		count = 0; 
		for(int i=0; i<nOfMatrix+1; i++)
			for(int j=0; j<nOfMatrix+1; j++)
				memo[i][j] = -1;
		for(int i=0; i< nOfMatrix+1; i++)
			memo[i][i]  = 0;
	}
	/**
	 * count�� return ���ִ� �޼���
	 * @return
	 */
	int getCount() {
		return count;
	}
	/**
	 * Recursive�� �̿��Ͽ� ��İ����� �ּ� Ƚ���� ã�� �޼���
	 * 
	 * ��İ����� ���� Ƚ�� = ( i�� k�� �������� ���ü� �ִ� Ƚ���� �ּҰ� + k+1�� j�� �������� ���ü� �ִ� Ƚ���� �ּҰ�
	 *  + ��� i�� dimension �� i-1 * ��� k dimension �� k * ��� j�� dimension �� j  )
	 * ���� ������ �ּڰ��� recursive�� ������� ���� ���� dimension�� ���Ѱ� �� �ּҰ��� ã�´�.
	 * @param i
	 * @param j
	 * @return
	 */
	int matrixChain(int i, int j) {
		count++;
		if(i==j) return 0;
		int min = 999999;
		for(int k=i; k<j; k++) {
			int q = matrixChain(i, k ) + matrixChain(k+1, j) + p[i-1]*p[k]*p[j];
			if(q<min) min = q;
		}
		return min;
	}
	/**
	 * Dynamic Programming�� �̿��Ͽ� ��İ����� �ּ� Ƚ���� ã�� �޼���
	 * 
	 * memo�� ����Ǿ����� ��� memo�� return 
	 * 
	 * ������ �ȵǾ� �ִٸ� ���� memo���� ����Ǿ��ִ� ������ ����� ����Ƚ�� ���� ���Ͽ� 
	 * �ּҰ��� ã�� �� memo�� �����Ѵ�.
	 * @param i
	 * @param j
	 * @return
	 */
	int matrixChainDP(int i, int j) {
		count++;
		if(memo[i][j]>=0)
			return memo[i][j];
		int min=9999999;
		for(int k=i; k<j; k++) {
			if(memo[i][k]<0) memo[i][k] = matrixChainDP(i,k);
			if(memo[k+1][j]<0) memo[k+1][j] = matrixChainDP(k+1, j);
			min = Math.min(min, memo[i][k]+memo[k+1][j] + p[i-1]*p[k]*p[j]);
		}
		return min;
	}
	
	public static void main(String[] args) {
		int numOfMatrix = 10;
		int [] dimension = {9,5,5,15,15,4,4,20,20,7};
		
		Matrixchain m = new Matrixchain(dimension);
		
		for(int i = 0; i<numOfMatrix; i++) {
			m.reset();
			System.out.print("Recursion : "+m.matrixChain(1, i)+"   Count = "+m.getCount());
			m.reset();
			System.out.println("===>  DP : "+m.matrixChainDP(1, i)+"   Count = "+m.getCount());
		}
		System.out.println("ebd");
		
	}

}
