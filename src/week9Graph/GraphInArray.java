package week9Graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
/**
 * 그래프를 Array로 구현
 * @author 차유상
 *
 */
public class GraphInArray {
	/**
	 * vertices와 adjacent를 Array로 구현
	 */
	String graphName;
	String[] vertices;
	String [][] adjacentArray ;
	int maxNumber = 0;
	boolean [] visited ; 
	
	/**
	 * 그래프를 표현할 최대 개수 지정
	 * @param maxN
	 */
	public GraphInArray(int maxN) {
		maxNumber = maxN ;
		visited = new boolean [maxNumber];
	}
	/**
	 * 그래프를 만드는 메서드
	 * 이 때 vertices는 String Array로 adjacent는 Integer Array로 만든다.
	 * @param name
	 */
	public void createGraph(String name) {
		graphName = name;
		vertices = new String[maxNumber];
		adjacentArray = new String [maxNumber][maxNumber];	
	}
	
	public void showGraph() {
		showGraphInArray();
	}
	/**
	 * 그래프를 출력하는 메서드
	 * for문을 활용해 0부터 vertices의 길이까지 표현
	 * 이때 vertices의 값이 null일 경우 그래프를 출력하지 않는다.
	 */
	private void showGraphInArray() {
		System.out.println("\n< "+graphName+" in AdjacentArray >");
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==null)
				break;
			System.out.print(vertices[i]+"  ");
			for (int j=0; j<vertices.length;j++) {
				if(adjacentArray[i][j]==null)
					System.out.print(" X ");
				else
				System.out.print( adjacentArray[i][j]+" ");
			}
			System.out.println();
		}		
	}
	/**
	 * 새로운 Vertex를 삽입하는 메서드
	 * vertices에 s가 저장되어있을 경우 check를 true로 저장
	 * vertices에 s가 저장 안되어 있다면 check에 false를 저장
	 * 또한 vertices가 저장 안되어 있는 자리를 index로 받아
	 * check가 false일 시 vertices[index]에 s를 저장  
	 * @param s
	 */
	public void insertVertex(String s) {
		boolean check = false;
		int index=0;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==s)
				check = true;
			if(vertices[i]==null) {
				index = i;
				break;
				}
			}
		if(check == false)
			vertices[index] = s;
	}
	/**
	 * Edge를 삽입하는 메서드
	 * 먼저 insertVertex를 통해 from과 to를 있는지 확인 후 없다면 만들어준다.
	 * 그 후 f와 t의 index를 구한 후 화살표를 가지고 있는 edge를 추가를 해준다.
	 * @param from
	 * @param to
	 */
	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);
		int f=-1;
		int t=-1;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==from) 	f = i;
			 if(vertices[i]==to)		t = i;
			}
		if(t>=0&&f>=0)
			adjacentArray[f][t]= vertices[t];
	}	
	
	/**
	 * Vertex를 제거하는 메서드
	 * 
	 * 삭제하고자 하는 Vertex의 index를 찾은 후 
	 * 해당 Vertex의 자리가 삭제되면 비어지기 위해 한칸씩 옯겨 adjacent 즉 edge를 재 정렬한다.
	 * 이 후 해당 Vertex 가 관계를 맺고 있던 edge를 모두 null로 초기화를 시킨다.
	 * 마지막으로 vertices에서 해당 vertex를 삭제한다.
	 * @param s
	 */
	public void deleteVertex(String s) {
		int index=-1;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==s)
				index = i;
			}
		if (index>=0) {
			int n = vertices.length;
			for (int i=index+1; i<n; i++) 
				for (int j=0; j<n; j++) 
					adjacentArray[i-1][j] = adjacentArray[i][j];
			for (int i=index+1; i<n; i++) 
				for (int j=0; j<n; j++) 
					adjacentArray[j][i-1] = adjacentArray[j][i];
			// reset n-1 th row & column 
			for (int i=0;i<n; i++) {
				adjacentArray[i][n-1] = null;
				adjacentArray[n-1][i] = null;
			}
			if (index>=0)
				vertices[index] = null;
		}
	}
	/**
	 * Edge를 삭제하는 메서드
	 * 
	 * 먼저 form과 to의 Vertex index를 찾은 후
	 * 그 둘의 관계인 edge를 null으로 초기화한다.
	 * @param from
	 * @param to
	 */
	public void deleteEdge(String from, String to) {
		int f=-1;
		int t =-1;
		for(int i=0; i<vertices.length; i++) {
			if(vertices[i]==from)
				f=i;
			else if(vertices[i]==to)
				t=i;
		}
		if (f>=0 && t>=0) {
			adjacentArray[f][t] = null ;
		}
	}
	
	/**
	 * graph가 비어있는지 체크하기 위한 메서드
	 * @return
	 */
	public boolean isEmpty() {
		boolean check = false;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]!=null)
				check = false;
		else 
			check = true;
	}
		return check;
	}
	
	/**
	 * vertex가 edge를 맺고있는 다른 vertex를 찾는 메서드
	 * 
	 * vertex s의 index를 찾은 후 adjacentArray가 null이 아닐 경우 vertices[i]를 통해 관계를 맺고 잇는 vertex를 저장한다.
	 * @param s
	 * @return
	 */
	public HashSet<String> adjacent(String s) {
		HashSet<String> result= new HashSet<>();
		int index=-1;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==s)
				index = i;
			}
		int j=0;
		if (index>=0) {
			for (int i=0; i<vertices.length;i++) {
				if (adjacentArray[index][i]!=null) {
					result.add(vertices[i]); 
				}
			}
		}
		return result;
	}
	
	/**
	 * visited을 초기화 하는 메서드
	 */
	public void initVisited() {
		for (int i=0; i<vertices.length;i++) 
			visited[i] = false;
	}
	
	
	/**
	 * DFS(깊이우선탐색) 메서드
	 * 
	 * visited를 초기화 한 후 DFS를 실행한다.
	 * @param s
	 */
	public void DFS(String s) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecusrsion(s);
	}
	/**
	 * DFS(깊이우선탐색)를 실행하는 메서드
	 * 
	 * 여러 갈래의 길을 하나씩 따져보는 탐색 방법이다.
	 * Tree에서 이어져 있는 자식의 자식 길을 하나씩 따져보는 방식
	 * @param s
	 */
	private void DFSRecusrsion(String s) {
		int index=-1;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==s)
				index = i;
			}
		visited[index]=true;
		System.out.println(s+" is visited ");
		for (int i=0; i<vertices.length;i++) {
			if (adjacentArray[index][i]!=null && !visited[i]) {
				DFSRecusrsion(vertices[i]);
			}
		}
	}	
	/**
	 * BFS(너비우선탐색) 메서드
	 * 
	 * visited를 초기화 한 후 BFS를 실행한다.
	 * @param s
	 */
	public void BFS(String s) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(s);
	}
	/**
	 *  BFS(너비우선탐색)를 실행하는 메서드
	 *  Queue를 통해 Tree에 맨위에서 부터 저장을 하는 방식으로서
	 *  root부터 한줄에 하나씩 Queue에 저장을 한다.
	 * @param s
	 */
	public void BFSIteration(String s) {
		Deque<String> que = new ArrayDeque<String>();
		int index=-1;
		for (int i=0; i<vertices.length; i++){
			if(vertices[i]==s)
				index = i;
			}
		visited[index]=true;
		System.out.println(s+" is visited ");
		que.add(s);
		
		while (!que.isEmpty()) {
			String v = que.poll();
			for (int i=0; i<vertices.length; i++){
				if(vertices[i]==v)
					index = i;
				}
			for (int i=0; i<vertices.length;i++) {
				if (adjacentArray[index][i]!=null && !visited[i]) {
					visited[i]=true;
					System.out.println(vertices[i]+" is visited ");
					que.add(vertices[i]);
				}
			}
		}
	}
	
}
