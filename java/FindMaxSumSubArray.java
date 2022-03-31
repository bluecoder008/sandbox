
public class FindMaxSumSubArray {

	public static void main(String[] args) {

		String inputLine = System.console().readLine();
		String[] inputs = inputLine.split("\\s+");
		Integer[] inputNums = new Integer[inputs.length];
		for (int n = 0; n < inputs.length; n++) {
			//System.out.println("input number: " + inputs[n]);
			inputNums[n] = Integer.parseInt(inputs[n]);
		}
		for (int n = 0; n < inputNums.length; n++) {
			System.out.println("inputNums[" + n + "]: " + inputNums[n]);
		}

		int max = findMaxSubArraySum(inputNums);
		System.out.println("max=" + max);
	}

	private static int findMaxSubArraySum(Integer[] inputNums) {

//		int retMax = Integer.MIN_VALUE;
//		int N = inputNums.length;
//
//		int[][] sum = new int[N][N];
//
//		return retMax;
		int s = 0, t = 0, current_sum = inputNums[0], max = inputNums[0];
		do {
			while(current_sum <= 0) {
				s = t + 1;
				t = s;
				if ( t >= inputNums.length) break;
				current_sum = inputNums[s];
			}

			while(current_sum > 0 && t < inputNums.length) {
				t++;
				if ( t >= inputNums.length) break;
				current_sum += inputNums[t];
				if (current_sum > max) {
					max = current_sum;
				}
			}
		} while(t < inputNums.length);
		return max;
	}
}
