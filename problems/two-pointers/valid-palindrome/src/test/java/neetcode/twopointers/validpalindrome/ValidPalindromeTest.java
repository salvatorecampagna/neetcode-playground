package neetcode.twopointers.validpalindrome;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ValidPalindromeTest {

    private static final ValidPalindrome solution = new ValidPalindrome();

    static Stream<Named<Predicate<String>>> implementations() {
        return Stream.of(
                Named.of("isPalindrome", solution::isPalindrome));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void sentenceWithMixedCaseAndPunctuation(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("Step on no pets!"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void wordsThatDoNotMirror(Predicate<String> isPalindrome) {
        assertFalse(isPalindrome.test("rats live on"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleLowercaseLetter(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("a"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleUppercaseLetter(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("A"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void emptyString(Predicate<String> isPalindrome) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> isPalindrome.test(""));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleWordMixedCase(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("ABBAabba"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void lowercaseEvenLen(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("*- ---aa- -"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void lowercaseOddLen(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("-- *--aba- -"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allNonAlphanumericChars(Predicate<String> isPalindrome) {
        assertTrue(isPalindrome.test("     --- +) ?"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void mismatchInTheMiddle(Predicate<String> isPalindrome) {
        assertFalse(isPalindrome.test("ab, c? d ba"));
    }
}
