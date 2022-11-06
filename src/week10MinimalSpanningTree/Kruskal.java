package week10MinimalSpanningTree;

import java.util.Arrays;
import java.util.Comparator;

import week9Graph.GraphInMatrix;
/**
 * Kruskal �˰��� ����
 * @author ������
 *
 */
public class Kruskal {
	/**
	 * edge => ����� ����ġ ǥ��
	 * check => kruskal �˰����� ���� ����Ǿ����� ǥ��
	 * parents => ����Ŭ ���̺� / root�� ǥ���Ͽ� cycle�� �����ϴ��� ǥ��
	 * result => ����� ����ġ�� �� / count => ����� ���� ��
	 */
	int[][] edge;
	boolean [] check;
	int[] parents;
	int result;
	int count;
	
	/**
	 * Kruskal �ʱ� ����
	 * parents�� vertices�� ���� ǥ��
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
	 * parents -1�� �ʱ�ȭ
	 */
	private void init() {
		for(int i=0; i<parents.length; i++) {
			parents[i] = -1;
		}
	}
	/**
	 * edge�� ����ġ�� ������������ �����ϴ� �޼���
	 * 
	 * Arrays.sort�� ���� ����
	 */
	private void sort() {
		Arrays.sort(edge,(o1, o2) ->{
			return Integer.compare(o1[2], o2[2]);
		});
	
	}
	/**
	 * Kruskal �˰��� �����ϴ� �޼���
	 * 
	 * edge�� �� ��ŭ kruskal ����
	 * union �޼��带 ���� true�̸� �ش� edge ����
	 * count�� parents�� ���� ���� ��� ��� ����Ȱ��̹Ƿ� break
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
	 * cycle�� �����ϴ��� Ȯ���ϴ� �޼���
	 * 
	 * find�� ���� root�� ã�� root�� ���� ��� cycle�� �����ϹǷ� false
	 * root�� �ٸ� ��� cycle�� �������� �����Ƿ� ū root�� parent�� ���� root�� ���� �� true return
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
	 * parents�� ã���ִ� �޼���
	 * 
	 * parents�� �ʱ⿡ -1�� �ʱ�ȭ �Ͽ����Ƿ� 0���� ������ �ڱ� �ڽ��� return
	 * 0���� Ŭ �� �ش� parents�� ����Ǿ��ִ� ���� parents�� ã�� return
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
	 * Kruskal�� ������ �����ִ� �޼���
	 */
	private void showKruskal() {
		for(int i=0; i<edge.length; i++) {
			for(int j=0; j<3; j++) {
				if(j==0)
					System.out.print(edge[i][j]+" ����� node : ");
				else if(j==1)
					System.out.print(edge[i][j]+" ����ġ : ");
				else
					System.out.println(edge[i][j]+" ����� ���� : "+check[edge[i][0]]);
			}
		}
		System.out.println("��� �� : "+ result);
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
