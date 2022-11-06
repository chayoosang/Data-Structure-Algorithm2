package week10MinimalSpanningTree;

import java.util.Arrays;
import java.util.Comparator;

import week9Graph.GraphInMatrix;
/**
 * Kruskal 알고리즘 구현
 * @author 차유상
 *
 */
public class Kruskal {
	/**
	 * edge => 연결된 가중치 표현
	 * check => kruskal 알고리즘을 통해 연결되었는지 표현
	 * parents => 사이클 테이블 / root를 표현하여 cycle을 형성하는지 표현
	 * result => 연결된 가중치의 합 / count => 연결된 선의 수
	 */
	int[][] edge;
	boolean [] check;
	int[] parents;
	int result;
	int count;
	
	/**
	 * Kruskal 초기 설정
	 * parents는 vertices의 수로 표현
	 * @param edge
	 * @param vertices
	 */
	public Kruskal(int[][] edge, int vertices) {
		this.edge = edge;
		check = new boolean[edge.length];
		parents = new int[vertices];
		result = 0;
		count = 0;
	}
	/**
	 * parents -1로 초기화
	 */
	private void init() {
		for(int i=0; i<parents.length; i++) {
			parents[i] = -1;
		}
	}
	/**
	 * edge를 가중치의 오름차순으로 정렬하는 메서드
	 * 
	 * Arrays.sort를 통해 정렬
	 */
	private void sort() {
		Arrays.sort(edge,(o1, o2) ->{
			return Integer.compare(o1[2], o2[2]);
		});
	
	}
	/**
	 * Kruskal 알고리즘 구현하는 메서드
	 * 
	 * edge의 수 만큼 kruskal 구현
	 * union 메서드를 통해 true이면 해당 edge 연결
	 * count가 parents의 수랑 같은 경우 모두 연결된것이므로 break
	 */
	public void kruskal() {
		for(int i=0; i<edge.length; i++) {
			if(union(edge[i][0]-1, edge[i][1]-1)) {
				result += edge[i][2];
				count++;
				check[i] = true;
			}
			
			if(count == parents.length)
				break;
		}
	}
	/**
	 * cycle을 형성하는지 확인하는 메서드
	 * 
	 * find를 통해 root를 찾아 root가 같은 경우 cycle을 형성하므로 false
	 * root가 다른 경우 cycle을 형성하지 않으므로 큰 root의 parent는 작은 root로 삽입 후 true return
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean union(int i, int j) {
		int iRoot = find(i);
		int jRoot = find(j);
		
		if(iRoot != jRoot) {
			parents[jRoot] = iRoot;
			return true;
		}
		return false;
	}
	/**
	 * parents를 찾아주는 메서드
	 * 
	 * parents를 초기에 -1로 초기화 하였으므로 0보다 작을시 자기 자신을 return
	 * 0보다 클 시 해당 parents에 저장되어있는 값의 parents를 찾아 return
	 * @param i
	 * @return
	 */
	private int find(int i) {
		if(parents[i]<0) return i;
		else {
			parents[i] = find(parents[i]);
			return parents[i];
		}
	}
	
	/**
	 * Kruskal의 구현을 보여주는 메서드
	 */
	private void showKruskal() {
		for(int i=0; i<edge.length; i++) {
			for(int j=0; j<3; j++) {
				if(j==0)
					System.out.print(edge[i][j]+" 연결된 node : ");
				else if(j==1)
					System.out.print(edge[i][j]+" 가중치 : ");
				else
					System.out.println(edge[i][j]+" 연결된 여부 : "+check[edge[i][0]]);
			}
		}
		System.out.println("결과 값 : "+ result);
	}

	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] vertices = { "1", "2", "3", "4", "5", "6", "7"};
		int [][] graphEdges = { {1,2,8}, {1,4,9}, {1,5,11}, {2,3,10}, {3,4,5}, {4,5,13}, {4,7,12}, {5,6,8}, {6,7,7}};
		GraphInMatrix myGM = new GraphInMatrix(maxNoVertex);
		
		myGM.createGraph("TestKruskal");
		for(int i=0; i<vertices.length; i++) {
			myGM.insertVertex(vertices[i]);
		}
		for (int i = 0; i<graphEdges.length; i++)
			myGM.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1], graphEdges[i][2]);
		myGM.showGraph();
		
		Kruskal testKruskal = new Kruskal(graphEdges, vertices.length);
		testKruskal.init();
		testKruskal.sort();
		testKruskal.kruskal();
		testKruskal.showKruskal();

	}



}
