package neetcode.arraysandhashing.containsduplicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ContainsDuplicateTest {

    private static final ContainsDuplicate solution = new ContainsDuplicate();

    static Stream<Named<Predicate<int[]>>> implementations() {
        return Stream.of(
                Named.of("hasDuplicate", solution::hasDuplicate),
                Named.of("hasDuplicateBySorting", solution::hasDuplicateBySorting),
                Named.of("hasDuplicateBySortingAndCopying", solution::hasDuplicateBySortingAndCopying));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(Predicate<int[]> hasDuplicate) {
        assertTrue(hasDuplicate.test(new int[] {1, 2, 3, 3}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(Predicate<int[]> hasDuplicate) {
        assertFalse(hasDuplicate.test(new int[] {1, 2, 3, 4}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void duplicateAtOppositeEnds(Predicate<int[]> hasDuplicate) {
        assertTrue(hasDuplicate.test(new int[] {7, 1, 2, 3, 7}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allEqual(Predicate<int[]> hasDuplicate) {
        assertTrue(hasDuplicate.test(new int[] {5, 5, 5, 5}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleElement(Predicate<int[]> hasDuplicate) {
        assertFalse(hasDuplicate.test(new int[] {42}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void empty(Predicate<int[]> hasDuplicate) {
        assertFalse(hasDuplicate.test(new int[] {}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void negativeNumbers(Predicate<int[]> hasDuplicate) {
        assertTrue(hasDuplicate.test(new int[] {-1, 0, 1, -1}));
        assertFalse(hasDuplicate.test(new int[] {-3, -2, -1, 0}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void extremeValues(Predicate<int[]> hasDuplicate) {
        assertTrue(hasDuplicate.test(new int[] {-1_000_000_000, 0, -1_000_000_000}));
        assertFalse(hasDuplicate.test(new int[] {-1_000_000_000, 1_000_000_000}));
    }

    @Test
    void arrayIsSortedHasNoDuplicate() {
        int[] a = new int[] {1, 10, 7, 4, 5, 8, 9, 12, 3, 0};
        int[] b = a.clone();
        Arrays.sort(b);
        assertFalse(solution.hasDuplicateBySorting(a));
        assertArrayEquals(b, a);
    }

    @Test
    void arrayIsSortedHasDuplicate() {
        int[] a = new int[] {1, 10, 7, 4, 5, 4, 9, 1, 3, 0};
        int[] b = a.clone();
        Arrays.sort(b);
        assertTrue(solution.hasDuplicateBySorting(a));
        assertArrayEquals(b, a);
    }

    @Test
    void preserveInputArray() {
        int[] a = new int[] {1, 10, 3, 4, 7, 4, 1};
        int[] b = a.clone();
        assertTrue(solution.hasDuplicateBySortingAndCopying(a));
        assertArrayEquals(b, a);
    }
}
