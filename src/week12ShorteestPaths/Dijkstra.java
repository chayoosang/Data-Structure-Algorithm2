//package week12ShorteestPaths;
//
//import java.util.Arrays;
//
//import week9Graph.GraphInMatrix;
//
///**
// * Dijkstra �˰��� ����
// *
// * @author ������
// *
// */
//public class Dijkstra extends GraphInMatrix{
//	/**
//	 * ��� �湮�ߴ��� ����ϱ� ���� prev
//	 * �������� ����ġ�� ��� ����ϱ� ���� prevWeight
//	 * prevWeight���� �ּ��� ����ġ�� ����ϱ� ���� currentWeight
//	 * �ش� ������ �湮�Ǿ����� �˱����� visit
//	 * �湮 Ƚ���� ����ϱ� ���� count
//	 */
//	String[] prev;
//	int[] prevWeight;
//	int[] currentWeight;
//	boolean[] visit;
//	int count = 0;
//	boolean firstStart = true;
//
//	/**
//	 * graphInList�� ���� Class�� �޾� super�� ���� Graph ����
//	 * @param size
//	 */
//	public Dijkstra(int size) {
//		super(size);
//	}
//	/**
//	 * Dijkstra�� �����ϱ� ���� �ʱ�ȭ
//	 * @param size
//	 */
//	public void init(int size) {
//		prev = new String[size];
//		visit = new boolean[size];
//		Arrays.fill(visit, false);
//		prevWeight = new int[size];
//		Arrays.fill(prevWeight, 9999);
//		prevWeight[0] = 0;
//		currentWeight = new int[size];
//		Arrays.fill(currentWeight, 9999);
//		currentWeight[0] = 0;
//	}
//
//	/**
//	 * Dijkstra �˰��� ����
//	 *
//	 * start�� �޾� �ش� ������������ ����
//	 *
//	 * count�� ������ ���� �������� ��� �湮�Ǿ����Ƿ� break
//	 * extractMin�� ���� start�� �ΰ� �ִ� ����ġ�� ���� ������ index�� �ް�
//	 * �ش� index�� �湮�ߴٴ� ǥ�÷� visit[index]�� true�� ���
//	 * �� �� dijkstra�� index�� �ٽ� �޾� ����� ǥ��
//	 * @param start
//	 */
//	public void dijkstra(int start) {
//		int index;
//		count++;
//
//		while(true) {
//			if(count == vertices.size()) break;
//			index = extractMin(start);
//			visit[index] = true;
//			dijkstra(index);
//		}
//	}
//
//	/**
//	 * �ּ��� ����ġ�� ���ϱ� ���� �޼���
//	 *
//	 *
//	 * ���� extractMin�� ó������ ����Ǿ��ٸ�(firstStart�� ���� Ȯ�� ����) ������ ���� ���Ⱓ���� ����ġ�� prevWeight�� ��� �� prev[start�� ���԰������� ���� ����]�� start�� ���
//	 *
//	 * ����  extractMin�� ó������ ������� �ʾҴٸ�(firstStart�� ���� Ȯ�� ����) ������ ���� ���԰����� ����ġ�� �ڽ��� ���� ���Ⱓ���� ����ġ�� ���Ͽ�
//	 * prevWeight���� ������� prevWeight�� ����ϰ� prev[start�� ���԰������� ���� ����]�� start�� ����Ѵ�.
//	 *
//	 *�� �� prevWeight���� �ּڰ��� currentWeight�� ��� �� �ش� index�� return
//	 * @param start
//	 * @return
//	 */
//	private int extractMin(int start) {
//		int min=100;
//		int index=0;
//
//		if(firstStart == true) {
//			for(int j=0; j<adjacentMatrix[0].length; j++) {
//					if(adjacentMatrix[0][j]>0) {
//						prevWeight[j] = adjacentMatrix[0][j];
//						prev[j] = vertices.get(start);
//						}
//				}
//			firstStart = false;
//			}
//		else{
//			for(int i=0; i<adjacentMatrix[start].length; i++) {
//					if(adjacentMatrix[start][i]>0&&prevWeight[i]>currentWeight[start]+adjacentMatrix[start][i]&&visit[i]!=true) {
//						prevWeight[i] = currentWeight[start]+adjacentMatrix[start][i];
//						prev[i] = vertices.get(start);
//						}
//					}
//			}
//		for(int j=0; j<prevWeight.length; j++) {
//			if(min>prevWeight[j]&&prevWeight[j]>0) {
//				min = prevWeight[j];
//				index = j;
//			}
//		}
//		currentWeight[index] = prevWeight[index];
//		prevWeight[index] = 0;
//		return index;
//	}
//
//
//	public void showDijkstra() {
//		for(int i=0; i<vertices.size(); i++) {
//			if(i==0)
//				System.out.println("Start => "+vertices.get(i)+" / ����ġ : "+currentWeight[i]);
//			else
//			System.out.println(prev[i]+" => "+vertices.get(i)+" / ����ġ : "+currentWeight[i]);
//		}
//	}
//
//
//	public static void main(String[] args) {
//		int maxNoVertex = 10;
//		String [] vertices = { "����", "��õ", "����", "�뱸", "����", "�λ�", "���"};
//		int [][] graphEdges = { {0,1,11}, {0,2,8}, {0,3,9}, {1,3,13}, {1,6,8}, {2,4,10}, {3,2,-3}, {3,4,5}, {3,5,12}, {5,6,7}};
//
//		Dijkstra dijkstra = new Dijkstra(maxNoVertex);
//
//
//		dijkstra.createGraph("Dijkstra");
//
//		for(int i=0; i<vertices.length; i++)
//			dijkstra.insertVertex(vertices[i]);
//		for (int i = 0; i<graphEdges.length; i++)
//			dijkstra.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
//		dijkstra.showGraph();
//
//		dijkstra.init(vertices.length);
//		dijkstra.dijkstra(0);
//		dijkstra.showDijkstra();
//	}
//
//}
