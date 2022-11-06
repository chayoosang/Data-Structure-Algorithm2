package week6DP;

/**
 * Dynamic Programming 예시4(행렬 곱셈)
 * Dynamic Programming을 이용하여 행렬 곱셈 문제를 recursive 방법과 성능 비교하기 
 * @author 차유상
 *
 */
public class Matrixchain {
	/**
	 * nOfMatrix => p의 길이
	 * p => main의 dimension을 저장할 table
	 * count => Dynamic Programming과 Recursive를 비교하기 위한 횟수
	 *  memo => Dynamic Programming을 사용하여 저장하고 재 계산을 방지할 table
	 */
	int nOfMatrix;
	int [] p;
	int count;
	int [][] memo;
	
	/**
	 *  main에서 보낸 dimension를 p에 저장
	 * nOfMatrix에 p의 개수 저장
	 * memo 배열 생성
	 * @param dimension
	 */
	public Matrixchain(int [] dimension) {
		p = dimension;
		nOfMatrix = p.length-1;
		memo = new int[nOfMatrix+1][nOfMatrix+1];
	}
	/**
	 * count와 memo를 초기화 하는 메서드
	 * count = 0으로 초기화
	 * memo는 대각선을 0으로 초기화하고 모든 부분을 -1로 초기화
	 * (단, 대각선기준 아래부분은 i > j가 되므로 성립이 안되므로 계속 -1로 사용) 
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
	 * count를 return 해주는 메서드
	 * @return
	 */
	int getCount() {
		return count;
	}
	/**
	 * Recursive를 이용하여 행렬곱셈의 최소 횟수를 찾는 메서드
	 * 
	 * 행렬곱셈의 곱셈 횟수 = ( i와 k의 곱셈에서 나올수 있는 횟수의 최소값 + k+1과 j의 곱셈에서 나올수 있는 횟수의 최소값
	 *  + 행렬 i의 dimension 중 i-1 * 행렬 k dimension 중 k * 행렬 j의 dimension 중 j  )
	 * 따라서 곱셈의 최솟값은 recursive한 방법으로 구한 다음 dimension을 더한값 중 최소값을 찾는다.
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
	 * Dynamic Programming을 이용하여 행렬곱셈의 최소 횟수를 찾는 메서드
	 * 
	 * memo에 저장되어있을 경우 memo를 return 
	 * 
	 * 저장이 안되어 있다면 기존 memo에서 저장되어있는 값으로 행렬의 곱셈횟수 값을 비교하여 
	 * 최소값을 찾은 후 memo에 저장한다.
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
