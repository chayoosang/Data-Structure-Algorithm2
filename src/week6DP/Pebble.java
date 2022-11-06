package week6DP;
/**
 * Dynamic Programming 예시 3(조약돌 넣기)
 * Dynamic Programming을 이용하여 조약돌 넣기 문제를 recursive 방법과 성능 비교하기 
 * @author 차유상
 *
 */
public class Pebble {
	/**
	 * table => main에서 보낸 data를 저장할 table
	 * memo => Dynamic Programming을 사용하여 저장하고 재 계산을 방지할 table
	 * nextPatter => 조약돌 넣기에서 1~4까지 pattern을 지정한 후 해당 pattern 이후 나올 pattern의 가지를 저장
	 * nCols => table의 행
	 * count => Dynamic Programming과 Recursive를 비교하기 위한 횟수
	 */
	int [][] table;
	int [][] memo;
	int [][] nextPattern =  {{0,0,0},{2,3,0},{1,3,4},{1,2,0},{2,0,0}};
	int nCols;
	int count;
	
	/**
	 * main에서 보낸 data를 table에 저장
	 * nCols에 table의 행 저장
	 * memo 배열 생성 후 reset으로 초기화
	 * @param data
	 */
	public Pebble(int[][] data) {
		table = data;
		nCols = table[0].length;
		memo = new int [5][nCols];
		 reset();
	}
	
	/**
	 * count와 memo를 초기화하는 메서드
	 * count = 0 으로 초기화
	 * memo를 -99999로 초기화
	 */
	void reset() {
		count = 0;
		for(int i = 0; i<5; i++) 
			for(int j=0; j<nCols; j++)
				memo[i][j] = -99999;
	}
	/**
	 * count를 return 받는 메서드
	 * @return
	 */
	int getCount() {
		return count;
	}
	/**
	 * Recursive 방법으로 조약돌 넣기에 최대 합을 구하는 메서드를 호출하는 메서드
	 * n => 행의 index
	 * j => pattern (1~4)
	 * 최대값을 기존 max와 모든 pattern에서 나올수 있는 가지수를 비교하여 저장
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
	 * Dynamic Programming방법으로 조약돌 넣기에 최대 합을 구하는 메서드를 호출하는 메서드
	 * n => 행의 index
	 * j => pattern (1~4)
	 * 기존 memo에 계산되어 저장되어있던 값을 max와 비교하여 max에 저장
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
	 *  Recursive 방법으로 조약돌 넣기에 최대 합을 구하는 메서드
	 *  
	 *  i==1일때 table이 표현하는 data의 끝이므로 aPatternValue 메서드를 사용하여 pattern에 따라 table값 return 
	 *  
	 *  Recursive 방법을 사용하여 현재 pattern에서 이전 행에서 나올수 있는 pattern 즉 nextPattern으로 비교하여 
	 *  전에 행에서 나올수 있는 값들을 모두 비교하여 최대값을 찾은후 자신의 값과 더하여 return  
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
	 * Dynamic Programming방법으로 조약돌 넣기에 최대 합을 구하는 메서드
	 * 
	 * memo[p][i]가 저장된 값일 경우 해당 값을 return
	 * i == 1일 경우 열이 1이므로 aPatternValue을 통해 값 저장
	 * 	
	 * 위의 2가지 경우가 아닐경우 nextPattern을 알아서 해당 pattern으로 나올수 있는 값
	 * 즉 계산되어졌던 memo에 값과 비교하여 자신의 값과 memo에서 구한 최대값을 memo에 저장 
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
	 * pattern(1~4)을 case로 나눠 table에서 해당 pattern에 맞게 value를 return해주는 메서드 
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
