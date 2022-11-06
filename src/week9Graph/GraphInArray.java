package week9Graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
/**
 * �׷����� Array�� ����
 * @author ������
 *
 */
public class GraphInArray {
	/**
	 * vertices�� adjacent�� Array�� ����
	 */
	String graphName;
	String[] vertices;
	String [][] adjacentArray ;
	int maxNumber = 0;
	boolean [] visited ; 
	
	/**
	 * �׷����� ǥ���� �ִ� ���� ����
	 * @param maxN
	 */
	public GraphInArray(int maxN) {
		maxNumber = maxN ;
		visited = new boolean [maxNumber];
	}
	/**
	 * �׷����� ����� �޼���
	 * �� �� vertices�� String Array�� adjacent�� Integer Array�� �����.
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
	 * �׷����� ����ϴ� �޼���
	 * for���� Ȱ���� 0���� vertices�� ���̱��� ǥ��
	 * �̶� vertices�� ���� null�� ��� �׷����� ������� �ʴ´�.
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
	 * ���ο� Vertex�� �����ϴ� �޼���
	 * vertices�� s�� ����Ǿ����� ��� check�� true�� ����
	 * vertices�� s�� ���� �ȵǾ� �ִٸ� check�� false�� ����
	 * ���� vertices�� ���� �ȵǾ� �ִ� �ڸ��� index�� �޾�
	 * check�� false�� �� vertices[index]�� s�� ����  
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
	 * Edge�� �����ϴ� �޼���
	 * ���� insertVertex�� ���� from�� to�� �ִ��� Ȯ�� �� ���ٸ� ������ش�.
	 * �� �� f�� t�� index�� ���� �� ȭ��ǥ�� ������ �ִ� edge�� �߰��� ���ش�.
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
	 * Vertex�� �����ϴ� �޼���
	 * 
	 * �����ϰ��� �ϴ� Vertex�� index�� ã�� �� 
	 * �ش� Vertex�� �ڸ��� �����Ǹ� ������� ���� ��ĭ�� ���� adjacent �� edge�� �� �����Ѵ�.
	 * �� �� �ش� Vertex �� ���踦 �ΰ� �ִ� edge�� ��� null�� �ʱ�ȭ�� ��Ų��.
	 * ���������� vertices���� �ش� vertex�� �����Ѵ�.
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
	 * Edge�� �����ϴ� �޼���
	 * 
	 * ���� form�� to�� Vertex index�� ã�� ��
	 * �� ���� ������ edge�� null���� �ʱ�ȭ�Ѵ�.
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
	 * graph�� ����ִ��� üũ�ϱ� ���� �޼���
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
	 * vertex�� edge�� �ΰ��ִ� �ٸ� vertex�� ã�� �޼���
	 * 
	 * vertex s�� index�� ã�� �� adjacentArray�� null�� �ƴ� ��� vertices[i]�� ���� ���踦 �ΰ� �մ� vertex�� �����Ѵ�.
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
	 * visited�� �ʱ�ȭ �ϴ� �޼���
	 */
	public void initVisited() {
		for (int i=0; i<vertices.length;i++) 
			visited[i] = false;
	}
	
	
	/**
	 * DFS(���̿켱Ž��) �޼���
	 * 
	 * visited�� �ʱ�ȭ �� �� DFS�� �����Ѵ�.
	 * @param s
	 */
	public void DFS(String s) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecusrsion(s);
	}
	/**
	 * DFS(���̿켱Ž��)�� �����ϴ� �޼���
	 * 
	 * ���� ������ ���� �ϳ��� �������� Ž�� ����̴�.
	 * Tree���� �̾��� �ִ� �ڽ��� �ڽ� ���� �ϳ��� �������� ���
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
	 * BFS(�ʺ�켱Ž��) �޼���
	 * 
	 * visited�� �ʱ�ȭ �� �� BFS�� �����Ѵ�.
	 * @param s
	 */
	public void BFS(String s) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(s);
	}
	/**
	 *  BFS(�ʺ�켱Ž��)�� �����ϴ� �޼���
	 *  Queue�� ���� Tree�� �������� ���� ������ �ϴ� ������μ�
	 *  root���� ���ٿ� �ϳ��� Queue�� ������ �Ѵ�.
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
