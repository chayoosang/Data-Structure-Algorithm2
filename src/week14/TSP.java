package week14;

import java.util.Arrays;
import java.util.LinkedList;

public class TSP {
	int[][] ad;
	LinkedList<Node> tsp = new LinkedList<>();
	
	public class Node {
		int weight;
		public int getWeight() {
			return weight;
		}
		public int[] getPrevious() {
			return previous;
		}
		int [] previous;
		
		public Node(int weight, int[] previous) {
			this.weight = weight;
			this.previous = Arrays.copyOf(previous, previous.length);
		}
	}
	
	
	public TSP(int [][] input) {
		ad = input;
	}
	
	public void find() {
		boolean[] visited = new boolean[ad.length];
		int[] previous = new int[ad.length];
		int start = 0;
		int count = 0;
		int weight = 0;
		Arrays.fill(visited, false);
		Arrays.fill(previous,-1);
		
		visited[start] = true;
		previous[0] = start;
		count++;
		find(start, visited, previous, count, start, weight);
	}
	
	private void find(int start, boolean[] visited, int[] previous, int count, int end, int weight) {
		
		for(int i =0; i<ad[start].length; i++) {
			if(i==end&&count==ad.length) {
				tsp.add(new Node(weight+ad[start][end], previous));
			}
			if(ad[start][i]>0&&visited[i]==false&&count<ad.length) {
				visited[i] = true;
				previous[count] = i;
				weight += ad[start][i];
				count++;
				find(i, visited, previous, count, end, weight);
				visited[i] = false;
				weight -= ad[start][i];
				count--;
				previous[count] = -1;
			}
		}
	}
	
	public void findMin() {
		int index=9999;
		int min = 9999;
		for(int i=0; i<tsp.size(); i++) {
			if(min>tsp.get(i).getWeight()) {
				min=tsp.get(i).getWeight();
				index = i;
			}
		}
		
		System.out.println("최소 가중치를 가진 Cycle");
		System.out.println("가중치 : "+tsp.get(index).getWeight());
		for(int i=0; i<tsp.get(index).getPrevious().length; i++)
			System.out.print( " => "+ tsp.get(index).getPrevious()[i]);
	}

	public static void main(String[] args) {
		 int[][] input = {{0, 10, 10, 30, 25}, {10, 0, 14, 21, 10}, {10, 18, 0, 7, 9}, {8, 11, 7, 0, 3}, {14, 10, 10, 3, 0}};
		 TSP me = new TSP(input);
		 me.find();
		 me.findMin();
	}

}
