package week4BalancedTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Binary Search Tree Class
 * 
 * @author 차유상
 *
 */
public class BST {
	public class Node{
		char key;
		Node parent;
		Node left;
		Node right;
		
		public Node(char c) {
			key = c;
			parent = null;
			left = null;
			right = null;
		}
		
		public String toString() {
			String retVal = "";
			return retVal+key + "(" + height(this) + ")";
		}
	}
	

	Node root;
	int numNode;
	
	public BST() {
		root = null;
		numNode = 0;
	}
	
	/**
	 * root를 추가하는 메소드
	 * x의 data로 새로운 root를 만들어 
	 * data의 크기를 기존 root의 data와 비교를 하여 
	 * 작으면 왼쪽에 크면 오른쪽에 배치
	 * 
	 * recursive 방식 사용
	 * @param x
	 */
	public void insert(char x) {
		insert(x, null, root); // null = parent of root
	}
	
	/**
	 * Node를 추가하는 메소드 
	 *
	 *1. r이 null이고 parent가 null일 시 root가 null이기 때문에 root에 data x 삽입 
	 *
	 *2. r이 null이고 parent가 있을경우 leaf이기 때문에 x와 parent의 data를 비교하여 작으면 왼쪽 크면 오른쪽에 삽입.
	 *
	 *3 .r이 비어져있지 않을 경우 x와 r.data를 비교하여 x가 클 경우 r.right로 시작하여 insert 재귀 호출 / x가 작을 경우 r.left로 시작하여 insert 재귀 호출
	 *
	 * @param p
	 * @param parent
	 * @param x
	 */
	protected Node insert(char x, Node parent, Node r) {
		if(r==null) {
			if(parent ==null) { //root
				root = insertNode(x, null);
				return root;
			}
			else {  // leaf
				if(x < parent.key) {
					parent.left = insertNode(x, parent);
					return parent.left;
				}
				else if(x > parent.key) {
					parent.right = insertNode(x, parent);
					return parent.right;
				}
				else // never happens 
					return null;
			}
		}
		else {
			if(x < r.key) 
				return insert(x, r, r.left);
			else if(x > r.key)
				return insert(x, r, r.right);
			else
				return null;
		}
			
	}
	/**
	 * data와 parent를 받아 새로운 Node를 만드는 메서드
	 * 
	 * @param x
	 * @param parent
	 * @return
	 */
	private Node insertNode(char x, Node parent) {
		Node newNode =  new Node(x);
		newNode.parent = parent;
		numNode++;
		return newNode;
	}

/**
 * 시작 Node를 정하여 data의 값이 x가 나올때까지 검색하는 메서드
 * startNode를 p로 받아 p의 data가 x와 같다면 p를 return
 * p의 data 보다 작다면 p의 왼쪽을 startNode로
 * p의 data 보다 크다면 p의 오른쪽을 startNode로 재귀 호출
 * @param startNode
 * @param x
 * @return
 */
	public Node search(Node startNode, char x) {
		Node p = startNode;
		if(p==null||p.key==x)
			return p;
		else if(x<p.key)
			return search(p.left,x);
		else
			return search(p.right, x);
	}
	/**
	 * x의 값을 가진 Node삭제하는 메서드
	 * @param x
	 */
	public Node delete(char x) {
		Node r =search(root, x);
		if (r != null) {
			numNode--;
			return delete(r);
		}
		else 
			return null;
	}
	/**
	 * delete메서드의 재귀적 메서드
	 * 
	 * 1. r.parent가 없을 경우 root밖에 없는거므로 root를 삭제후 부모인 null을 return
	 * 
	 * 2. r이 r.parent의 왼쪽 자식일 경우 r의 부모의 왼쪽 자식을 삭제 후 부모인 r.parent을 return 
	 * 
	 * 3. r이 r.parent의 오른쪽 자식일 경우 r의 부모의 오른쪽 자식을 삭제 후 부모인 r.parent을 return 
	 * 
	 * @param startNode
	 * @param x
	 */
	protected Node delete(Node r) {
		if(r.parent == null) { // r = root
			root = deleteNode(r);
			return null;
		}
		else if(r == r.parent.left) {
			r.parent.left = deleteNode(r);
			return r.parent;
		}
		else {
			r.parent.right = deleteNode(r);
			return r.parent;
		}
	}

		
		
		
	/**
	 *  delete메서드의 재귀적 메서드
	 *   
	 * 1. r의 자식이 없는 경우
	 * -r의 자식이 없으면 그냥 삭제하므로 return null
	 * 
	 * 2. r의 자식이 하나 있는 경우
	 * - 자식의 왼쪽 오른쪽 상관 없이 r의 자식의 부모는 r에서 r의 부모로 바뀐다.
	 * - 그 후 r의 자식을 return 한다.
	 * 
	 * 3. r의 자식이 둘인 경우
	 * - sccessor(r)를 통해 후계자를 찾은 후 Node s에 저장
	 * - r에 s의 key를 저장한 후 기존 s를 삭제 한다. 
	 * - 그 전 sccessor를 통해 후계자를 선택했으므로 s가 s의 부모의 어느 자식이든지 간에 s의 오른쪽 자식을 넘겨준다.
	 */
		protected Node deleteNode(Node r) {
			if(r.left==null&&r.right==null) {
				return null;
			}
			else if(r.left==null&&r.right!=null) {
				r.right.parent = r.parent;
				return r.right;
			}
			else if(r.left!=null&&r.right==null) {
				r.left.parent = r.parent;
				return r.left;
			}
			else {
			Node s = sccessor(r);  // or predecessor(r)
			r.key = s.key;
			 if(s==s.parent.left) 
				 s.parent.left = s.right;
			 else
				 s.parent.right = s.right;
			 return r;
			}
	}


/**
 * 후계자를 찾는 메서드(오른쪽)
 * @param v
 * @return
 */
	private Node sccessor(Node v) {
		if(v== null)
			return null;
		Node p = v.right;
		while(p.left!=null)
			p=p.left;
		return p;
	}
	/**
	 * 후계자를 찾는 메서드(왼쪽)
	 * @param v
	 * @return
	 */
	private Node predecessor(Node v) {
		if(v== null)
			return null;
		Node p = v.left;
		while(p.right!=null)
			p=p.right;
		return p;
	}
/**
 * tree를 출력하는 메서드
 */
	public void showTree() {
		if (root ==  null)
			return;
		Deque<Node> que = new ArrayDeque<Node>();
		que.add(root);
		int depthLevel = 0;
		while(que.peek()!=null) {
			Deque<Node> temp = new ArrayDeque<Node>();
			System.out.print("\nDepth-level " + depthLevel + "  :  ");
			while(que.peek()!=null) {
				temp.add(que.poll());
			}
			while(temp.peek()!=null) {
				Node e = temp.poll();
				System.out.print(e.toString()+"  ");
				if(e.left!=null)
					que.add(e.left);
				if(e.right!=null)
					que.add(e.right);
			}
			depthLevel++;
		}
		
	}
	/**
	 * tree를 출력하는 메서드
	 */
	private String toString(Node t) {
		return inorder(t);
	}
/**
*
 * tree를 출력하는 메서드
*
 * @param t
 * @return
 */
	private String inorder(Node t) {
		if(t== null) {
			return "";
		}
		else
			return inorder(t.left)+" "+t.toString() +" "+inorder(t.right);
	}
	
//////////////////////////////////////////////////////////////////////////////
	/**
	 * tree의 height를 알려주는 메서드
	 * @return
	 */
	public int height() {
		return height(root);
	}
	/**
	 * tree의 height를 알려주는 메서드
	 * height는 tree가 null일 경우 -1을,
	 * 아닐 경우 root부터 시작하여 오른쪽 혹은 왼쪽 자식이 더 높은 height를 더한다.
	 * @param r
	 * @return
	 */
	protected int height(Node r) {
		if(r==null)
			return -1;
		else
			return 1+ Math.max(height(r.left), height(r.right));
	}
	/**
	 * IPL을 초기화 하는 메서드
	 * @return
	 */
	public int IPL() {
		int count = 0;
		return IPLCount(root, count);
	}

	/**
	 * IPL을 알려주는 메서드
	 * 
	 *  IPL은 root부터 모든 자식(손자)의 순서를 한 묶음씩 더한다.
	 *  
	 * @param r
	 * @param count
	 * @return
	 */
	private int IPLCount(Node r, int count) {
		if(r==null)
			return count;
		count++;
		int lCount = IPLCount(r.left, count);
		int rCount = IPLCount(r.right, count);
		return count + lCount + rCount;
	}

	//////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		
		char [] data = {'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W'};
		BST bt = new BST();
		
		for(int i=0; i<data.length; i++) {
			bt.insert(data[i]);
			bt.showTree();
		}
		System.out.print("\nTree Created :");
		bt.showTree();
		
		bt.delete('S');
		System.out.println("\nAfter deleting 'S' : ");
		bt.showTree();
		bt.delete('G');
		System.out.println("\nAfter deleting 'G' : ");
		bt.showTree();
		bt.delete('U');
		System.out.println("\nAfter deleting 'U' : ");
		bt.showTree();
		
	}

}
