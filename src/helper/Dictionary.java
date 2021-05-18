package helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Dictionary {
    final private Map<String, Integer> word2Index = new HashMap<>();
    final private Map<Integer, String> index2Word = new HashMap<>();

    public Dictionary(Set<String> vocabulary){
        AtomicInteger index = new AtomicInteger(0);
        vocabulary.forEach((word) -> {
            word2Index.put(word, index.get());
            index2Word.put(index.getAndIncrement(), word);
        });
    }

    public int getIndex(String word) {
        return word2Index.getOrDefault(word, -1);
    }

    public String getWord(int index) {
        return index2Word.getOrDefault(index, null);
    }

    public Map<String, Integer> getWord2Index() {
        return word2Index;
    }

    public Map<Integer, String> getIndex2Word() {
        return index2Word;
    }

    public int getWord2IndexSize() {
        return word2Index.size();
    }

    public int getIndex2WordSize() {
        return index2Word.size();
    }
}
