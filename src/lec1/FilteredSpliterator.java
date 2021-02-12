package lec1;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilteredSpliterator<E> implements Spliterator<E> {

    private final Spliterator<E> original;
    private final Predicate<E> predicate;

    private E found = null;

    public FilteredSpliterator(Spliterator<E> original, Predicate<E> predicate) {
        this.original = original;
        this.predicate = predicate;
    }

    @Override
    public boolean tryAdvance(Consumer<? super E> action) {
        while (original.tryAdvance(e -> found = e)) {
            if (predicate.test(found)) {
                action.accept(found);
                return true;
            }
        }
        return false;
    }

    @Override
    public Spliterator<E> trySplit() {
        Spliterator<E> split = original.trySplit();
        if (split == null)
            return null;
        return new FilteredSpliterator<>(split, predicate);
    }

    @Override
    public long estimateSize() {
        return original.estimateSize();
    }

    @Override
    public int characteristics() {
        return original.characteristics();
    }
}
