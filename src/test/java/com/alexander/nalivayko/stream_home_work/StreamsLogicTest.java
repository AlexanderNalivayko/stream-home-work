package com.alexander.nalivayko.stream_home_work;

import org.assertj.core.util.Streams;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class StreamsLogicTest {
    @Test
    public void shouldNumerateOddStringsFromList() {
        List<String> names = Arrays.asList("John", "Kate", "Jany", "Bob", "Jim");
        String expected = "1. John, 3. Jany, 5. Jim";
        assertThat(StreamsLogic.numerateOdd(names)).isEqualTo(expected);
    }

    @Test
    public void shouldBringToUpperCaseAndSortDescending() {
        List<String> stringsList = Arrays.asList("alligator", "ball", "cheese");
        assertThat(StreamsLogic.capitalizeAndSortDescending(stringsList))
                .isSortedAccordingTo((Comparator<String>) (s1, s2) -> s2.compareTo(s1))
                .allMatch(str -> str.equals(str.toUpperCase()));
    }

    @Test
    public void shouldParseAllNumbers() {
        Collection<String> collection = Arrays.asList("1, 2, 0", "4, 5");
        assertThat(StreamsLogic.parseNumbersInCollection(collection))
                .containsAll(Arrays.asList(1, 2, 0, 4, 5));
    }

    @Test
    public void shouldGenerateLinearCongruentialSequence() {
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);
        long seed = 0;
        long limit = 1000;

        Long[] longArrayOne = StreamsLogic.generateRandomNumbers(a, c, m, seed).limit(limit).toArray(Long[]::new);
        Long[] longArrayTwo = StreamsLogic.generateRandomNumbers(a, c, m, seed).limit(limit).toArray(Long[]::new);

        assertThat(longArrayOne).containsSequence(longArrayTwo);
    }

    @Test
    public void shouldSupplyElementsFromBothStreamsAndStopWhenOneOfThemEmpty() {
        Stream<Integer> odd = IntStream.iterate(1, x -> x + 2).limit(10).boxed();
        Stream<Integer> even = IntStream.iterate(2, x -> x + 2).limit(15).boxed();
        List<Integer> actual = StreamsLogic.zip(odd, even).collect(toList());
        List<Integer> expected = IntStream.iterate(1, x -> x + 1).limit(20).boxed().collect(toList());
        assertThat(actual).isEqualTo(expected);
    }
}
