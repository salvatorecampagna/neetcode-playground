package neetcode.slidingwindow.besttimetobuyandsellstock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BestTimeToBuyAndSellStockTest {

    private static final BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();

    static Stream<Named<ToIntFunction<int[]>>> implementations() {
        return Stream.of(
                Named.of("maxProfit", solution::maxProfit),
                Named.of("maxProfitSinglePass", solution::maxProfitSinglePass),
                Named.of("maxProfitNestedLoop", solution::maxProfitNestedLoop));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void bestBuyIsNotTheLowestPrice(ToIntFunction<int[]> maxProfit) {
        assertEquals(5, maxProfit.applyAsInt(new int[] {9, 3, 6, 4, 8, 2}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void fallingPricesGiveNoProfit(ToIntFunction<int[]> maxProfit) {
        assertEquals(0, maxProfit.applyAsInt(new int[] {12, 9, 6, 4, 1}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void risingPricesGiveTheLargestProfit(ToIntFunction<int[]> maxProfit) {
        assertEquals(9, maxProfit.applyAsInt(new int[] {1, 2, 4, 4, 10}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void flatPricesGiveNoProfit(ToIntFunction<int[]> maxProfit) {
        assertEquals(0, maxProfit.applyAsInt(new int[] {4, 4, 4, 4, 4}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void alternatingPrices(ToIntFunction<int[]> maxProfit) {
        assertEquals(1, maxProfit.applyAsInt(new int[] {12, 13, 12, 13, 12}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void bestTradeAtTheStart(ToIntFunction<int[]> maxProfit) {
        assertEquals(10, maxProfit.applyAsInt(new int[] {2, 12, 7, 8, 9}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void bestTradeAtTheEnd(ToIntFunction<int[]> maxProfit) {
        assertEquals(13, maxProfit.applyAsInt(new int[] {2, 7, 2, 2, 15}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void bestTradeInTheMiddle(ToIntFunction<int[]> maxProfit) {
        assertEquals(6, maxProfit.applyAsInt(new int[] {12, 10, 2, 8, 7}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleDay(ToIntFunction<int[]> maxProfit) {
        assertEquals(0, maxProfit.applyAsInt(new int[] {12}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoDaysFlat(ToIntFunction<int[]> maxProfit) {
        assertEquals(0, maxProfit.applyAsInt(new int[] {12, 12}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoDaysRising(ToIntFunction<int[]> maxProfit) {
        assertEquals(3, maxProfit.applyAsInt(new int[] {12, 15}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoDaysFalling(ToIntFunction<int[]> maxProfit) {
        assertEquals(0, maxProfit.applyAsInt(new int[] {12, 10}));
    }
}
