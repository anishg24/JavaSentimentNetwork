package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    private final ArrayList<String> reviews;
    private final ArrayList<String> labels;

    public DataReader() {
        reviews = new ArrayList<>();
        labels = new ArrayList<>();
        this.readReviewFile("src/data/reviews.txt");
        this.readLabelFile("src/data/labels.txt");
    }

    public DataReader(String reviewFileName, String labelFileName) {
        reviews = new ArrayList<>();
        labels = new ArrayList<>();
        this.readReviewFile(reviewFileName);
        this.readLabelFile(labelFileName);
    }

    private void readReviewFile(String reviewFileName){
        try {
            File reviewFile = new File(reviewFileName);
            Scanner fileReader = new Scanner(reviewFile);
            while (fileReader.hasNextLine()){
                this.reviews.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(reviewFileName + " doesn't exist!");
            e.printStackTrace();
        }
    }

    private void readLabelFile(String labelFileName){
        try {
            File labelFile = new File(labelFileName);
            Scanner fileReader = new Scanner(labelFile);
            while (fileReader.hasNextLine()){
                this.labels.add(fileReader.nextLine().toUpperCase());
            }
            fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println(labelFileName + " doesn't exist!");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getReviews() {
        return this.reviews;
    }

    public ArrayList<String> getLabels() {
        return this.labels;
    }

    public int getNumReviews() {
        return this.reviews.size();
    }

    public int getNumLabels() {
        return this.labels.size();
    }

    public void prettyPrintReview(int index){
        System.out.print(this.labels.get(index) + "\t:\t");
        try {
            System.out.println(this.reviews.get(index).substring(0, 80) + "...");
        } catch (IndexOutOfBoundsException e){
            System.out.println(this.reviews.get(index));
        }
    }
}
