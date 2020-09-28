# Sliding Window
[Siding Window](https://www.educative.io/courses/grokking-the-coding-interview/7D5NNZWQ8Wr)
## Example question 1 - Average of subarray of size K:
> Given an array, find the **average** of all contiguous subarrays of size 'K' in it.
    `Array: [1,3,2,6,-1,4,1,8,2],K=5`

### Brute force:

```java
public static double[] findAverageBrute(int[] arr, int K) {
        double[] res = new double[arr.length-K+1];
        for (int i=0;i<=arr.length-K;i++) {
            double total = 0; // to ensure `total/K` gives us a double
            for (int j=i;j<i+K;j++) {
                total += arr[j];
            }
            res[i] = total/K;
        }
        return res;
    }
```
1. Time Complexity:
>   * We are going to calculate `(N-K)*K` since for every `(N-K)` element, we would like to calculate the sum of its next `K` elements.
>   * This gives us `O(N*K)` where `N` is the number of elements in the array.
2. Inefficiency:
> For every two consecutive array, there is a `K-1` overlapping part and we have evaluated twice.

### Sliding window:

``` java
public static double[] findAverageSliding(int[] arr, int K) {
        double[] res = new double[arr.length-K+1];
        double total = 0;
        int start = 0;
        for (int end=0;end<arr.length;end++) {
            total += arr[end];
            if (end>=K-1) {
                res[start] = total/K;
                total -= arr[start];
                start++;
            }
        }
        return res;
    }
```
1. Time Complexity:
>   * O(N)

## Exampe question 2 - Maximum sum subarray of size K:
> Given an array of positive numbers and a positive number `K`, find the maximum sum of any contiguous subarray of size `K`.

```java
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
```
1. Time complexity:
>   * O(N)
2. Space complexity:
>   * O(1)

## Example question 3 - Smallest Subarray with a given sum:
> Given an array of positive numbers and a positive number S, find the length of smallest contiguous subarray whose sum is greater than or equal to S. Return 0, if no such subarray exists.

```java
public static int findSmallSubarray(int[] arr,int S) {
        int length = 0;
        int minLen = 1000;
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
        return minLen;
    }
```
1. A flexible sliding window
    * Add up elements in a stepwise fashion from the beginning of the array until their sum becomes greater than or equal to S
    * In each step, we will aso try to shrink the window from the beginning. This is needed as we intend to find the smallest window.
        - Check if the current window length is the smallest so far, and if so, remember its length.
        - Subtract the first element of the window from the running sum to shrink the sliding window.
2. Time Complexity
>   * O(N) 
>   * The outer for loop runs for all elements and the inner while loop processes each element only once, so the time complexity will be O(N+N)(?)
3. Space Complexity
>   * O(1)

## Example question 4 - Longest Substring with K Distinct Characters:
> Given a string, find the length of the longest substring in it with no more than K distinct characters.

```java
    public static int findLength(String str, int k) {
        int[] flag = new int[26];
        int start = 0;
        int maxLen = -1;
        int length = 0;
        int disChar = 0;
        for (int end=0;end<str.length();end++) {
            int ch = (int)str.charAt(end)-(int)'a';
            if (flag[ch]==0) {
                disChar ++;
            }
            flag[ch] ++;
            length ++;
            // System.out.println("distinct character:"+disChar);
            while (disChar>k) {
                ch = (int)str.charAt(start)-(int)'a';
                flag[ch] --;
                if (flag[ch]==0) {
                    disChar --;
                }
                length --;
                start ++;
            }
            if (disChar<=k) {
                maxLen = maxLen<length?length:maxLen;
                // System.out.println("max length:"+maxLen);
            }
        }
        return maxLen;
    }
```
1. Hashmap:
    * To remember the frequency of each character we have processed
2. Time complexity:
    * O(N) where N is the number of characters in the input string.
    * The outer `for` loop runs for all characters and the inner while `while` loop processes each charater only once, therefore the complexity of the algorithm will be O(N+N) which is asymptotically equivalent to O(N).
3. Space complexity:
    * O(26) as we will store a maximum of 26 characters in the hash map.
    * We can also use `map<Character, Integer>` to store the frequency of the characters and it gives us a space complexity of O(K)!

## Example question 5 - Fruits into Baskets:

```java
// Version 1 uaing a 1D array:
public static int findLength(char[] arr) {
        int start = 0;
        int len = 0;
        int maxLen = -1;
        int disT = 0;
        int[] flag = new int[26];
        for (int end=0;end<arr.length;end++) {
            int ch = (int) arr[end] - (int) 'A';
            if (flag[ch]==0) {
                disT ++;
            }
            flag[ch] ++;
            len ++;
            while (disT>2) {
                ch = (int) arr[start] - (int) 'A';
                flag[ch] --;
                if (flag[ch]==0) {
                    disT --;
                }
                len --;
                start ++;
            }
            maxLen = maxLen < len?len:maxLen;
        }
        return maxLen;
    }
// Version 2 using a map:
public static int findLengthMap(char[] arr) {
        Map<Character, Integer> fm = new HashMap<>();
        int start = 0;
        int maxLen = -1;
        for (int end=0;end<arr.length;end++) {
            fm.put(arr[end], fm.getOrDefault(arr[end], 0)+1);
            while (fm.size()>2) {
                fm.put(arr[start], fm.get(arr[start])-1);
                if (fm.get(arr[start])==0) {
                    fm.remove(arr[start]);
                }
                start ++;
            }
            maxLen = maxLen < end-start+1?end-start+1:maxLen;
        }
        return maxLen;
    }
```
1. Time complexity:
    * O(N)
2. Space complexity:
    * O(1)
    * The maximum types of fruits stored will be three types

## Example question 6 - No-repeat Substring:
> Given a string, find the length of the longest substring which has no repeating characters.

``` java
    
```







