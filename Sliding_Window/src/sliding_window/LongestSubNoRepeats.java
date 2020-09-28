package sliding_window;

import java.util.*;

public class LongestSubNoRepeats {
	public static int findLength(String s) {
		int maxLen = 0;
		Map<Character, Integer> sm = new HashMap<>(); 
		// we store the character and the position of the character in the string
		int start = 0;
		for (int end = 0; end < s.length();end ++) {
			if (sm.containsKey(s.charAt(end))) {
				start = Math.max(start, sm.get(s.charAt(end))+1);
			}
			sm.put(s.charAt(end),end);
			maxLen = maxLen < end-start+1?end-start+1:maxLen;
		}
		return maxLen;
	}
	
	public static void main(String[] args) {
		int res = findLength("abcbde");
		System.out.println(res);
	}
}
