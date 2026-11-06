package neetcode.arraysandhashing.groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagrams {

    public static final int ALPHABET_SIZE = 26;

    static class Key {
        private final char[] counts;
        private final int hash;

        public Key(char[] counts) {
            this.counts = counts;
            this.hash = Arrays.hashCode(counts);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return hash == key.hash && Arrays.equals(counts, key.counts);
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }

    // Groups by letter counts, without sorting; see the README for complexity.
    public List<List<String>> groupAnagrams(String[] strs) {
        return groupAnagramsWithStringAsKey(strs);
    }

    List<List<String>> groupAnagramsWithArrayAsKey(String[] strs) {
        final Map<Key, List<String>> groups = new HashMap<>();
        for (var str : strs) {
            final Key key = new Key(counts(str));
            groups.computeIfAbsent(key, _ -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(groups.values());
    }

    List<List<String>> groupAnagramsWithStringAsKey(String[] strs) {
        final Map<String, List<String>> groups = new HashMap<>();
        for (var str : strs) {
            final String key = new String(counts(str));
            groups.computeIfAbsent(key, _ -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(groups.values());
    }

    private char[] counts(String s) {
        char[] counts = new char[ALPHABET_SIZE];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            counts[index]++;
        }
        return counts;
    }
}
