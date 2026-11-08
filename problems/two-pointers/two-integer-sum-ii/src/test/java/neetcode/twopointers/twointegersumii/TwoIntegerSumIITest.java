package neetcode.twopointers.twointegersumii;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TwoIntegerSumIITest {

    @FunctionalInterface
    interface TwoSumMethod {
        int[] apply(int[] numbers, int target);
    }

    private static final TwoIntegerSumII solution = new TwoIntegerSumII();

    static Stream<Named<TwoSumMethod>> implementations() {
        return Stream.of(
                Named.of("twoSum", solution::twoSum),
                Named.of("twoSumTwoPointers", solution::twoSumTwoPointers),
                Named.of("twoSumNestedForLoops", solution::twoSumNestedForLoops)
        );
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(TwoSumMethod twoSum) {
        assertArrayEquals(new int[] {1, 2}, twoSum.apply(new int[] {1, 2, 3, 4}, 3));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsEmptyArray(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {}, 0));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsSingleElement(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {1}, 0));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsInputWithoutSolution(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {1, 2, 3}, 10));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsPositiveTargetWithAllNegativeValues(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {-1, -2, -3}, 3));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void pairAtTheStart(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{1, 2}, twoSum.apply(new int[] {-1, 3, 4, 7, 8}, 2));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void pairAtTheEnd(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{4, 5}, twoSum.apply(new int[] {-1, 3, 4, 7, 8}, 15));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void pairInTheMiddle(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{3, 4}, twoSum.apply(new int[] {-1, 2, 4, 7, 8}, 11));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoEqualElements(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{1, 2}, twoSum.apply(new int[] {1, 1}, 2));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void pairAtBothEnds(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{1, 5}, twoSum.apply(new int[] {-1, 2, 4, 7, 8}, 7));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void equalAdjacentValues(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{2, 3}, twoSum.apply(new int[] {1, 3, 3, 8}, 6));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void negativeTarget(TwoSumMethod twoSum) {
        assertArrayEquals(new int[]{1, 2}, twoSum.apply(new int[] {-5, -2, 1, 4}, -7));
    }
}
