package lec1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterator<E> implements Iterator<E> {

    private final Iterator<E> original;
    private final Predicate<E> predicate;

    private boolean nextExists = false;
    private E next = null;

    public FilteredIterator(Iterator<E> original, Predicate<E> predicate) {
        this.original = original;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (nextExists)
            return true;
        while (original.hasNext()) {
            E elem = original.next();
            if (predicate.test(elem)) {
                nextExists = true;
                next = elem;
                return true;
            }
        }
        return false;
    }

    @Override
    public E next() {
        if (hasNext()) {
            E result = next;
            nextExists = false;
            next = null;
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }
}
