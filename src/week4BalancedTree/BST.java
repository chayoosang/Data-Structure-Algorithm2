package week4BalancedTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Binary Search Tree Class
 * 
 * @author ������
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
	 * root�� �߰��ϴ� �޼ҵ�
	 * x�� data�� ���ο� root�� ����� 
	 * data�� ũ�⸦ ���� root�� data�� �񱳸� �Ͽ� 
	 * ������ ���ʿ� ũ�� �����ʿ� ��ġ
	 * 
	 * recursive ��� ���
	 * @param x
	 */
	public void insert(char x) {
		insert(x, null, root); // null = parent of root
	}
	
	/**
	 * Node�� �߰��ϴ� �޼ҵ� 
	 *
	 *1. r�� null�̰� parent�� null�� �� root�� null�̱� ������ root�� data x ���� 
	 *
	 *2. r�� null�̰� parent�� ������� leaf�̱� ������ x�� parent�� data�� ���Ͽ� ������ ���� ũ�� �����ʿ� ����.
	 *
	 *3 .r�� ��������� ���� ��� x�� r.data�� ���Ͽ� x�� Ŭ ��� r.right�� �����Ͽ� insert ��� ȣ�� / x�� ���� ��� r.left�� �����Ͽ� insert ��� ȣ��
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
	 * data�� parent�� �޾� ���ο� Node�� ����� �޼���
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
 * ���� Node�� ���Ͽ� data�� ���� x�� ���ö����� �˻��ϴ� �޼���
 * startNode�� p�� �޾� p�� data�� x�� ���ٸ� p�� return
 * p�� data ���� �۴ٸ� p�� ������ startNode��
 * p�� data ���� ũ�ٸ� p�� �������� startNode�� ��� ȣ��
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
	 * x�� ���� ���� Node�����ϴ� �޼���
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
	 * delete�޼����� ����� �޼���
	 * 
	 * 1. r.parent�� ���� ��� root�ۿ� ���°ŹǷ� root�� ������ �θ��� null�� return
	 * 
	 * 2. r�� r.parent�� ���� �ڽ��� ��� r�� �θ��� ���� �ڽ��� ���� �� �θ��� r.parent�� return 
	 * 
	 * 3. r�� r.parent�� ������ �ڽ��� ��� r�� �θ��� ������ �ڽ��� ���� �� �θ��� r.parent�� return 
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
	 *  delete�޼����� ����� �޼���
	 *   
	 * 1. r�� �ڽ��� ���� ���
	 * -r�� �ڽ��� ������ �׳� �����ϹǷ� return null
	 * 
	 * 2. r�� �ڽ��� �ϳ� �ִ� ���
	 * - �ڽ��� ���� ������ ��� ���� r�� �ڽ��� �θ�� r���� r�� �θ�� �ٲ��.
	 * - �� �� r�� �ڽ��� return �Ѵ�.
	 * 
	 * 3. r�� �ڽ��� ���� ���
	 * - sccessor(r)�� ���� �İ��ڸ� ã�� �� Node s�� ����
	 * - r�� s�� key�� ������ �� ���� s�� ���� �Ѵ�. 
	 * - �� �� sccessor�� ���� �İ��ڸ� ���������Ƿ� s�� s�� �θ��� ��� �ڽ��̵��� ���� s�� ������ �ڽ��� �Ѱ��ش�.
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
 * �İ��ڸ� ã�� �޼���(������)
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
	 * �İ��ڸ� ã�� �޼���(����)
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
 * tree�� ����ϴ� �޼���
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
	 * tree�� ����ϴ� �޼���
	 */
	private String toString(Node t) {
		return inorder(t);
	}
/**
*
 * tree�� ����ϴ� �޼���
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
	 * tree�� height�� �˷��ִ� �޼���
	 * @return
	 */
	public int height() {
		return height(root);
	}
	/**
	 * tree�� height�� �˷��ִ� �޼���
	 * height�� tree�� null�� ��� -1��,
	 * �ƴ� ��� root���� �����Ͽ� ������ Ȥ�� ���� �ڽ��� �� ���� height�� ���Ѵ�.
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
	 * IPL�� �ʱ�ȭ �ϴ� �޼���
	 * @return
	 */
	public int IPL() {
		int count = 0;
		return IPLCount(root, count);
	}

	/**
	 * IPL�� �˷��ִ� �޼���
	 * 
	 *  IPL�� root���� ��� �ڽ�(����)�� ������ �� ������ ���Ѵ�.
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
