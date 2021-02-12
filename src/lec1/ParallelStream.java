package lec1;

import java.util.Arrays;
import java.util.List;

public class ParallelStream {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B");
        long t1 = System.currentTimeMillis();
        int sumLen = list.stream().mapToInt(str -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                // ignore
            }
            return str.length();
        }).sum();
        long t2 = System.currentTimeMillis();
        System.out.println(sumLen);
        System.out.println(t2 - t1);
    }
}
