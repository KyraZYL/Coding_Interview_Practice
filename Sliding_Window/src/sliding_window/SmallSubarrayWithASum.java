package sliding_window;

public class SmallSubarrayWithASum {
	public static int findSmallSubarray(int[] arr,int S) {
		int length = 0;
		int minLen = Integer.MAX_VALUE;
		int sum = 0;
		int start =0;
		for (int end=0;end<arr.length;end++) {
			sum += arr[end];
			length ++;
			while (sum>=S) {
				minLen = (minLen>length)?length:minLen;
				sum -= arr[start];
				length --;
				start++;
			}
		}
		return (minLen==Integer.MAX_VALUE)?0:minLen;
	}
	
	public static void main(String[] args) {
		int res = findSmallSubarray(new int[] {2,1,5,2,8},7);
		System.out.println("Res:"+res);
	}
}
