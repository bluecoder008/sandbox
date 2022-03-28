/**

    Given a sorted array, find a way to find the # of occurrence of a number 
    for eg: [1, 1, 2, 3, 3, 3, 4, 5] X=3 -> 3
            [1, 1] X=1 -> 2
            [1, 1, 2, 2, 3, 3] X=2 -> 2
   find # of occurrence of 3 in time better than O(n)

*/

class FindNumberOccurence {

    static class Interval {
        int left = -1, right = -1;
	public String toString() {
		return "(" + left + "," + right + ")";
	}
    }

    /*
     * Find the sub-interval of sequence of number X in sorted
     * array A, between index s and t
     */
    static Interval findInterval(int[] A, int s, int t, int X) {
 
	//System.out.println("searching A [" + s + "," + t + "], X=" + X );   
	Interval ret = new Interval();

        if ( A[s] == X ) {
            ret.left = s;
        }

	if ( A[t] == X ) {
            ret.right = t;
	}

	//System.out.println("before check, ret=" + ret);
	if ( t == s + 1 ) {
		if ( ret.left > -1 && ret.right == -1 ) {
			ret.right = ret.left;
		}
		if ( ret.right > -1 && ret.left == -1 ) {
			ret.left = ret.right;
		}
		//System.out.println("returning: " + ret);
		return ret;
	}

	int mid = (s+t)/2;

	Interval ret1, ret2;

	if ( A[mid] >= X ) {
		ret1 = findInterval(A, s, mid, X );
		if ( ret1.left > -1 ) {
			ret.left = ret1.left;
		}
		if ( ret1.right > -1 ) {
			ret.right = ret1.right;
		}
	} 

	if ( A[mid] <= X ) {
                ret2 = findInterval(A, mid, t, X);
		if ( ret2.left > -1 && (ret2.left < ret.left || ret.left ==-1)) {
			ret.left = ret2.left;
		}
		if ( ret2.right > -1 && (ret2.right > ret.right || ret.right == -1)) {
			ret.right = ret2.right;
		}
	}
	//System.out.println("returning: " + ret);
	return ret;
    }

    public static void main(String[] args) {

        int[] A = new int[args.length-1];

	for(int n = 0; n < A.length ; n++) {
		A[n] = Integer.parseInt(args[n]);
	}

	int X = Integer.parseInt(args[A.length]);

	Interval interval = findInterval(A, 0, A.length-1, X);
	System.out.println("returned interval: " + interval);
        int occurance = 0;
        if (interval.left != -1 && interval.right != -1) {
            occurance = interval.right - interval.left + 1;
        }
	System.out.println("occurance of " + X + " is: " + occurance );
    }
}
