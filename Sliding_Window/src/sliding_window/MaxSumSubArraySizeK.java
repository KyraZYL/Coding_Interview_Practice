package sliding_window;

/*
 
 */


public class MaxSumSubArraySizeK {
	public static int findMaxSumArray(int[] arr, int K) {
		int sum = 0;
		int maxSum = -1;
		int start = 0;
		for (int end=0;end<arr.length;end++) {
			sum += arr[end];
			if (end>=K-1) {
				maxSum = (maxSum>sum)?maxSum:sum;
				sum -= arr[start];
				start++;
			}
		}
		return maxSum;
	}
	
	public static void main(String[] args) {
		int res = findMaxSumArray(new int[] {2,1,5,1,3,2}, 3);
		System.out.println("res:"+res);
	}
}
