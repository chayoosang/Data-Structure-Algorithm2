package week13AStar;

import java.util.ArrayList;
import java.util.Arrays;

import week9Graph.GraphInList;

/**
 * Dijkstra 알고리즘 구현
 * 
 * GraphInList에서 adjacentList를 String이 아닌 class Element로 구현
 * class Element{String source, int weight}로 구현
 * @author 차유상
 *
 */
public class DijkstraAStar extends GraphInList{
	/**
	 * 어디서 방문했는지 기록하기 위한 prev
	 * 정점들의 가중치를 모두 기록하기 위한 prevWeight
	 * prevWeight에서 최소의 가중치를 기록하기 위한 currentWeight
	 * 해당 정점이 방문되었는지 알기위한 visit
	 * 방문 횟수를 기록하기 위한 count
	 */
	ArrayList<String> prev;
	int[] prevWeight;
	int[] currentWeight;
	int[] heuristic;
	boolean[] visit;
	int count = 0;
	boolean firstStart = true;
	
	/**
	 * graphInList를 상위 Class로 받아 super를 통해 Graph 구현
	 * @param size
	 * @param heuristic 
	 */
	public DijkstraAStar(int size, int[] heuristic) {
		super(size);
		this.heuristic = heuristic;
	}
	/**
	 * Dijkstra를 구현하기 위한 초기화
	 * @param size
	 */
	public void init(int size) {
		prev = new ArrayList<>();
		visit = new boolean[size];
		Arrays.fill(visit, false);
		prevWeight = new int[size];
		Arrays.fill(prevWeight, 9999);
		prevWeight[0] = 0;
		currentWeight = new int[size];
		Arrays.fill(currentWeight, 9999);
		currentWeight[0] = 0;
	}
	
	/**
	 * Dijkstra 알고리즘 구현
	 * 
	 * start를 받아 해당 정점에서부터 시작
	 * 
	 * count가 정점의 수와 같아지면 모두 방문되었으므로 break
	 * extractMin을 통해 start와 맺고 있는 가중치와 작은 정점을 index로 받고
	 * 해당 index를 방문했다는 표시로 visit[index]를 true로 기록
	 * 그 후 dijkstra를 index로 다시 받아 재귀적 표현
	 * @param start
	 */
	public void dijkstra(int start, int end) {
		int index;
		count++;
		
			index = extractMin(start, end);
			if(index==end) {
				prev.add(vertices.get(end));
				return;
				}
			visit[index] = true;
			dijkstra(index, end);

	}
	
	/**
	 * 최소의 가중치를 구하기 위한 메서드
	 * 
	 *
	 * 만약 extractMin을 처음으로 실행되었다면(firstStart를 통해 확인 가능) 정점이 가진 진출간선의 가중치를 prevWeight에 기록 및 prev[start를 진입간선으로 맺은 정점]에 start을 기록 
	 * 
	 * 만약  extractMin을 처음으로 실행되지 않았다면(firstStart를 통해 확인 가능) 정점이 가진 진입간선의 가중치와 자신이 가진 진출간선의 가중치를 더하여 
	 * prevWeight보다 작은경우 prevWeight에 기록하고 prev[start를 진입간선으로 맺은 정점]에 start을 기록한다.
	 *
	 *그 후 prevWeight에서 최솟값을 currentWeight에 기록 후 해당 index를 return
	 * @param start
	 * @return
	 */
	private int extractMin(int start, int end) {
		int min=100;
		int index=0;
		
		if(firstStart == true) {
			for(int j=0; j<adjacentList.get(0).size(); j++) {
				Element e = adjacentList.get(0).get(j);
				for(int q=1; q<vertices.size(); q++) {
					if(e.getSource().equals(vertices.get(q))) {
						if(q==end) {
							prev.add(vertices.get(start));
							return end;
							}
						prevWeight[q] = e.getWeight();
						break;
						}
					}
				}
			firstStart = false;
			}
		else{
			for(int j=0; j<adjacentList.get(start).size(); j++) {
				Element e = adjacentList.get(start).get(j);
				for(int q=1; q<vertices.size(); q++) {
					if(e.getSource().equals(vertices.get(q))&&prevWeight[q]>currentWeight[start]+e.getWeight()&&visit[q]!=true) {
						if(q==end) {
							prev.add(vertices.get(start));
							return end;
							}
						prevWeight[q] = e.getWeight()+currentWeight[start];
						break;
						}
					}
				}
			}

		for(int j=0; j<heuristic.length; j++) {
			if(min>prevWeight[j]+heuristic[j]&&prevWeight[j]>0) {
				min = prevWeight[j]+heuristic[j];
				index = j;
			}
		}
		currentWeight[index] = prevWeight[index];
		prevWeight[index]=0;
		prev.add(vertices.get(start));
		return index;
	}
	
	
	public void showDijkstra() {
		for(int i=0; i<prev.size(); i++) {
			if(i==0)
				System.out.print("Start => "+vertices.get(i));
			else {
				System.out.print(" => "+prev.get(i));
			}
		}
	}
	
	
	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] vertices = { "0", "1", "2", "3", "4", "5", "6","7","8","9"};
		int [][] graphEdges = { {0,1,10}, {0,2,30}, {0,4,17}, {0,5,23}, {0,6,25}, {1,4,20}, {2,1,19}, {2,3,24}, {3,9,20}, {4,6,17}, {5,2,16}, {5,3,18}, {6,7,39}, {6,8,25}, {7,5,20}, {7,9,28}, {8,7,29}, {9,8,40}};
		
		int[] heuristic = {52, 61, 40, 19, 68, 34, 52, 19, 39};
		
		DijkstraAStar dijkstra = new DijkstraAStar(maxNoVertex, heuristic);
		
		
		dijkstra.createGraph("Dijkstra");		
		for (int i = 0; i<vertices.length; i++)
			dijkstra.insertVertex(vertices[i]);
		for (int i = 0; i<graphEdges.length; i++)
			dijkstra.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		dijkstra.showGraph();
		
		dijkstra.init(vertices.length);
		dijkstra.dijkstra(0,9);
		dijkstra.showDijkstra();
	}

}
