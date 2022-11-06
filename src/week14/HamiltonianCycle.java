package week14;

import java.util.Arrays;

public class HamiltonianCycle {
	int[][] ad;
	
	public HamiltonianCycle(int [][] input) {
		ad = input;
	}
	
	public void find() {
		boolean[] visited = new boolean[ad.length];
		int[] previous = new int[ad.length];
		int start = 0;
		int count = 0;
		Arrays.fill(visited, false);
		Arrays.fill(previous,-1);
		
		visited[start] = true;
		previous[0] = start;
		count++;
		find(start, visited, previous, count, start);
	}
	
	private void find(int start, boolean[] visited, int[] previous, int count, int end) {
		
		for(int i =0; i<ad[start].length; i++) {
			if(i==end&&count==ad.length) {
					System.out.println("해밀턴 사이클 O");
					for(int j=0; j<previous.length; j++) 
						System.out.print( " => "+ previous[j]);
					System.out.println();
					return;
			}
			if(ad[start][i]>0&&visited[i]==false&&count<ad.length) {
				visited[i] = true;
				previous[count] = i;
				count++;
				find(i, visited, previous, count, end);
				visited[i] = false;
				count--;
				previous[count] = -1;
			}
		}
	}

	public static void main(String[] args) {
		 int[][] input = {{0, 10, 10, 30, 25}, {10, 0, 14, 21, 10}, {10, 18, 0, 7, 9}, {8, 11, 7, 0, 3}, {14, 10, 10, 3, 0}};
		 HamiltonianCycle me = new HamiltonianCycle(input);
		 me.find();
	}

}
