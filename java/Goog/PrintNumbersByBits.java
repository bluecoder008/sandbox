import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
Given N - which is the number of bits, print all the possible combinations of numbers formed by printing all numbers with one bit set, followed by two bits set, ... upto the point when all k-bits are set. They must be sorted according to the number of bits set, if two numbers have the same number of bits set then they should be placed as per their value. 

For example if N=3 
Output: 
000, 001, 010, 100,101, 110, 011, 111 
0 bits set, followed by 1 bits set followed by 2 bits set followed by 3 bits set.

*/

public class PrintNumbersByBits {

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        List<String> numbers = new ArrayList<String>();

        String allZeros = StringUtils.repeat('0', N);
        //for(int n = 0; n < N; n++) {
        //    allZeros += '0';
        //}
   
        List<String> currentSet  = Arrays.asList( allZeros );

        for(int n = 1; n <= N; n++) {
            numbers.addAll(currentSet);
            currentSet = getNumbersWithBitsSet(N, n, currentSet);
        }
        numbers.addAll(currentSet);

        boolean first = true;
        for(String num : numbers ) {
            System.out.print( (first ? "" : ", ") + num);
            first = false;
        }
        System.out.println();
    }

    /**
     * The function returns the list of bit patterns of N-bit with n bits set.
     *
     */
    static List<String> getNumbersWithBitsSet(int N, int n, List<String> prev) {

	List<String> retList = new ArrayList<String>();

        for(String bits : prev ) {
            for(int n1 = 0; n1 < N; n1++) {
                String newBits = setOneBit(bits, n1, N);
                if (newBits != null && !retList.contains(newBits) ) {
                    retList.add(newBits);
                }
            }
        }
        return retList;
    }

    static String setOneBit(String bits, int n1, int N) {

       for( int t = 0; t < N ; t++ ) {
           int n11 = (n1 + t) % N;
           if ( bits.charAt(n11) == '0' ) {
               StringBuilder sb = new StringBuilder(bits);
               sb.setCharAt(n11, '1');
               return sb.toString();
           }
       }
       return null;
    } 
}

