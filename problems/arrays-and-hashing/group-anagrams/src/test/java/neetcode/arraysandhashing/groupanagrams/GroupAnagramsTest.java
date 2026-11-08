package neetcode.arraysandhashing.groupanagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GroupAnagramsTest {

    private static final GroupAnagrams solution = new GroupAnagrams();

    static Stream<Named<Function<String[], List<List<String>>>>> implementations() {
        return Stream.of(
                Named.of("groupAnagrams", solution::groupAnagrams),
                Named.of("groupAnagramsWithArrayAsKey", solution::groupAnagramsWithArrayAsKey),
                Named.of("groupAnagramsWithStringAsKey", solution::groupAnagramsWithStringAsKey));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void wordsInSeveralGroups(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(
                List.of(List.of("listen", "silent", "enlist"), List.of("google", "gogole"), List.of("banana")),
                groupAnagrams.apply(new String[] {"listen", "silent", "enlist", "google", "banana", "gogole"}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleOneLetterString(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(List.of(List.of("q")), groupAnagrams.apply(new String[] {"q"}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleEmptyString(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(List.of(List.of("")), groupAnagrams.apply(new String[] {""}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void sameLettersDifferentCounts(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(
                List.of(List.of("aaa"), List.of("aab", "aba", "baa"), List.of("abb", "bab")),
                groupAnagrams.apply(new String[] {"aaa", "aab", "abb", "aba", "baa", "bab"}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void largerStringsDifferentGroupsByCounts(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(
                List.of(List.of("aaabbb", "bababa", "bbbaaa"), List.of("aabbaab", "abababa"), List.of("ababbb")),
                groupAnagrams.apply(new String[] {"abababa", "bababa", "bbbaaa", "aaabbb", "ababbb", "aabbaab"}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void duplicateEmptyStrings(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(
                List.of(List.of("", ""), List.of("a", "a"), List.of("b", "b")),
                groupAnagrams.apply(new String[] {"a", "", "b", "a", "b", ""}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allDifferentGroups(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(
                List.of(List.of("a"), List.of("aa"), List.of("aaa"), List.of("b"), List.of("bb"), List.of("bbb")),
                groupAnagrams.apply(new String[] {"a", "aa", "aaa", "b", "bb", "bbb"}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void sameLettersSwappedCounts(Function<String[], List<List<String>>> groupAnagrams) {
        assertSameGroups(
                List.of(List.of("aaaab"), List.of("bbbba")),
                groupAnagrams.apply(new String[] {"aaaab", "bbbba"}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void hashCollisionIsNotAGroup(Function<String[], List<List<String>>> groupAnagrams) {
        String z31 = "z".repeat(31);
        assertSameGroups(
                List.of(List.of("y"), List.of(z31)),
                groupAnagrams.apply(new String[] {"y", z31}));
    }

    private static void assertSameGroups(List<List<String>> expected, List<List<String>> actual) {
        assertEquals(normalize(expected), normalize(actual));
    }

    private static List<List<String>> normalize(List<List<String>> groups) {
        List<List<String>> normalized = new ArrayList<>();
        for (List<String> group : groups) {
            List<String> sorted = new ArrayList<>(group);
            sorted.sort(Comparator.naturalOrder());
            normalized.add(sorted);
        }
        normalized.sort(Comparator.comparing(group -> group.getFirst()));
        return normalized;
    }
}
