package week3Select;
/**
 * ���� �˰���
 * @author ������
 *
 */
public class SelectTest {
	/**
	 * �ݺ� Ƚ�� 
	 */
	int recursiveCount = 0;
	/**
	 * �ݺ� Ƚ�� reset �ϴ� �޼���
	 */
	public void resetCount() {
		recursiveCount=0;
	}
	/**
	 * �ݺ� Ƚ�� return ���ִ� �޼���
	 * @return recursiveCount
	 */
	public int getCount() {
		return recursiveCount;
	}
	/**
	 * ��� �����ð� ���� �˰���
	 * 
	 * 1. ù��° index�� p �� ������ index�� r���� ũ�� data �迭�� �����̹Ƿ� -1�� return 
	 * 2. p�� r�� ���� ��� �迭�� ũ�Ⱑ 1�̹Ƿ� data[p]�� return 
	 * 3. �� ���ǵ��� �ƴ� ��� Quick Sort����� Ȱ���ϴ� partition �޼��带 ���� q�� �����Ͽ� ������ ���� �� ��
	 * q�� �������� �ݹ��� ���� ������ q���� ��������, �������� q���� ū �������� q-p�� k�� i�� �񱳸� �Ͽ� ã�´�.
	 * i<k �� ��� q�� �������� ���� ���ܿ��� ������ ������ i�� ã�´�.
	 * i==k �� ��� ã�� ���� data�� index�� k�� ���̴�.
	 * i>k �� ��� q�� �������� ������ ���ܿ��� ������ ������ i�� ã�´�.
	 * 
	 * @param data
	 * @param p
	 * @param r
	 * @param i
	 * @return
	 */
	public int select(int [] data, int p, int r, int i) {
		recursiveCount++;
		
		if(p>r) {
			System.out.println("Invalid argument.");
			return -1;
		}
		if(p==r)
			return data[p];
		
		int q = partition(data, p, r);
		int k = q-p;
		if(i<k)
			return select(data, p , q-1, i);
		else if(i==k)
			return data[q];
		else
			return select(data, q+1, r, i-(q-p+1));
		
	}
	/**
	 *data�� Quick sort�� �ϴ� �޼���
	 * 
	 * pivot(���� ��)�� ���� ���Ƿ� r�� ����, left�� �������� p�� right�� ������ r�� ����
	 * index�� ���ذ� ���ʺ��� �񱳸� �Ͽ� ������ ���ΰ� ũ�� ���������� �� ���� �Ѵ�.
	 * index�� ���ذ� �����ʺ��� �񱳸� �Ͽ� ũ�� ���ΰ� ������ �������� �� ���� �Ѵ�.
	 * 
	 * �� �� �����̾��� index�� ��ȯ�Ѵ�.
	 * @param data
	 * @param p
	 * @param r
	 * @return right  ��ȯ �� �����̾��� index
	 */
	private int partition(int[] data, int p, int r) {
		int pivot = r;
		
		int left = p;
		int right = r;
		
		while(true) {
			while(data[left]<data[pivot] && left<right) left++;
			while(data[right]>=data[pivot] && left<right) right--;
			if(left<right) swapData(data, left, right);
			else break;
		}
		swapData(data, pivot, right);
		return right;
	}
	/**
	 * data�� ��ġ�� �ٲٴ� �޼���
	 * @param data
	 * @param i
	 * @param j
	 */
	private void swapData(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		
	}
	
	/**
	 * �־��� ��� �����ð� ���� �˰���
	 * 
	 * ���� �����ð� ���� �˰��򿡼� ���ҵ��� 5���� ���� �� �߾Ӱ��� ã�� �߾Ӱ��� �������� ���� �� ��Ҹ� ���ϴ� ���.
	 * 
	 * 1. ù��° index�� p �� ������ index�� r���� ũ�� data �迭�� �����̹Ƿ� -1�� return 
	 * 2. p�� r�� ���� ��� �迭�� ũ�Ⱑ 1�̹Ƿ� data[p]�� return 
	 * 3. �� ���ǵ��� �ƴ� ��� linearPartition�� ���� Quick sort�� �� ���Ҹ� 5�� �� ���� �߾Ӱ��� �������� ã�´�.
	 * �� �� �������� q-p�� k�� ���� �� �� i�� k�� ���Ͽ� i�� ��ġ�� ã�´�.
	 * i<k�� ��� ���������� ���� ������ �������� �߽����� �� �߾Ӱ����� ���� ���ҵ��̹Ƿ� �ٽ� ������ �߾Ӱ��� �������� ���� �� �ݺ��Ͽ� ã�´�.
	 * i==k�� ��� �������� ��ġ�� ���̹Ƿ� data[i]�� return �Ѵ�.
	 * i>k�� ��� ������ ���� ū ������ �ϳ��� �������� �߽����� ���ڸ��� �߾Ӱ� ���̿��� �ٽ� �������� ��� ã�� �۾��� �ݺ��Ѵ�.
	 * @param data
	 * @param p
	 * @param r
	 * @param i
	 * @return
	 */
	public int linearSelect(int [] data, int p, int r , int i) {
		recursiveCount++;
		
		if(p>r) {
			System.out.println("Invalid argument.");
			return -1;
		}
		if(p==r)
			return data[p];
		
		int q = linearPartition(data, p, r);
		int k = q-p;
		if(i<k)
			return linearSelect(data, p , q-1, i);
		else if(i==k)
			return data[q];
		else
			return linearSelect(data, q+1, r,  i-(q-p+1));
	}
	/**
	 * data�� Quick Sort �ϸ� 5���� ������ �߾Ӱ��� ��� �޼���
	 * 
	 * pVaule�� median�� ���� data�� 5���� ���� ������ ���� ������ �Ѵ�.
	 * pivot(5���� ���� �߾Ӱ��� ���� ��), left�� �������� p�� right�� ������ r�� ����
	 * index�� pivot�� ���ʺ��� �񱳸� �Ͽ� ������ ���ΰ� ũ�� ���������� �� ���� �Ѵ�.
	 * index�� pivot�� �����ʺ��� �񱳸� �Ͽ� ũ�� ���ΰ� ������ �������� �� ���� �Ѵ�.
	 * 
	 * @param data
	 * @param p
	 * @param r
	 * @return
	 */
	private int linearPartition(int[] data, int p, int r) {
		int pValue = median(data, p, r);
		int index=0;
		for(int i=p; i<=r; i++) {
			if(data[i]==pValue) {
				index =  i;
				break;
			}
		}
		
		swapData(data, r, index);
		int pivot = r;
		
		int left = p;
		int right = r;
		
		while(true) {
			while(data[left]<data[pivot] && left<right) left++;
			while(data[right]>=data[pivot] && left<right) right--;
			if(left<right) swapData(data, left, right);
			else break;
		}
		swapData(data, pivot, right);
		return right;
	}
	
	/**
	 * data�� 5���� ������ �߾Ӱ��� �߾Ӱ��� ���ذ��� ��� �޼���
	 * 
	 * 1. data�� �������� �������� ���̸� ����� r-p+1�� 5���� ���� ��� �ٷ� median5�޼��带 ���� ã�� data�� ��ȯ��Ų��.
	 * 2. ���� ��찡 �ƴ� ��� data�� ������ 5�� ���� �ݿø� �� �� �߾Ӱ��� ������ ���Ѵ�.
	 * �� �� �߾Ӱ��鸸 ������� �迭�� ���� ����� ���ο� �迭 �ȿ��� ������ �Ѵ�.
	 * 
	 * 
	 * @param data
	 * @param p
	 * @param r
	 * @return
	 */
	private int median(int[] data, int p, int r) {
		if((r-p+1) <=5)
			return median5(data, p, r);
		float f = r-p+1;
		int arrSize = (int) Math.ceil(f/5);
		int [] medianArr = new int [arrSize];
		for(int i=0; i<arrSize; i++) {
			medianArr[i] = median5(data, p+5*i, (int)Math.min(p+5*i+4, r));
		}
		return median(medianArr, 0, arrSize-1);
	}
	/**
	 * 5���� ������ ��� ���� Ȥ�� �迭�� ������ p�� ���� r�� ���� ��� data�� return ���ִ� �޼��� 
	 * @param data
	 * @param p
	 * @param r
	 * @return
	 */
	private int median5(int[] data, int p, int r) {
		if(p==r)
			return data[p];
		sort5(data, p, r);
		return data[(p+ (int) ((r-p+1))/2)];
	}
	/**
	 * �迭�� ���� ���Ͽ� �������ִ� �޼���
	 * @param data
	 * @param p
	 * @param r
	 */
	private void sort5(int[] data, int p, int r) {
		for(int i=p; i<r; i++) 
			for(int j=i+1; j<=r; j++)
				if(data[i]>data[j])
					swapData(data, i, j);
		
	}
	public static void main(String[] args) {
		int [] data = {5, 27, 24, 6, 35, 3, 7, 8, 18, 71, 77, 9, 11, 32, 21, 4};
		
		SelectTest s = new SelectTest();
		for(int i=0; i<data.length; i++)
			System.out.print(s.select(data, 0, data.length-1, i)+"   ");
		System.out.println();
		System.out.println("# of Recursive Calls of Select = "+s.getCount());
		
		s.resetCount();
		for(int i=0; i<data.length; i++)
			System.out.print(s.linearSelect(data, 0, data.length-1, i)+"   ");
		System.out.println();
		System.out.println("# of Recursive Calls of LinearSelect = "+s.getCount());
	}

}
