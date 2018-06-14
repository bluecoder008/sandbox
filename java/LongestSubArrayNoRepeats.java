import java.util.*;

class LongestSubArrayNoRepeats {

    static String longestSubArray(/*@NotNull*/ String input) {

        if (input.isEmpty() || input.length() == 1) {
            return input;
        }

	//System.out.println("working on: " + input);

        int start = 0, end = 1, max = 1;
        String strMax = "" + input.charAt(0);

        Map<Character,Integer> seen = new HashMap<>();
        seen.put(input.charAt(0), 0); 
        int inputN = input.length();
        do {
            while((end < inputN) && !seen.containsKey(input.charAt(end))) {
               seen.put(input.charAt(end), end);
               end++;
            }
            // Now either repeat char in input[end] or end equals input.length()
            if (end == inputN) {
                break;
            }
            if ( (end - start) > max ) {
                max = end - start;
                strMax = input.substring(start, end);
            }
            start = seen.get(input.charAt(end)) + 1;
            end = start + 1;
            seen.clear();
            seen.put(input.charAt(start), start);
        } while(end < inputN);
        //System.out.println("=> " + strMax);
        return strMax;
    }

    public static void main(String[] args) {

       assert longestSubArray("abcabcbb").equals("abc");
       assert longestSubArray("bbbbb").equals("b");
       assert longestSubArray("pwwkew").equals("wke");

    }
}
