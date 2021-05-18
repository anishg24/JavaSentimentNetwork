import helper.Counter;
import helper.DataReader;
import helper.RatioCounter;

import java.util.ArrayList;

public class DataExploration {
    public static void main(String[] args) {
        DataReader data = new DataReader();
        ArrayList<String> reviews = data.getReviews();
        ArrayList<String> labels = data.getLabels();

        Counter positiveCounts = new Counter();
        Counter negativeCounts = new Counter();
        Counter totalCounts = new Counter();

        for (int i = 0; i < labels.size(); i ++){
            if (labels.get(i).equals("POSITIVE")){
                for (String word: reviews.get(i).split(" ")){
                    positiveCounts.add(word);
                    totalCounts.add(word);
                }
            } else {
                for (String word: reviews.get(i).split(" ")){
                    negativeCounts.add(word);
                    totalCounts.add(word);
                }
            }
        }

        RatioCounter posNegRatios = new RatioCounter();
        for (String word: totalCounts.getMostCommon()){
            if (totalCounts.getCount(word) > 100)
                posNegRatios.add(word, Math.log(positiveCounts.getCount(word)/(double)negativeCounts.getCount(word)));
        }

        System.out.println("posNegRatios.getRatio(\"the\") = " + posNegRatios.getRatio("the"));
        System.out.println("posNegRatios.getRatio(\"stinker\") = " + posNegRatios.getRatio("amazing"));
    }
}
