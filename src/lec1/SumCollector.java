package lec1;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SumCollector implements Collector<Number, SumAcc, Long> {

    @Override
    public Supplier<SumAcc> supplier() {
        return () -> new SumAcc();
    }

    @Override
    public BiConsumer<SumAcc, Number> accumulator() {
        return (acc, elem) -> acc.sum += elem.longValue();
    }

    @Override
    public BinaryOperator<SumAcc> combiner() {
        return (acc1, acc2) -> {
            acc1.sum += acc2.sum;
            return acc1;
        };
    }

    @Override
    public Function<SumAcc, Long> finisher() {
        return acc -> acc.sum;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
