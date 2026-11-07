package neetcode.arraysandhashing.topkfrequentelements;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TopKFrequentElementsTest {

    @FunctionalInterface
    interface TopKFrequentMethod {
        int[] apply(int[] nums, int k);
    }

    private static final TopKFrequentElements solution = new TopKFrequentElements();

    static Stream<Named<TopKFrequentMethod>> implementations() {
        return Stream.of(
                Named.of("topKFrequent", solution::topKFrequent));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(TopKFrequentMethod topKFrequent) {
        assertSameElements(new int[] {2, 3}, topKFrequent.apply(new int[] {1, 2, 2, 3, 3, 3}, 2));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(TopKFrequentMethod topKFrequent) {
        assertSameElements(new int[] {7}, topKFrequent.apply(new int[] {7, 7}, 1));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void topOneUnsorted(TopKFrequentMethod topKFrequent) {
        assertSameElements(new int[] {4}, topKFrequent.apply(new int[] {1, 2, 3, 4, 5, 1, 2, 4, 5, 4}, 1));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void topTenOverTen(TopKFrequentMethod topKFrequent) {
        assertSameElements(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, topKFrequent.apply(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void topThreeAtTop(TopKFrequentMethod topKFrequent) {
        assertSameElements(new int[] {5, 4, 3}, topKFrequent.apply(new int[] {1, 2, 3, 3, 3, 4, 4, 5, 4, 5, 4, 5, 4, 5}, 3));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void topThreeAtBottom(TopKFrequentMethod topKFrequent) {
        assertSameElements(new int[] {2, 1, 4}, topKFrequent.apply(new int[] {1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 5, 6, 4}, 3));
    }

    private static void assertSameElements(int[] expected, int[] actual) {
        int[] sortedExpected = expected.clone();
        int[] sortedActual = actual.clone();
        Arrays.sort(sortedExpected);
        Arrays.sort(sortedActual);
        assertArrayEquals(sortedExpected, sortedActual);
    }
}
