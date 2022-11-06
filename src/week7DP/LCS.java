package week7DP;
/**
 * LCS (두개의 String에서 일치하는 가장 긴 줄 찾기)의 recursive와 DP 비교
 * @author 차유상
 *
 */
public class LCS {
	/**
	 * String(input1, input2)을 비교할 Data1과 Data2
	 * DP에 사용되어질 DPTable
	 * recursive와 DP 비교를 위한 count
	 */
	String[] Data1;
	String[] Data2;
	int[][] DPTable;
	int count; 
	
	/**
	 * main의 2개의 String(input1, input2)를 Data1, Data2로 지정
	 * DPTable 생성
	 * @param input1
	 * @param input2
	 */
	public LCS(String[] input1, String[] input2) {
		Data1 = input1;
		Data2 = input2;
		DPTable = new int[Data1.length][Data2.length];
	
	}
	/**
	 * count 초기화 및 DPTable 첫 행과 열 0으로 초기화
	 */
	public void reset() {
		count = 0;
		for(int i=0; i<DPTable[0].length; i++)
			DPTable[0][i] = 0;
		for(int j=0; j<DPTable.length; j++)
			DPTable[j][0] = 0;			
	}
	/**
	 * LCS의 recursive 방법
	 * 
	 * LCS의 점화식에 근거하여 3가지의 경우로 나눔
	 * 
	 * 1. index1(Data1의 index)과 index2(Data2의 index) 중 하나라도 0일 시 0을 return 
	 * 2. Data1[index1] == Data2[index2] 일 시 rLCS(index1-1, index2-1)에 길이 1을 더하여 return 
	 * 3. Data1[index1] != Data2[index2] 일 시 rLCS(index1-1, index2)과 rLCS(index1, index2-1) 중 길이가 더 높은 값을 선택하여 return
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
	 * LCS의 DP 방법
	 * 
	 * DPTable에 첫번 째 열과 행은 모두 0으로 초기화 하였으므로
	 * 점화식에 근거한 1번 조건은 3번조건 실행 시 같지않을경우 0으로 값이 들어와 모두 충족한다.
	 * 
	 * 따라서 2번조건과 3번조건만 충족시키면 되므로
	 * 2번 조건은 같을시 각 index를 1씩 줄인 그전 값에 1을 더하고
	 * 3번 조건은 DPTable의 index1-1, index2 와 index1, index2-1 의 값 중 큰 값을 쓰게 된다.
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
