package week15FinalExam;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;

public class Maze {
	
///////////////////////////////////////////////////////////////////
/// 0) 학번, 이름을 기입하시오.
	String numId ="60171938";   // 학번
	String name ="차유상";    // 이름	
///////////////////////////////////////////////////////////////////

	public class Coordinate{
		int x, y;
		public Coordinate(int i, int j) {
			x=i; y=j;
		}
		public boolean equals(Coordinate other) {
			return (this.x==other.x)&&(this.y==other.y); 
		}
		public void copyFrom(Coordinate other) {
			this.x = other.x;
			this.y = other.y;
		}
		public String toString() {
			return "("+x+","+y+")";
		}		
	}
	int size ;
	Coordinate start, destin;
	
	public Maze(int n) {
		size = n;
		start = new Coordinate(0,0);
		destin = new Coordinate(size-1,size-1);
		
		System.out.println("<< "+numId+" : "+name+" >>");
	}
	
	public int DFS(int [][] in) {
		int [][] m = deepCopy(in);
		show("Initial State", m);

		Coordinate p = new Coordinate(start.x,start.y);
		int seq = 1;
		int n = DFS(m, p, seq); // sequence-value starts from 1, ie.  [0,0]=1
		show("DFS result", m);
		return n;
	}
	
	public int DFS(int [][] m, Coordinate p, int seq) {
///////////////////////////////////////////////////////////////////
/// 1) 여기에 DFS 코드를 완성하시오.
		m[p.x][p.y] = seq;
		Iterator<Coordinate> ad = adjacent(p, m).iterator();
		while(ad.hasNext()) {
			Coordinate a = (Coordinate) ad.next();
			if(m[a.x][a.y]>0) {
					continue;
			}
			seq++;
			seq = DFS(m, a, seq);
		}

///////////////////////////////////////////////////////////////////
		return seq;
	}
	
	
	public void BFS(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);
		
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		int seq = 1;

		while(Q.peek()!=null) {
///////////////////////////////////////////////////////////////////
/// 2) 여기에 BFS 코드를 완성하시오.
			boolean check = false;
			Coordinate a = Q.poll();

			m[a.x][a.y] = seq;
			seq++;
			Iterator<Coordinate> ad = adjacent(a, m).iterator();
			while(ad.hasNext()) {
				Coordinate b = (Coordinate) ad.next();
				if(m[b.x][b.y]==0) {
					Iterator<Coordinate> q = Q.iterator();
					while(q.hasNext()) {
						Coordinate c = (Coordinate) q.next();
						if(c.equals(b))
							check=true;
					}
					if(check==false)
						Q.add(b);
					
					check=false;
			}
			}			
			
///////////////////////////////////////////////////////////////////
		}
		show("BFS result", m);
	}

	public void Dijkstra(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);
		int seq = 1;

///////////////////////////////////////////////////////////////////
/// 3) 여기에 Dijkstra 코드를 완성하시오.
		m[p.x][p.y] = seq;
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		while(Q.peek()!=null) {
			Coordinate a = Q.poll();

			Iterator<Coordinate> ad = adjacent(a, m).iterator();
			while(ad.hasNext()) {
				Coordinate b = (Coordinate) ad.next();
				if(m[b.x][b.y]==0) {
					m[b.x][b.y]=m[a.x][a.y] + seq;
					Q.add(b);
				}
				else if(m[b.x][b.y]>m[a.x][a.y] + seq) {
					m[b.x][b.y]=m[a.x][a.y] + seq;
					Q.add(b);
				}
			}
			
		}
		





///////////////////////////////////////////////////////////////////
		
		show("Dijkstra result", m);
	}

	public void AStar(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);
		int seq = 1;
///////////////////////////////////////////////////////////////////
/// 4) 여기에 A* 코드를 완성하시오.
		
		m[p.x][p.y] = seq;
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		while(Q.peek()!=null) {
			Coordinate a = Q.poll();

			Iterator<Coordinate> ad = adjacent(a, m).iterator();
			while(ad.hasNext()) {
				Coordinate b = (Coordinate) ad.next();
				if(m[b.x][b.y]==0) {
					m[b.x][b.y]=m[a.x][a.y] + seq;
					Q.add(b);
				}
				else if(m[b.x][b.y]>m[a.x][a.y] + seq+calcHVal(b)) {
					m[b.x][b.y]=m[a.x][a.y] + seq;
					Q.add(b);
				}
			}


		}
///////////////////////////////////////////////////////////////////

		show("Dijkstra + A* result", m);
	}


	private HashSet<Coordinate> adjacent(Coordinate u, int [][] maze) {  // can filter 1-boundary condition, 2-not the wall(-1)
		HashSet<Coordinate> retSet = new HashSet<>();
		if (u.x-1>=0 && maze[u.x-1][u.y]!=-1) retSet.add(new Coordinate(u.x-1, u.y));
		if (u.x+1<size && maze[u.x+1][u.y]!=-1) retSet.add(new Coordinate(u.x+1, u.y));
		if (u.y-1>=0 && maze[u.x][u.y-1]!=-1) retSet.add(new Coordinate(u.x, u.y-1));
		if (u.y+1<size && maze[u.x][u.y+1]!=-1) retSet.add(new Coordinate(u.x, u.y+1));		
		return retSet;
	}
	
	private int calcHVal(Coordinate c) {
		double temp = Math.sqrt((destin.x-c.x)*(destin.x-c.x)+(destin.y-c.y)*(destin.y-c.y));
		return (int)temp;
	}
	
	private void show(String s, int [][] m) {
		System.out.println("\n [ "+s+" ]");
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.printf("%3d",m[i][j]);
			}
			System.out.println();
		}
	}

	private int[][] deepCopy(int[][] m) {
		int [][] ret = new int[size][size];
		for (int i=0;i<size;i++)
			for (int j=0;j<size;j++)
				ret[i][j]=m[i][j];
		return ret;
	}

	public static void main(String[] args) {
		int [][] input = { {0,-1,0,0,0,0,0,-1,0,-1},
		           		   {0,-1,0,-1,-1,-1,0,-1,0,0},
		                   {0,0,0,0,0,0,0,0,0,-1},
		                   {-1,0,-1,0,-1,0,-1,-1,0,0},
		                   {-1,0,-1,0,-1,0,0,-1,-1,0},
		                   {-1,0,0,0,0,0,0,0,0,0},
		                   {0,0,-1,0,0,-1,-1,0,-1,-1},
		                   {0,-1,-1,0,0,0,0,0,0,-1},
		                   {0,-1,0,0,-1,0,0,-1,0,0},
		                   {0,0,-1,0,0,0,-1,-1,0,0} };
		
		int size  = input.length;
	
		Maze  me = new Maze(size);
		me.DFS(input);
		me.BFS(input);
		me.Dijkstra(input);
		me.AStar(input);

	}

}
