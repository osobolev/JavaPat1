package lec1;

import java.util.Iterator;
import java.util.function.Function;

public class MappedIterator<F, T> implements Iterator<T> {

    private final Iterator<F> original;
    private final Function<F, T> function;

    public MappedIterator(Iterator<F> original, Function<F, T> function) {
        this.original = original;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        return original.hasNext();
    }

    @Override
    public T next() {
        F elem = original.next();
        return function.apply(elem);
    }
}
