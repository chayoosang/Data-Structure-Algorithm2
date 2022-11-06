//package week12ShorteestPaths;
//
//import java.util.Arrays;
//
//import week9Graph.GraphInMatrix;
//
///**
// * FloydWarshall 알고리즘 구현
// * @author 차유상
// *
// */
//public class FloydWarshall extends GraphInMatrix{
//
//	private int[][] weight; //가중치를 저장할 Array
//	private int size; // 정점의 개수를 저장할 size
//
//	/**
//	 * GraphInMatrix를 상속받으므로 super를 통해 GraphInMatrix 만듬
//	 * @param maxNum
//	 */
//	public FloydWarshall(int maxNum) {
//		super(maxNum);
//	}
//
//	/**
//	 * FloydWarshall 알고리즘 구현 전 초기화
//	 * weight의 값을 Graph의 adjacentMatrix로 받음.
//	 * 이때 정점이 자기 자신을 가르칠 경우 0을,
//	 * 값이 있을 경우 weight에 adjacentMatrix 값을,
//	 * 값이 없을 경우 999(무한)을 넣는다.
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
//	 * FloydWarshall 알고리즘 구현 메서드
//	 *
//	 * 중간 값을 i로, 시작 값을 j로, 끝 값을 k로 받아 for문을 통해 반복한다.
//	 * 만약 시작 정점(j)에서 끝 정점(k)로 한번에 가는 가중치 보다
//	 * 시작 정점(j)에서 중간 정점(i)를 통해 끝 정점(k)로 가는 것이 더 작을 경우
//	 * weight[시작][끝]에 weight[시작][중간] + weight[중간][끝] 값을 집어 넣는다.
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
