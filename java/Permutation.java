
/**
 * Given a character string, consists of non-repeating characters, print all permutation of the original string
 * Ex:  "ABC" => "ABC", "ACB", "BCA", "BAC", "CBA", "CAB"
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Permutation {

    public static void main(String[] args) {

        // error checking
	if (args.length != 1 ) {
            throw new IllegalArgumentException("Argument size should be exactly 1.");
	}
	
        String inputStr = args[0];

        printPermutation(inputStr);      
    }

    private static void printPermutation(String input) {

	System.out.println("Permutations:");
	for(String one : enumeratePermutation(input) ) {
		System.out.print(" " + one);
	}
    }

    private static List<String> enumeratePermutation(String str) {

	List<String> returnList = new ArrayList<>();

        if (StringUtils.isBlank(str) ) {
            throw new IllegalArgumentException();
        }

	if (str.length() == 1) {
		returnList.add(str);
		return returnList;
        }

	for(int n = 0; n < str.length(); n++) {
		String subStr1 = "";
		String subStr2 = "";
		if ( n > 0 ) {
			subStr1 = str.substring(0,n);
		}
		if ( n < str.length() - 1 ) {
			subStr2 = str.substring(n+1);
		}
		for(String one : enumeratePermutation(subStr1 + subStr2)) {
			returnList.add( str.substring(n,n+1) + one );
		}
        }
	return returnList;
    }
}

