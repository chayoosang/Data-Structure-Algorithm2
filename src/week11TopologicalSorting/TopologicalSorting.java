package week11TopologicalSorting;

import java.util.Arrays;

import java.util.LinkedList;
/**
 * ���� ������ GraphInList�� ����
 * 
 * @author ������
 *
 */
public class TopologicalSorting extends GraphInList{
	/**
	 * GraphInList�� �θ� Class�� �޾� maxNoSize super
	 * @param size
	 */
	public TopologicalSorting(int size) {
		super(size);
	}
	/**
	 * �������� �˰���1
	 * ���԰����� ���� ������ getNextNode�� ��� ������ �˻��Ͽ� A array�� ������� ����.
	 * �� �� array A�� �ִ� ������ Graph���� ������ �Ͽ� �ش� ������ ���� ���Ⱓ�� �� �ٸ� ������ ���忡�� ���԰����� ����.
	 * �� �� �ٽ� getNextNode�� ���� ��� ������ ���ö����� �ݺ�.
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
	 * ���� ������ ���� ������ ã�� �޼���
	 * 
	 * boolean Array�� ������ ������ ������ size��ŭ�� ũ��� ����.
	 * boolean Array check�� ��� true�� ���� �� ��
	 * ��� adjacentList�� Ȯ���Ͽ� ������ list�� �ִ� ��� check�� false�� �ٲ۴�.
	 * �� �� check�� Ȯ���Ͽ� true �� ���� ������ ���� ��� �ش� ������ return �Ѵ�.
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
	 * ��������2 �˰���
	 * 
	 * LinkedList R�� visited boolean Array�� ��� false�� �����Ѵ�.
	 * ��� ������ for�� �ݺ��Ͽ� �ش� index�� visited�� false�Ͻ� dfsTS�޼��带 �����Ͽ� LinkeList R�� �ֽ�ȭ �Ѵ�.
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
	 * LinkedList R�� �ֽ�ȭ �Ͽ� ���������� �����ϴ� �޼���
	 * 
	 * �޼��尡 ����Ǹ� �ش� ���� index�� visited�� true�� �����Ѵ�.
	 * �ش� �������� ���԰����� ���ų� ���԰����� �ΰ��ִ� ������ �湮�� �Ϸ�Ǿ��ٸ� 
	 * �ش� ������ LinkedList�� �� ó������ �����Ѵ�.
	 * ���� ���԰����� ������ �ְ� �� ������ �湮����� ���ٸ� dfsTS�� ��������� �����Ͽ� LinkedList�� �ֽ�ȭ �Ѵ�.
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
		String [] vertices = { "��", "����", "��", "��", "����", "���"};
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
