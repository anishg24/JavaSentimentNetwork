package helper;

import java.util.LinkedHashMap;
import java.util.Map;

public class RatioCounter extends Counter {
    final private Map<String, Double> counts = new LinkedHashMap<>();

    public void add(String word, double ratio) {
        counts.put(word, ratio);
    }

    public double getRatio(String word){
        return counts.getOrDefault(word, 0.);
    }
}
