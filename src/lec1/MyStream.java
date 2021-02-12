package lec1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<E> {

    private final Iterator<E> iterator;

    public MyStream(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    public <T> MyStream<T> map(Function<E, T> function) {
        return new MyStream<>(new MappedIterator<>(iterator, function));
    }

    public MyStream<E> filter(Predicate<E> predicate) {
        return new MyStream<>(new FilteredIterator<>(iterator, predicate));
    }

    public List<E> toList() {
        List<E> list = new ArrayList<>();
        while (iterator.hasNext()) {
            E elem = iterator.next();
            list.add(elem);
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Иван", "Джон", "", "Хуан");
        List<String> greetings = new MyStream<>(list.iterator())
            .filter(str -> !str.isEmpty())
            .map(name -> String.format("Привет, %s!", name))
            .toList();
        System.out.println(greetings);
    }
}
