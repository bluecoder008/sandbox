
class BinarySearchCircularSortedArray {

    public static void main(String[] args) {     

        System.out.println("Please enter the array: ");
	String line = System.console().readLine();

	String[] lineParts = line.split("\\s+");
	int[] A = new int[lineParts.length];

	for(int n=0; n<A.length; n++) {
		A[n] = Integer.parseInt(lineParts[n]);
	}
	
        System.out.println("Please enter the key: ");
	line = System.console().readLine();
	System.out.println("line: " + line);
	int key = Integer.parseInt(line);

	int index = findKeyInCircularArray(A, key);
	System.out.println("returned index is: " + index);
    }

    static int findKeyInCircularArray(int[] A, int key) {

	int lo = 0;
	int hi = A.length - 1;

	while ( lo < hi ) {
		
		if ( A[lo] == key ) {
			return lo;
		}
		
		if ( A[hi] == key ) {
			return hi;
		}
		int mid = lo + hi >>> 1;

		if ( A[lo] < A[mid] ) { // Use the first half to check
			if ( A[lo] < key && key < A[mid] ) {
				hi = mid - 1 ;
			} else {
				lo = mid + 1;
			}
		} else {                // Use the second half to check
			if ( A[mid] < key && key < A[hi] ) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
	}
	// At this point, hi == lo
	if ( A[hi] == key ) {
		return hi;
	}
	return -1;
    }
}

