package neetcode.arraysandhashing.twosum;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TwoSumTest {

    @FunctionalInterface
    interface TwoSumMethod {
        int[] apply(int[] nums, int target);
    }

    private static final TwoSum solution = new TwoSum();

    static Stream<Named<TwoSumMethod>> implementations() {
        return Stream.of(
                Named.of("twoSum", solution::twoSum),
                Named.of("twoSumNestedForLoops", solution::twoSumNestedForLoops),
                Named.of("twoSumWithHashMap", solution::twoSumWithHashMap),
                Named.of("twoSumWithHashMapOnePass", solution::twoSumWithHashMapOnePass));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(TwoSumMethod twoSum) {
        assertArrayEquals(new int[] {0, 1}, twoSum.apply(new int[] {3, 4, 5, 6}, 7));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(TwoSumMethod twoSum) {
        assertArrayEquals(new int[] {0, 2}, twoSum.apply(new int[] {4, 5, 6}, 10));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example3(TwoSumMethod twoSum) {
        assertArrayEquals(new int[] {0, 1}, twoSum.apply(new int[] {5, 5}, 10));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void unsortedInput(TwoSumMethod twoSum) {
        assertArrayEquals(new int[] {1, 3}, twoSum.apply(new int[] {2, 0, 4, 7, 2}, 7));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void invalidSameIndex(TwoSumMethod twoSum) {
        assertArrayEquals(new int[] {1, 2}, twoSum.apply(new int[] {1, 2, 2}, 4));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void invalidInputWithoutSolution(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {1, 10, 3, 4, 7}, 2));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void invalidEmptyArray(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {}, 10));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void invalidSingleElementArray(TwoSumMethod twoSum) {
        assertThrows(IllegalArgumentException.class, () -> twoSum.apply(new int[] {1}, 10));
    }
}
