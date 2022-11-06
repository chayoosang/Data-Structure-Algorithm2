package week11TopologicalSorting;

import java.util.Arrays;

import java.util.LinkedList;
/**
 * 위상 정렬을 GraphInList로 구현
 * 
 * @author 차유상
 *
 */
public class TopologicalSorting extends GraphInList{
	/**
	 * GraphInList를 부모 Class로 받아 maxNoSize super
	 * @param size
	 */
	public TopologicalSorting(int size) {
		super(size);
	}
	/**
	 * 위상정렬 알고리즘1
	 * 진입간선이 없는 정점을 getNextNode로 모든 정점을 검사하여 A array에 순서대로 받음.
	 * 이 때 array A에 있는 정점은 Graph에서 삭제를 하여 해당 정점이 가진 진출간선 즉 다른 정점의 입장에서 진입간선을 삭제.
	 * 그 후 다시 getNextNode를 통해 모든 정점이 들어올때까지 반복.
	 * 
	 */
	public void TPSort1() {
		String[] A = new String[vertices.size()];
		int nOfVertices = vertices.size();
		
		for(int i=0; i<nOfVertices; i++) {
			A[i] = getNextNode();
			deleteVertex(A[i]);
			showGraph();
		}
		System.out.print(">> Strart");
		for(int i=0; i<nOfVertices; i++) {
			System.out.print(" => "+A[i]);
		}
		System.out.println();
	}
	/**
	 * 진입 간선이 없는 정점을 찾는 메서드
	 * 
	 * boolean Array를 현재의 정점의 개수의 size만큼의 크기로 만듬.
	 * boolean Array check를 모두 true로 설정 한 후
	 * 모든 adjacentList를 확인하여 정점이 list에 있는 경우 check를 false로 바꾼다.
	 * 그 후 check를 확인하여 true 즉 진입 간선이 없는 경우 해당 정점을 return 한다.
	 * @return
	 */
	private String getNextNode() {
		boolean[] check = new boolean[vertices.size()];
		Arrays.fill(check, true);
		
		for(int i=0; i<vertices.size(); i++) {
			for(int j=0; j<vertices.size(); j++) {
			if(adjacentList.get(i).equals(vertices.get(j)))
				check[j] = false;
			}
		}
		
		for(int i=0; i<vertices.size(); i++) {
			if(check[i]==true)
				return vertices.get(i);
		}
		return null;
	}
	
	/**
	 * 위상정렬2 알고리즘
	 * 
	 * LinkedList R과 visited boolean Array를 모두 false로 생성한다.
	 * 모든 정점을 for로 반복하여 해당 index의 visited가 false일시 dfsTS메서드를 실행하여 LinkeList R을 최신화 한다.
	 */
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[vertices.size()];
		Arrays.fill(visited, false);
		
		for(String s : vertices) {
			if(visited[vertices.indexOf(s)]==false)
				dfsTS(visited, s, R);
		}
		System.out.println(R);
	}
	/**
	 * LinkedList R을 최신화 하여 위상정렬을 구현하는 메서드
	 * 
	 * 메서드가 실행되면 해당 정점 index의 visited를 true로 저장한다.
	 * 해당 정점에서 진입간선이 없거나 진입간선을 맺고있는 정점이 방문이 완료되었다면 
	 * 해당 정점을 LinkedList의 맨 처음으로 삽입한다.
	 * 만약 진입간선을 가지고 있고 그 정점이 방문기록이 없다면 dfsTS를 재귀적으로 실행하여 LinkedList를 최신화 한다.
	 * @param visited
	 * @param s
	 * @param R
	 * @return
	 */
	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R) {
		visited[vertices.indexOf(s)] = true;
		
		for(int i=0; i<vertices.size(); i++) {
			for(String x : adjacentList.get(i))
			if(visited[vertices.indexOf(x)]==false)
				dfsTS(visited, x, R);
		}
		System.out.println(s+" is added a the first");
		R.addFirst(s);
		return R;
	}


	
	

	public static void main(String[] args) {
		
		
		int maxNoVertex = 10;
		String [] vertices = { "물", "봉지", "불", "면", "스프", "계란"};
		int [][] graphEdges = { {1,3}, {2,4}, {2,5}, {3,4}, {3,5}, {3,6}, {4,6}, {5,6}};
		
		TopologicalSorting TPSorting1 = new TopologicalSorting(maxNoVertex);
		

		TPSorting1.createGraph("TopologicalSorting1");		
		for (int i = 0; i<graphEdges.length; i++)
			TPSorting1.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		TPSorting1.showGraph();
		
		TPSorting1.TPSort1();
		
		TopologicalSorting TPSorting2 = new TopologicalSorting(maxNoVertex);
		

		TPSorting2.createGraph("TopologicalSorting2");		
		for (int i = 0; i<graphEdges.length; i++)
			TPSorting2.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		TPSorting2.showGraph();
		
		TPSorting2.TPSort2();
	}

}
