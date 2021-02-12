package lec1;

import java.util.Arrays;
import java.util.Random;
import java.util.Spliterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class SplitSum {

    private static int sum(Spliterator.OfInt s) {
        int[] sum = {0};
        s.forEachRemaining((int i) -> sum[0] += i);
        return sum[0];
    }

    private static int parallelSum(Spliterator.OfInt si) throws ExecutionException, InterruptedException {
        Spliterator.OfInt split = si.trySplit();
        if (split != null) {
            ExecutorService pool = ForkJoinPool.commonPool();
            Future<Integer> f1 = pool.submit(() -> sum(si));
            Future<Integer> f2 = pool.submit(() -> sum(split));
            return f1.get().intValue() + f2.get().intValue();
        } else {
            return sum(si);
        }
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[500_000_000];
        Random rnd = new Random(0);
        for (int j = 0; j < array.length; j++) {
            array[j] = rnd.nextInt();
        }
        System.out.println("START");
        Spliterator.OfInt arraySplit = Arrays.spliterator(array);
        long t1 = System.currentTimeMillis();
        int sum = parallelSum(arraySplit);
        long t2 = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(t2 - t1);
    }
}
