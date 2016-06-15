import java.util.*;
class FindDup {


    static Set<Integer> findDup(int[] A) {

	Set<Integer> ret = new HashSet<>();
	if ( A== null) {
		return ret;
	}
	
        int len = A.length;

	if (len <= 1) {
            return ret;
	}

        for(int x=0; x < len; x++) {

	    if( A[x] == x + 1 ) {
               continue;
	    }

	    // let n = A[x], swap A[x] and A[n-1],
            //  iff A[x] != A[n-1]
	    do {
		int Ax = A[x];
		if ( A[x] == A[Ax-1] ) {
			ret.add(A[x]);
		    	break;
		}
		int temp = A[x];
		A[x] = A[Ax-1];
		A[Ax-1] = temp;
            } while ( A[x] != x + 1 );
       }
       return ret;
    }

    public static void main(String[] args) {

        int[] A = new int[args.length];

	for(int x = 0; x < args.length; x++) {
            A[x] = Integer.parseInt(args[x]);
	}
	
	for(Integer num : findDup(A)) {
		System.out.println(num);
	}
    }
}
