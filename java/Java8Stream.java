

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by weilan_wu on 4/3/16.
 * <p>
 * Exercise to check out Java8 feature of stream: Given a list of arbitrary integers
 * filter out the even numbers and return them in sorted order
 */
public class Java8Stream {

    public static void main(String[] args) {

        final int N = 10;

        List<Integer> givenList = new LinkedList<>();
        for (int n = 0; n < N; n++) {
            givenList.add((int) (Math.random() * 100));
        }
        System.out.println("Given list:");
        givenList.stream().forEach(System.out::println);

        System.out.println("Filter list:");
        Stream<Integer> results = givenList.stream().filter(s -> s % 2 == 0).sorted();
        results.forEach(System.out::println);
    }
}
