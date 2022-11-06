package week13AStar;

import java.util.ArrayList;
import java.util.Arrays;

import week9Graph.GraphInList;

/**
 * Dijkstra �˰��� ����
 * 
 * GraphInList���� adjacentList�� String�� �ƴ� class Element�� ����
 * class Element{String source, int weight}�� ����
 * @author ������
 *
 */
public class DijkstraAStar extends GraphInList{
	/**
	 * ��� �湮�ߴ��� ����ϱ� ���� prev
	 * �������� ����ġ�� ��� ����ϱ� ���� prevWeight
	 * prevWeight���� �ּ��� ����ġ�� ����ϱ� ���� currentWeight
	 * �ش� ������ �湮�Ǿ����� �˱����� visit
	 * �湮 Ƚ���� ����ϱ� ���� count
	 */
	ArrayList<String> prev;
	int[] prevWeight;
	int[] currentWeight;
	int[] heuristic;
	boolean[] visit;
	int count = 0;
	boolean firstStart = true;
	
	/**
	 * graphInList�� ���� Class�� �޾� super�� ���� Graph ����
	 * @param size
	 * @param heuristic 
	 */
	public DijkstraAStar(int size, int[] heuristic) {
		super(size);
		this.heuristic = heuristic;
	}
	/**
	 * Dijkstra�� �����ϱ� ���� �ʱ�ȭ
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
	 * Dijkstra �˰��� ����
	 * 
	 * start�� �޾� �ش� ������������ ����
	 * 
	 * count�� ������ ���� �������� ��� �湮�Ǿ����Ƿ� break
	 * extractMin�� ���� start�� �ΰ� �ִ� ����ġ�� ���� ������ index�� �ް�
	 * �ش� index�� �湮�ߴٴ� ǥ�÷� visit[index]�� true�� ���
	 * �� �� dijkstra�� index�� �ٽ� �޾� ����� ǥ��
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
	 * �ּ��� ����ġ�� ���ϱ� ���� �޼���
	 * 
	 *
	 * ���� extractMin�� ó������ ����Ǿ��ٸ�(firstStart�� ���� Ȯ�� ����) ������ ���� ���Ⱓ���� ����ġ�� prevWeight�� ��� �� prev[start�� ���԰������� ���� ����]�� start�� ��� 
	 * 
	 * ����  extractMin�� ó������ ������� �ʾҴٸ�(firstStart�� ���� Ȯ�� ����) ������ ���� ���԰����� ����ġ�� �ڽ��� ���� ���Ⱓ���� ����ġ�� ���Ͽ� 
	 * prevWeight���� ������� prevWeight�� ����ϰ� prev[start�� ���԰������� ���� ����]�� start�� ����Ѵ�.
	 *
	 *�� �� prevWeight���� �ּڰ��� currentWeight�� ��� �� �ش� index�� return
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
