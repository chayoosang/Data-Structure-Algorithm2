package week12ShorteestPaths;

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
public class BellmanFord extends GraphInList{
	/**
	 * ��� �湮�ߴ��� ����ϱ� ���� prev
	 * �������� ����ġ�� ��� ����ϱ� ���� prevWeight
	 * prevWeight���� �ּ��� ����ġ�� ����ϱ� ���� currentWeight
	 * �ش� ������ �湮�Ǿ����� �˱����� visit
	 * �湮 Ƚ���� ����ϱ� ���� count
	 */
	String[] prev;
	int[] prevWeight;
	int[] currentWeight;
	boolean[] visit;
	int count = 0;
	boolean firstStart = true;
	
	/**
	 * graphInList�� ���� Class�� �޾� super�� ���� Graph ����
	 * @param size
	 */
	public BellmanFord(int size) {
		super(size);
	}
	/**
	 * Dijkstra�� �����ϱ� ���� �ʱ�ȭ
	 * @param size
	 */
	public void init(int size) {
		prev = new String[size];
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
	public void dijkstra(int start) {
		int index;
		count++;
		
		while(true) {
			if(count == vertices.size()) break;
			index = extractMin(start);
			visit[index] = true;
			dijkstra(index);
		}
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
	private int extractMin(int start) {
		int min=100;
		int index=0;
		
		if(firstStart == true) {
			for(int j=0; j<adjacentList.get(0).size(); j++) {
				Element e = adjacentList.get(0).get(j);
				for(int q=1; q<vertices.size(); q++) {
					if(e.getSource().equals(vertices.get(q))) {
						prevWeight[q] = e.getWeight();
						prev[q] = vertices.get(start);
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
						prevWeight[q] = e.getWeight()+currentWeight[start];
						prev[q] = vertices.get(start);
						break;
						}
					}
				}
			}

		for(int j=0; j<prevWeight.length; j++) {
			if(min>prevWeight[j]&&prevWeight[j]>0) {
				min = prevWeight[j];
				index = j;
			}
		}
		currentWeight[index] = prevWeight[index];
		prevWeight[index] = 0;
		return index;
	}
	
	
	public void showDijkstra() {
		for(int i=0; i<vertices.size(); i++) {
			if(i==0)
				System.out.println("Start => "+vertices.get(i)+" / ����ġ : "+currentWeight[i]);
			else
			System.out.println(prev[i]+" => "+vertices.get(i)+" / ����ġ : "+currentWeight[i]);
		}
	}
	
	
	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] vertices = { "����", "��õ", "����", "�뱸", "����", "�λ�", "���"};
		int [][] graphEdges = { {0,1,11}, {0,2,8}, {0,3,9}, {1,3,13}, {1,6,8}, {2,4,10}, {3,2,-3}, {3,4,5}, {3,5,12}, {5,6,7}};
		
		BellmanFord dijkstra = new BellmanFord(maxNoVertex);
		
		 
		dijkstra.createGraph("BellmanFord");		
		for (int i = 0; i<graphEdges.length; i++)
			dijkstra.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		dijkstra.showGraph();
		
		dijkstra.init(vertices.length);
		dijkstra.dijkstra(0);
		dijkstra.showDijkstra();
	}

}
