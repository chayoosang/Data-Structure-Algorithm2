package week13AStar;

import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleAStar {
	
	private ArrayList<int[][]> node;
	private int[][] object;
	private boolean[] visited;
	private int count;
	
	public PuzzleAStar(int[][] objectPuzzle) {
		this.node = new ArrayList<>();
		this.object = objectPuzzle;
		this.count = 1;
		this.visited = new boolean[9];
		Arrays.fill(visited, false);
	}
	
	
	public void aStar(int[][] start) {
		int row=0;
		int column=0;
		
		this.node.add(start);
		
		if(Arrays.equals(start, object))
			return;
		
		for(int i=0; i<start.length; i++) {
			for(int j=0; j<start[0].length; j++) {
				if(start[i][j]==0) {
					row=j;
					column=i;
				}
			}
		}
		start = minAstar(start, row, column);
		aStar(start);

	}
	private int[][] minAstar(int[][] start, int row, int column){
		int[][] prev = start;
		int diffrent = 0;
		int minArray[] = new int[4];
		int min =999;
		int index = 4;
		
		Arrays.fill(minArray, 999);
		
		if(column>0) {
		if(visited[start[column-1][row]]!=true) {
			start[column][row] = start[column-1][row];
			start[column-1][row] = 0;
			for(int i=0; i<start.length; i++) {
				for(int j=0; j<start[0].length; j++) {
					if(this.object[i][j]!=start[i][j]) 
						diffrent++;
				}
			}
			minArray[0] = diffrent+count;
			start = prev;
			diffrent = 0;
		}
		}
		
		if(column<2) {
		if(visited[start[column+1][row]]!=true) {
			start[column][row] = start[column+1][row];
			start[column+1][row] = 0;
			for(int i=0; i<start.length; i++) {
				for(int j=0; j<start[0].length; j++) {
					if(this.object[i][j]!=start[i][j]) 
						diffrent++;
				}
			}
			minArray[1] = diffrent+count;
			start = prev;
			diffrent = 0;
		}
		}
		
		if(row>0) {
		if(visited[start[column][row-1]]!=true) {
			start[column][row] = start[column][row-1];
			start[column][row-1] = 0;
			for(int i=0; i<start.length; i++) {
				for(int j=0; j<start[0].length; j++) {
					if(this.object[i][j]!=start[i][j]) 
						diffrent++;
				}
			}
			minArray[2] = diffrent+count;
			start = prev;
			diffrent = 0;
		}
		}
		
		if(row<2) {
		if(visited[start[column][row+1]]!=true) {
			start[column][row] = start[column][row+1];
			start[column][row+1] = 0;
			for(int i=0; i<start.length; i++) {
				for(int j=0; j<start[0].length; j++) {
					if(this.object[i][j]!=start[i][j]) 
						diffrent++;
				}
			}
			minArray[3] = diffrent+count;
			start = prev;
			diffrent = 0;
		}
		}
		
		for(int i=0; i<minArray.length; i++) {
			if(min>minArray[i]) {
				min=minArray[i];
				index = i;
			}
		}
		
		switch(index) {
		case 0:
			start[column][row] = start[column-1][row];
			start[column-1][row] = 0;
			this.visited[start[column][row]] = true;
			break;
		case 1:
			start[column][row] = start[column+1][row];
			start[column+1][row] = 0;
			this.visited[start[column][row]] = true;
			break;
		case 2:
			start[column][row] = start[column][row-1];
			start[column][row-1] = 0;
			this.visited[start[column][row]] = true;
			break;
		case 3:
			start[column][row] = start[column][row+1];
			start[column][row+1] = 0;
			this.visited[start[column][row]] = true;
			break;
		case 4:
			System.out.println("¿À·ù");
			break;
			
		}
		count++;
		return start;
	}
	
	public void print() {
		for(int i=0; i<node.size(); i++) {
			System.out.println(Arrays.deepToString(node.get(i)));
		}
	}
	

	public static void main(String[] args) {
		int[][] startPuzzle = {{2,8,3}, {1,6,4}, {7,0,5}};
		int[][] objectPuzzle = {{1,2,3},{8,0,4},{7,6,5}};
		
		PuzzleAStar astar = new PuzzleAStar(objectPuzzle);
		astar.aStar(startPuzzle);
		astar.print();
		
	}

}
