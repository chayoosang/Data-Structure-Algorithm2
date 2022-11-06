package week3Select;
/**
 * 선택 알고리즘
 * @author 차유상
 *
 */
public class SelectTest {
	/**
	 * 반복 횟수 
	 */
	int recursiveCount = 0;
	/**
	 * 반복 횟수 reset 하는 메서드
	 */
	public void resetCount() {
		recursiveCount=0;
	}
	/**
	 * 반복 횟수 return 해주는 메서드
	 * @return recursiveCount
	 */
	public int getCount() {
		return recursiveCount;
	}
	/**
	 * 평균 선형시간 선택 알고리즘
	 * 
	 * 1. 첫번째 index인 p 가 마지막 index인 r보다 크면 data 배열의 오류이므로 -1을 return 
	 * 2. p와 r이 같은 경우 배열의 크기가 1이므로 data[p]를 return 
	 * 3. 위 조건들이 아닌 경우 Quick Sort방실을 활용하는 partition 메서드를 통해 q를 지정하여 순서를 지정 한 후
	 * q를 기준으로 반반을 나눠 왼쪽은 q보다 작은집단, 오른쪽은 q보다 큰 집단으로 q-p인 k와 i를 비교를 하여 찾는다.
	 * i<k 일 경우 q의 기준으로 왼쪽 집단에서 범위를 좁히며 i를 찾는다.
	 * i==k 일 경우 찾는 값이 data의 index가 k인 값이다.
	 * i>k 일 경우 q의 기준으로 오른쪽 집단에서 범위를 좁히며 i를 찾는다.
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
	 *data를 Quick sort를 하는 메서드
	 * 
	 * pivot(기준 값)의 값을 임의로 r로 지정, left는 시작점인 p로 right는 끝점인 r로 지정
	 * index가 기준과 왼쪽부터 비교를 하여 작으면 냅두고 크면 오른쪽으로 재 정렬 한다.
	 * index가 기준과 오른쪽부터 비교를 하여 크면 냅두고 작으면 왼쪽으로 재 정렬 한다.
	 * 
	 * 그 후 기준이었던 index를 반환한다.
	 * @param data
	 * @param p
	 * @param r
	 * @return right  교환 후 기준이었던 index
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
	 * data의 위치를 바꾸는 메서드
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
	 * 최악의 경우 선형시간 선택 알고리즘
	 * 
	 * 기존 선형시간 선택 알고리즘에서 원소들을 5개씩 나눈 후 중앙값을 찾아 중앙값의 기준점을 잡은 후 대소를 비교하는 방식.
	 * 
	 * 1. 첫번째 index인 p 가 마지막 index인 r보다 크면 data 배열의 오류이므로 -1을 return 
	 * 2. p와 r이 같은 경우 배열의 크기가 1이므로 data[p]를 return 
	 * 3. 위 조건들이 아닌 경우 linearPartition을 통해 Quick sort를 한 원소를 5개 씩 나눠 중앙값과 기준점을 찾는다.
	 * 그 후 기준점을 q-p인 k로 지정 한 후 i와 k를 비교하여 i의 위치를 찾는다.
	 * i<k일 경우 기준점보다 작은 것으로 기준점을 중심으로 각 중앙값보다 작은 원소들이므로 다시 사이의 중앙값을 기준으로 잡은 후 반복하여 찾는다.
	 * i==k일 경우 기준점에 위치한 것이므로 data[i]를 return 한다.
	 * i>k일 경우 기준점 보다 큰 원소중 하나로 기준점을 중심으로 끝자리의 중앙값 사이에서 다시 기준점을 잡아 찾는 작업을 반복한다.
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
	 * data를 Quick Sort 하며 5개로 나누어 중앙값을 잡는 메서드
	 * 
	 * pVaule를 median을 통해 data를 5개씩 끊어 나누어 먼저 정렬을 한다.
	 * pivot(5개로 나눈 중앙값의 기준 값), left는 시작점인 p로 right는 끝점인 r로 지정
	 * index가 pivot과 왼쪽부터 비교를 하여 작으면 냅두고 크면 오른쪽으로 재 정렬 한다.
	 * index가 pivot과 오른쪽부터 비교를 하여 크면 냅두고 작으면 왼쪽으로 재 정렬 한다.
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
	 * data를 5개로 나누어 중앙값과 중앙값의 기준값을 잡는 메서드
	 * 
	 * 1. data의 시작점과 끝점으로 길이를 계산한 r-p+1이 5보다 작을 경우 바로 median5메서드를 통해 찾는 data를 반환시킨다.
	 * 2. 위의 경우가 아닌 경우 data의 개수를 5로 나눠 반올림 한 후 중앙값의 개수를 정한다.
	 * 이 후 중앙값들만 집어넣을 배열을 새로 만들어 새로운 배열 안에서 정렬을 한다.
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
	 * 5개로 나눴을 경우 정렬 혹은 배열의 시작점 p와 끝점 r이 같을 경우 data를 return 해주는 메서드 
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
	 * 배열을 서로 비교하여 정렬해주는 메서드
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
