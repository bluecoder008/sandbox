import java.util.*;

class MaxSubArrayProductOne {

    static public int maxSubArrayProductOne(List<Integer> array) {

        if (array.isEmpty()) {
            return 0;
        }

        int N = array.size();
        Integer[] a = new Integer[N];
 
        a = array.toArray(a); 
        System.out.println(Arrays.toString(a));

        int[] product = new int[N];
        product[0] = 1;

        int max = 1;
        for(int m = 1; m < N; m++) {
           if ( m == 1) {
               product[m] = a[0] * a[1];
           } else {
               product[m] = product[m-1] * a[m];
               if ( product[m] == 1 && (m+1) > max ) {
                   max = m + 1;
               }
           }
        }       

        for(int start=1; start < N; start++) {
            if ( max > (N-start)) {
                System.out.println("(shortcut) max=" + max);
                return max;
            }
            for(int end=start+1; end < N; end++) {
               int cur = product[end] * product[start] * a[start];
               if (cur == 1 && (max < (end-start+1))) {
                   max = end - start + 1;
               }
            }

        }
        System.out.println("max=" + max);
        return max;
    }

    static public void main(String[] args) {

        List<Integer> values = Arrays.asList(1, -1, -1, -1, 1, 1);
        maxSubArrayProductOne(values);

        values = Arrays.asList(1, -1, -1, -1, 1, 1, -1, -1, 1, 1, 1);
        maxSubArrayProductOne(values);
    }
}
