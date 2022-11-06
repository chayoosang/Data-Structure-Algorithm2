package week7DP;
/**
 * 0/1 Knapsack을 recursive와 DP 비교
 * @author 차유상
 *
 */
public class Knapsack {
	/**
	 * item의 가격과 무게를 저장할 item[][]생성
	 * DP방식으로 가격을 비교할 DPTable[][]생성
	 * recursive와 DP를 비교하기위한 count  생성
	 */
	int[][] item;
	int[][] DPTable;
	int count;
	
	/**
	 * item은 main에서 생성한 input으로 받는다
	 * DPTable 기존 item보다 행과 열을 1씩 증가하여 생성 (item의 개수와 가방의 weight를 1부터 표현하기 위해)
	 * 
	 * @param input
	 * @param bagWeight
	 */
	public Knapsack(int[][] input, int bagWeight) {
		item = input;
		DPTable = new int [input.length+1][bagWeight+1];
	}
	/**
	 * count = 0으로 초기화
	 * item의 개수와 가방의 weight를 1부터 표현하기 위해 0행과 0열은 모두 0으로 표시
	 */
	public void reset() {
		count = 0;
		for(int i=0; i<DPTable[0].length; i++)
			DPTable[0][i] = 0;
		for(int j=0; j<DPTable.length; j++)
			DPTable[j][0] = 0;			
	}

	/**
	 * 0/1 Knapsack을 recursive하게 표현
	 * 
	 * 1. 가방의 무게와 아이템의 개수를 모두 소진시 0으로 return
	 * 2. price = item[index]의 가격 / itemWeight = item[index]의 무게
	 * 가방 무게에 제한이 안될시 현재 item의 가격과 이전 item의 가격을 더한것과 
	 * 자신을 포함하지 않았을경우 item의 여태 가격과 비교하여 큰것을 return
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
	 * 0/1 Knapsack을 DP로 표현
	 * 
	 * DPTable에서 0 행과 0열은 모두 0으로 표시했으므로 1행과 1열부터 시작
	 *  j는 1부터 가방의 무게까지로 표현
	 *  j-itemWeight => 가방에 item이 들어갈수 있을경우 DPTable에서 j-itemWeight 즉 들어갈수 있을 최대의 값과 현재의 값을 더한 값과
	 *  해당 가방의 무게 j에서 이전 item에서 여태 포함된 가격을 비교하여 최대값을 저장
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
