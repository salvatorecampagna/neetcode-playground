package neetcode.twopointers.containerwithmostwater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ContainerWithMostWaterTest {

    private static final ContainerWithMostWater solution = new ContainerWithMostWater();

    static Stream<Named<ToIntFunction<int[]>>> implementations() {
        return Stream.of(
                Named.of("maxArea", solution::maxArea),
                Named.of("maxAreaTwoPointers", solution::maxAreaTwoPointers),
                Named.of("maxAreaNestedLoops", solution::maxAreaNestedLoops));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(ToIntFunction<int[]> maxArea) {
        assertEquals(36, maxArea.applyAsInt(new int[] {1, 7, 2, 5, 4, 7, 3, 6}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(ToIntFunction<int[]> maxArea) {
        assertEquals(4, maxArea.applyAsInt(new int[] {2, 2, 2}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void outerBarsHoldTheMost(ToIntFunction<int[]> maxArea) {
        assertEquals(35, maxArea.applyAsInt(new int[] {8, 7, 2, 5, 4, 7, 3, 5}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void descendingHeights(ToIntFunction<int[]> maxArea) {
        assertEquals(12, maxArea.applyAsInt(new int[] {7, 6, 5, 4, 3, 2}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void ascendingHeights(ToIntFunction<int[]> maxArea) {
        assertEquals(9, maxArea.applyAsInt(new int[] {3, 4, 5, 6}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allZeroHeightsHoldNothing(ToIntFunction<int[]> maxArea) {
        assertEquals(0, maxArea.applyAsInt(new int[] {0, 0, 0, 0}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoBars(ToIntFunction<int[]> maxArea) {
        assertEquals(3, maxArea.applyAsInt(new int[] {3, 4}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void tallerInnerBarBeatsTheShortOuterOne(ToIntFunction<int[]> maxArea) {
        assertEquals(35, maxArea.applyAsInt(new int[]{1, 10, 2, 8, 6, 3, 7}));
    }

    @Test
    void matchesNestedLoopsOnSmallRandomInputs() {
        final Random random = new Random(42);
        int attempts = 1000;
        for (int j = 0; j < attempts; j++) {
            int n = random.nextInt(2, 50);
            int[] heights = new int[n];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = random.nextInt(0, 10 + 1);
            }
            assertEquals(solution.maxAreaNestedLoops(heights), solution.maxAreaTwoPointers(heights),
                    () -> Arrays.toString(heights));
        }
    }

    @Test
    void matchesNestedLoopsOnLargerRandomInput() {
        long seed = System.nanoTime();
        final Random random = new Random(seed);
        int n = random.nextInt(2, 1_000 + 1);
        int[] heights = new int[n];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = random.nextInt(0, 10_000 + 1);
        }
        assertEquals(solution.maxAreaNestedLoops(heights), solution.maxAreaTwoPointers(heights), "failed with seed = " + seed);
    }
}
