package week12ShorteestPaths;

import java.util.Arrays;

import week9Graph.GraphInList;
import week9Graph.GraphInList.Element;


public class BellmanFord2 extends GraphInList{
	
	private int weightArray[];
	private String toEdge[];
	private int size;
	private boolean check;
	
	public BellmanFord2(int maxNumber) {
		super(maxNumber);
	}
	
	public void init(int num) {
		this.check = false;
		this.size = num;
		this.weightArray = new int[num];	
		Arrays.fill(weightArray, 9999);
		this.toEdge = new String[num];
		this.weightArray[0] = 0;
	}
	
	public void bellmanford() {
		for(int i=0; i<this.size; i++) {
			if(adjacentList.get(i)!=null) {
				for(Element e : adjacentList.get(i)) {
					for(int j=0; j<this.size; j++) {
						if(e.getSource().equals(vertices.get(j))&&this.weightArray[i]!=9999&&this.weightArray[j]>weightArray[i]+e.getWeight()) {
								this.weightArray[j] =weightArray[i]+e.getWeight();
								this.toEdge[j] = vertices.get(i);
								break;
							}
					}
				}
				}
			else continue;
			
		}
		
		for(int i=0; i<adjacentList.size(); i++) {
			for(Element e : adjacentList.get(i)) {
				for(int j=0; j<this.size; j++) {
					if(e.getSource().equals(vertices.get(j))&&this.weightArray[j]>weightArray[i]+e.getWeight()) {
						this.check = true;
						return;
						}
				}
			}
		}
		
	}
	
	public void print() {
		if(this.check == true)
			System.out.println("\n음의 사이클 존재!");
		else {
		for(int i=0; i<this.size; i++) {
			if(i==0)
				System.out.println("Start => "+vertices.get(i));
			else
				System.out.println(vertices.get(i)+"과 연결된 정점 : "+this.toEdge[i]+", 가중치 : "+this.weightArray[i]);
		}
		}
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] vertices = { "서울", "인천", "대전", "대구", "광주", "부산", "울산"};
		int [][] graphEdges = { {0,1,11}, {2,0,-8}, {0,3,9}, {1,3,13}, {1,6,8}, {2,4,10}, {3,2,-3}, {3,4,5}, {3,5,12}, {5,6,7}};
		
		BellmanFord2 myBellmanford = new BellmanFord2(maxNoVertex);
		
		 
		myBellmanford.createGraph("BellmanFord");		
		for(int i=0; i<vertices.length; i++)
			myBellmanford.insertVertex(vertices[i]);
		for (int i = 0; i<graphEdges.length; i++)
			myBellmanford.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myBellmanford.showGraph();
		
		myBellmanford.init(vertices.length);
		myBellmanford.bellmanford();
		myBellmanford.print();
		
	}

}
