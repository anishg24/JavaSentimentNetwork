package helper;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.Map.Entry.comparingByValue;

public class Counter {
    final private Map<String, Integer> counts = new LinkedHashMap<>();

    public void add(String word) {
        counts.merge(word, 1, Integer::sum);
    }

    public int getCount(String word) {
        return counts.getOrDefault(word, 0);
    }

    public List<String> getMostCommon() {
        return counts.entrySet().stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Set<String> getWords() {
        return counts.keySet();
    }
}