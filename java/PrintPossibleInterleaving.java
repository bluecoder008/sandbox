import java.util.*;

public class PrintPossibleInterleaving {

	public static void main(String[] args) {

		System.out.println("Please enter String1: ");
		String s1 = System.console().readLine();

		System.out.println("Please enter String2: ");
		String s2 = System.console().readLine();

		List<String> allPossibles = generateInterleaving(s1, s2);

		for(String s : allPossibles ) {
			System.out.println(s);
		}
	}

	static List<String> generateInterleaving(String A, String B) {

		List<String> ret = new ArrayList<>();
		if ( A.isEmpty() ) {
			ret.add(B);
			return ret;
		}

		if ( B.isEmpty() ) {
			ret.add(A);
			return ret;
		}

		if ( A.length() == 1 && B.length() == 1 ) {
			ret.add(A+B);
			ret.add(B+A);
			return ret;
		} 

		// Case 1, the output string starts with A[0]

		List<String> output1 = generateInterleaving(A.substring(1), B);
		for(String s : output1) {
			ret.add( A.substring(0,1) + s );
		}

		// Case 2, the output string starts with B[0]

		List<String> output2 = generateInterleaving(A, B.substring(1));
		for(String s : output2) {
			ret.add( B.substring(0,1) + s );
		}
		return ret;
	}
}
