//package week9Graph;
//
//public class GraphBaseMain {
//
//	public static void main(String[] args) {
//		int maxNoVertex = 10;
//		String [] vertices = { "서울", "울산", "대구", "부산", "인천", "제주", "대전", "광주"};
//		int [][] graphEdges = { {1,4}, {1,6}, {2,1}, {2,3}, {4,6}, {4,7}, {5,2}, {5,3}, {5,4}, {5,6}, {7,5}, {7,8}};
//		GraphInMatrix myGM = new GraphInMatrix(maxNoVertex);
//
//		myGM.createGraph("TestGraph in Matrix");
//		myGM.showGraph();
//
//		for (int i = 0; i<graphEdges.length; i++)
//			myGM.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1],1);
//		myGM.showGraph();
//
//
//		System.out.println("\nAdjacent Set of "+"서울");
//		System.out.println(myGM.adjacent("서울"));
//
//		myGM.BFS(vertices[0]);
//		myGM.DFS(vertices[0]);
//
//	///////////////////////////////////////////////////////////////////
////		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////
////		GraphInList myGL = new GraphInList(maxNoVertex);
////
////		myGL.createGraph("TestGraph in List");
////		myGL.showGraph();
////
////		for (int i = 0; i<graphEdges.length; i++)
////			myGL.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
////		myGL.showGraph();
////
////
////
////		System.out.println("\nAdjacent Set of "+"서울");
////		System.out.println(myGL.adjacent("서울"));
////
////		myGL.BFS(vertices[0]);
////		myGL.DFS(vertices[0]);
//
//		//////////////////////////////////////////////////////////
//		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//		GraphInArray myGA = new GraphInArray(maxNoVertex);
//
//
//		myGA.createGraph("TestGraph in Array");
//		myGA.showGraph();
//
//		for (int i = 0; i<graphEdges.length; i++)
//			myGA.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
//		myGA.showGraph();
//
//
//
//		System.out.println("\nAdjacent Set of "+"서울");
//		System.out.println(myGL.adjacent("서울"));
//
//		myGA.BFS(vertices[0]);
//		myGA.DFS(vertices[0]);
//	}
//
//}
//
