package week10MinimalSpanningTree;

import java.util.ArrayList;

import java.util.LinkedList;

import week9Graph.GraphInMatrix;

public class Prim {
	
	
	boolean[] visited;
	int[] pastminWeight;
	int[] currentminWeight;
	int maxNum;
	int count;
	int result;
	
	
	
	public Prim(int maxN) {
		maxNum = maxN;
		visited = new boolean[maxNum];
		
		pastminWeight = new int[maxNum];
		currentminWeight = new int[maxNum];
		
	}
	
	public void init() {
		count=0;
		result=0;
		for(int i=0; i<maxNum; i++) {
			visited[i] = false;
			pastminWeight[i] =100;
			currentminWeight[i] =100;
			if(i==0) {
				pastminWeight[i] = 0;
				currentminWeight[i] = 0;
			}
			
		}
	}
	
	public void prim(int[][] adjacent, int r) {
		count++;
		visited[r] = true;
		int index;
		
		while(true) {
			if(count == maxNum) break;
			index = extractMin(adjacent, r);
			prim(adjacent, index);
		}
	}
	
	private int extractMin(int[][] adjacent, int r) {
		int min=100;
		int index=0;
		for(int i=0; i<adjacent[0].length; i++) {
			if(i!=0&&adjacent[r][i]>0&&!visited[i]&&pastminWeight[i]>adjacent[r][i]) pastminWeight[i] = adjacent[r][i];
		}
		for(int j=0; j<pastminWeight.length; j++) {
			if(min>pastminWeight[j]&&pastminWeight[j]>0) {
				min = pastminWeight[j];
				index = j;
			}
		}
		currentminWeight[index] = pastminWeight[index];
		pastminWeight[index] = 0;

		result += min;
		return index;
	}
	
	public void showPrim() {
		for(int i=0; i<maxNum; i++) {
			System.out.println((i+1)+" 번 째 최소 가중치 : "+currentminWeight[i] + " 방문 :  " + visited[i]);
		}
		

		System.out.println("result : " + result);
	}

	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] vertices = { "1", "2", "3", "4", "5", "6", "7"};
		int [][] graphEdges = { {1,2,8}, {1,4,9}, {1,5,11}, {2,3,10}, {3,4,5}, {4,5,13}, {4,7,12}, {5,6,8}, {6,7,7}};
		GraphInMatrix myGM = new GraphInMatrix(maxNoVertex);
		
		myGM.createGraph("TestPrim");
		for(int i=0; i<vertices.length; i++) {
			myGM.insertVertex(vertices[i]);
		}
		for (int i = 0; i<graphEdges.length; i++)
			myGM.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1], graphEdges[i][2]);
		myGM.showGraph();
		
		Prim primTest = new Prim(vertices.length);
		primTest.init();
		primTest.prim(myGM.getAdjacentMatrix(), 0);
		primTest.showPrim();
		
		
		

		}

}
