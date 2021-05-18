package networks;

import helper.Dictionary;
import org.jblas.DoubleMatrix;
import org.jblas.MatrixFunctions;
import org.jblas.util.Random;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EfficientSentimentNetwork {
    private final Dictionary dict;
    private final int inputNodes;
    private final int outputNodes;
    private final int hiddenNodes;
    private final double learningRate;

    private DoubleMatrix layer_1;
    private DoubleMatrix weights_0_1;
    private DoubleMatrix weights_1_2;

    public EfficientSentimentNetwork(Dictionary dict){
        Random.seed(1);
        this.dict = dict;
        this.inputNodes = this.dict.getWord2IndexSize();
        this.outputNodes = 1;
        this.hiddenNodes = 10;
        this.learningRate = 0.1;

        this.initializeNetwork();
    }

    private void initializeNetwork() {
        this.weights_0_1 = DoubleMatrix.zeros(this.inputNodes, this.hiddenNodes);
        this.weights_1_2 = DoubleMatrix.randn(this.hiddenNodes, this.outputNodes);
        this.layer_1 = DoubleMatrix.zeros(1, this.hiddenNodes);
    }

    public int getTargetForLabel(String label) {
        if (label.equals("POSITIVE")) return 1;
        return 0;
    }

    public DoubleMatrix sigmoid(DoubleMatrix x) {
        return MatrixFunctions.exp(x.neg()).add(1.).rdiv(1.);
    }

    public DoubleMatrix sigmoidOutput2Derivative(DoubleMatrix output){
        return output.mmul(output.rsub(1));
    }

    public void train(ArrayList<String> trainingReviewsRaw, ArrayList<String> trainingLabels) {
        ArrayList<Set<Integer>> trainingReviews = new ArrayList<>();
        for (String review: trainingReviewsRaw) {
            Set<Integer> indices = new HashSet<>();
            for (String word: review.split(" ")) {
                int index = dict.getIndex(word);
                if (index > 0) indices.add(index);
            }
            trainingReviews.add(indices);
        }

        assert(trainingReviews.size() == trainingLabels.size());

        int correct = 0;
        LocalTime start = LocalTime.now();

        for(int i = 0; i < trainingReviews.size(); i ++) {
            Set<Integer> review = trainingReviews.get(i);
            String label = trainingLabels.get(i);

            this.layer_1 = DoubleMatrix.zeros(1, this.hiddenNodes);
            for (int index: review) {
                this.layer_1 = this.layer_1.add(this.weights_0_1.getRow(index));
            }

            DoubleMatrix layer_2 = this.sigmoid(this.layer_1.mmul(this.weights_1_2));

            DoubleMatrix layer_2_error = layer_2.sub(this.getTargetForLabel(label));
            DoubleMatrix layer_2_delta = layer_2_error.mmul(this.sigmoidOutput2Derivative(layer_2));

            DoubleMatrix layer_1_error = layer_2_delta.mmul(this.weights_1_2.transpose());
            DoubleMatrix layer_1_delta = layer_1_error.dup();

            this.weights_1_2 = this.weights_1_2.sub(this.layer_1.transpose().mmul(layer_2_delta).mmul(this.learningRate));

            for (int index: review) {
                this.weights_0_1.putRow(index, this.weights_0_1.getRow(index).sub(layer_1_delta.getRow(0).mmul(this.learningRate)));
            }

            double prediction = layer_2.get(0, 0);

            if (prediction >= 0.5 && label.equals("POSITIVE") || prediction < 0.5 && label.equals("NEGATIVE"))
                correct += 1;

            long elapsedTime = start.until(LocalTime.now(), ChronoUnit.SECONDS);
            double reviewsPerSec;
            if (elapsedTime > 0) reviewsPerSec = i / (double)elapsedTime;
            else reviewsPerSec = 0.;

            System.out.printf("\rProgress: %f Speed(reviews/sec): %f #Correct: %d #Trained: %d Training Accuracy: %f",
                    100 * i/(double)(trainingLabels.size()),
                    reviewsPerSec,
                    correct,
                    i + 1,
                    correct * 100 /(double)(i+1));

            if (i % 2500 == 0) System.out.println();
        }
    }

    public void test(ArrayList<String> testingReviews, ArrayList<String> testingLabels) {
        assert(testingReviews.size() == testingLabels.size());

        int correct = 0;
        LocalTime start = LocalTime.now();

        for (int i = 0; i < testingReviews.size(); i ++) {
            double prediction = this.predict(testingReviews.get(i));
            String label = testingLabels.get(i);
            if ((prediction >= 0.5 && label.equals("POSITIVE")) || (prediction < 0.5 && label.equals("NEGATIVE")))
                correct += 1;

            long elapsedTime = start.until(LocalTime.now(), ChronoUnit.SECONDS);
            double reviewsPerSec;
            if (elapsedTime > 0) reviewsPerSec = i / (double)elapsedTime;
            else reviewsPerSec = 0;

            System.out.printf("\rProgress: %f Speed(reviews/sec): %f #Correct: %d #Tested: %d Testing Accuracy: %f",
                    100 * i/(double)(testingReviews.size()),
                    reviewsPerSec,
                    correct,
                    i + 1,
                    correct * 100 /(double)(i+1));
        }
    }

    public double predict(String review) {

        this.layer_1 = DoubleMatrix.zeros(1, this.hiddenNodes);
        Set<Integer> uniqueIndices = new HashSet<>();
        for (String word: review.split(" ")) {
            int index = this.dict.getIndex(word);
            if (index > 0) uniqueIndices.add(index);
        }

        for (Integer index: uniqueIndices) {
            this.layer_1 = this.layer_1.add(this.weights_0_1.getRow(index));
        }

        DoubleMatrix layer_2 = this.sigmoid(this.layer_1.mmul(this.weights_1_2));

        return layer_2.get(0, 0);
    }
}
