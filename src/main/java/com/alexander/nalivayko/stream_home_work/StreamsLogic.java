package com.alexander.nalivayko.stream_home_work;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsLogic {

    public static String numerateOdd(List<String> stringList) {
        return IntStream.range(0, stringList.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> (i + 1) + ". " + stringList.get(i))
                .collect(Collectors.joining(", "));
    }

    public static List<String> capitalizeAndSortDescending(List<String> stringList) {
        return stringList.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static List<Integer> parseNumbersInCollection(Collection<String> collection) {
        return collection.stream()
                .map(str -> str.split(","))
                .flatMap(Arrays::stream)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static Stream<Long> generateRandomNumbers(long a, long c, long m, long seed) {
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Queue<T> elementsA = first.collect(Collectors.toCollection(LinkedList::new));
        Queue<T> elementsB = second.collect(Collectors.toCollection(LinkedList::new));
        return Stream.generate(new Supplier<T>() {
            boolean first = true;
            @Override
            public T get() {
                Queue<T> queue = first ? elementsA : elementsB;
                first = !first;
                return queue.poll();
            }
        }).limit(Math.min(elementsA.size(), elementsB.size()) * 2);
    }
}
