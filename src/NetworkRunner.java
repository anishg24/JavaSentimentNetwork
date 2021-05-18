import helper.Counter;
import helper.DataReader;
import helper.Dictionary;
import networks.EfficientSentimentNetwork;

import java.util.ArrayList;
import java.util.Scanner;

public class NetworkRunner {
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

        Dictionary dict = new Dictionary(totalCounts.getWords());
        EfficientSentimentNetwork network = new EfficientSentimentNetwork(dict);
        network.test(reviews, labels);
        System.out.println();

        Scanner reader = new Scanner(System.in);
        System.out.println("Type something and see what the computer thinks!");

        while (true) {
            System.out.print("> ");
            String review = reader.nextLine();

            double prediction = network.predict(review);
            String result;
            if (prediction > 0.5) result = "POSITIVE";
            else result = "NEGATIVE";

            System.out.println("The computer saw this as " + result + ". (" + prediction + ")");
        }
    }
}
