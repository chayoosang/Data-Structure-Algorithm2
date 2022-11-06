//package week12ShorteestPaths;
//
//import java.util.Arrays;
//
//import week9Graph.GraphInMatrix;
//
///**
// * FloydWarshall �˰��� ����
// * @author ������
// *
// */
//public class FloydWarshall extends GraphInMatrix{
//
//	private int[][] weight; //����ġ�� ������ Array
//	private int size; // ������ ������ ������ size
//
//	/**
//	 * GraphInMatrix�� ��ӹ����Ƿ� super�� ���� GraphInMatrix ����
//	 * @param maxNum
//	 */
//	public FloydWarshall(int maxNum) {
//		super(maxNum);
//	}
//
//	/**
//	 * FloydWarshall �˰��� ���� �� �ʱ�ȭ
//	 * weight�� ���� Graph�� adjacentMatrix�� ����.
//	 * �̶� ������ �ڱ� �ڽ��� ����ĥ ��� 0��,
//	 * ���� ���� ��� weight�� adjacentMatrix ����,
//	 * ���� ���� ��� 999(����)�� �ִ´�.
//	 * @param size
//	 */
//	public void init(int size) {
//		this.weight = new int[size][size];
//		this.size = size;
//
//		for(int i=0; i<size; i++) {
//			for(int j=0; j<size; j++) {
//				if(i==j)
//					this.weight[i][j] = 0;
//				else if(getAdjacentMatrix(i,j)>0)
//					this.weight[i][j] = getAdjacentMatrix(i,j);
//				else
//					this.weight[i][j] = 999;
//			}
//		}
//
//	}
//
//	/**
//	 * FloydWarshall �˰��� ���� �޼���
//	 *
//	 * �߰� ���� i��, ���� ���� j��, �� ���� k�� �޾� for���� ���� �ݺ��Ѵ�.
//	 * ���� ���� ����(j)���� �� ����(k)�� �ѹ��� ���� ����ġ ����
//	 * ���� ����(j)���� �߰� ����(i)�� ���� �� ����(k)�� ���� ���� �� ���� ���
//	 * weight[����][��]�� weight[����][�߰�] + weight[�߰�][��] ���� ���� �ִ´�.
//	 */
//	public void floydWarshall() {
//		for(int i=0; i<this.size; i++) {
//			for (int j=0; j<this.size; j++) {
//				for (int k=0; k<this.size; k++) {
//					if (weight[j][k]>weight[j][i]+weight[i][k])
//						weight[j][k] = weight[j][i]+weight[i][k];
//				}
//			}
//		}
//	}
//
//	public void print() {
//		System.out.println();
//		System.out.println("<FloaydWarshall>");
//		for(int i=0; i<this.size; i++) {
//			System.out.print(i+"  ");
//			for(int j=0; j<this.size; j++)
//				System.out.print(this.weight[i][j]+" ");
//			System.out.println();
//		}
//	}
//
//
//	public static void main(String[] args) {
//		int maxNoVertex = 10;
//		String [] vertices = { "0", "1", "2", "3", "4", "5"};
//		int [][] graphEdges = { {0,1,5}, {0,2,4}, {1,3,2}, {1,4,6}, {2,5,4}, {3,4,7}, {4,2,1}, {4,5,9}};
//
//		FloydWarshall myFloydWarshall = new FloydWarshall(maxNoVertex);
//
//
//		myFloydWarshall.createGraph("FloydWarshall");
//		for(int i=0; i<vertices.length; i++)
//			myFloydWarshall.insertVertex(vertices[i]);
//		for (int i = 0; i<graphEdges.length; i++)
//			myFloydWarshall.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
//		myFloydWarshall.showGraph();
//
//
//		myFloydWarshall.init(vertices.length);
//		myFloydWarshall.floydWarshall();
//		myFloydWarshall.print();
//
//
//	}
//
//}
