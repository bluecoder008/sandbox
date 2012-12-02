
/**
 * This sample code demonstates how to find the median of
 * five elements using no more than 6 comparisons
 *
 */

class FindMedian
{
    static void printResults(int[] a, int m) {

       System.out.print("===> Input[" + a.length + "]: ");

       for(int n = 0; n < a.length; n++) {
           System.out.print(a[n] + " ");
       }
       System.out.println("\n===> Median:" + m);

    }

    // Find the median of three numbers,
    // using 2 comparisons only
    static int findMedian3(int[] a) {

       int median;
       if ( (a[0] - a[1]) * (a[0] - a[2]) < 0 )
           median = a[0];

       else if ( (a[1] - a[0]) * (a[1] - a[2]) < 0 )
           median = a[1];

       else
           median = a[2];
       printResults(a, median);
       return median;
    }

    //
    // Find the median of five numbers
    // using 4 comparisons plus the ones needed for three numbers
    // 4 + 2 => 6
    //
    // The key observation: if a number is known to be less than 3 numbers,
    // or, larger than 3 numbers, it can elmininated to be median.
    // 
    static int findMedian5(int[] a) 
    {
        // comparison 1  -> afterwards: a[0] < a[1]  
        if ( a[0] < a[1] ) {
            ; //nop
        } else {
           int tmp = a[0];
           a[0]    = a[1];
           a[1]    = tmp ;
        }

        // comparison 2  -> afterwards: a[3] < a[4]  
        if ( a[3] < a[4] ) {
            ; //nop
        } else {
           int tmp = a[3];
           a[3]    = a[4];
           a[4]    = tmp ;
        }

        int[] b = new int[3];
 
        // comparison 3 -> take the larger of smaller ones
        if ( a[0] < a[3] )
            b[0] = a[3];
        else
            b[0] = a[0];

        // comparison 4 -> take the smaller of larger ones
        if ( a[1] < a[4] )
            b[1] = a[1];
        else
            b[1] = a[4];

        b[2] = a[2];

        // comparison 5-6 -> spent in findMedian3 method
        int m = findMedian3(b);
        printResults(a, m);
        return m;
    }

    public static void main(String[] args) {

        if ( args.length != 5 ) {
            System.err.println("Please enter five distinct integer values.");
            System.exit(-1);
        }

        int[] inputs = new int[5];

        for(int n = 0; n < args.length; n++ ) {

            inputs[n] = Integer.parseInt(args[n]);
        }

        int m = findMedian5(inputs);
       
    }
}
