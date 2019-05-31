
class Partitioner {

    /* parititon the list as less-than and greater-than part,
       using the first element as pivot
     */
    static int partition(int[] a) {
        if (a.length  == 0) {
            return 0;
        }
        int pivot = a[0];
        int ret = a.length;
        for(int n=1; n < a.length; n++) {    

            if ( a[n] > pivot ) {
                if (ret == a.length) {
                    ret = n;
                }
            } else {
                if ( ret < a.length) {
                    int temp = a[ret];
                    a[ret] = a[n];
                    a[n] = a[ret];
                    ret = ret + 1;
                }
            }
        }
        return ret - 1;
    }

    public static void main(String[] args) {

        int a[] = { 4, 6, 8, 2, 1, 5, 1, 6 };
        for(int n=0; n < a.length; n++) {
            System.out.println( a[n] );
        }
        System.out.println( partition(a) );
    }
}
