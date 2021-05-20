---
layout: default
title: DataReader.java
parent: Chapter 2
nav_order: 1
---

# `DataReader.java`
At the very start of our program, we need a way to quickly read in our data from `reviews.txt` and `labels.txt`. To achieve this, we will create a class (or navigate to it) called `DataReader.java`. 

## 2.0.0 The Underlying Logic
To keep it simple for ourselves, we will use two `ArrayList<Strings>`, one for `reviews.txt` and one for `labels.txt`. We want to store a list of reviews, each element containing its own unique review. As we found in the previous section, our data is separated by *newline* characters. We can use this knowledge to iterate through a file, add everything we found before a new line into the ArrayList, then continue until we reach the end of a file.

To do this, we will use a `Scanner` object to read in from a `File` object. Finally, so we can view our data easily, we will create a function to print out a review and a label based on a given index.  I know this sounds complicated, but when you see the following code, it will start to make sense. 

## 2.0.1 The `try-catch` Block
Something your AP CS course may not have covered is the `try-catch` block. The `try-catch` block was created to perform a different action when an error occurs, instead of just stopping your program entirely. Let's view this as an example:
```java
int[] arr = new int[3];
arr[4] = 1; // the problem line
arr[2] = 2;
System.out.println(arr[2]);
// rest of our code
```
If we ran this code, Java will *throw an exception* (or error) when it hits line 2. To the keen eyed readers, you might realize that we are trying to access the 5th element in an array of size 3. Of course this won't work, and Java will return a `java.lang.ArrayIndexOutOfBoundsException`. Because Java stops executing at line 2, line 3 (and subsequent lines) is never run and we do not see any output.

Let's say we want to be able to catch this error and handle it a different way so that our program doesn't stop when it sees this error. We would then use the `try-catch` block:
```java
int[] arr = new int[3];
try {
	arr[4] = 1; // the problem line
	arr[2] = 2;
	System.out.println(arr[2]);
} catch (ArrayIndexOutOfBoundsException e) {
	System.out.println("There was an error adding things to the list! Skipping...");
}
// rest of our code
```
Now, instead of our code stopping when it sees the index error, Java will continue to execute everything in the `catch` part of the block (in our case line 7). If you want to learn more about Java's `try-catch` block, [click here](https://www.w3schools.com/java/java_try_catch.asp).

## 2.0.2 `File` and `Scanner`
Reading in data from a `File` isn't covered by the AP CS curriculum, that's why its important to cover it here. Reading from a file is incredibly similar to reading in user input from `Scanner`. If you covered the unit, you might recognize how to setup a scanner:
```java
import java.util.Scanner;
// ...
Scanner reader = new Scanner(System.in);
```
`Scanner`'s constructor that we are familiar with takes one argument: an `InputStream`. A stream is essentially a sequence of data elements made available over time. There's a source and a destination. The source could be from anywhere, a file, the internet, or system input (`System.in`). The destination is the `Scanner`, where we will process the flowing stream of data.

AP CS teaches us to use the `System.in` stream, which allows users to enter in information during runtime, but if we were to manually enter each review (keep in mind there are 25,000 reviews), it would take a long time. This is why we need to find a way to get a stream from a `File` instead of using our system's input.

Luckily, Java has the `File` object that does just that. To use it, we will:
```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// ...
try {
	File dataFile = new File("path/to/our/file.txt");
	Scanner fileReader = new Scanner(dataFile);
	// ... do our work
	fileReader.close();
} catch (FileNotFoundException e) {
	System.out.println("path/to/our/file.txt doesn't exist!");
	e.printStackTrace();
}
```
Lines 1-3: Import the objects we care about
Line 4: Create a `File` object that gets data from our file and streams it
Line 5: Create a `Scanner` object that streams data from our `File` object
Line 6: We can use `Scanner` methods like we did before, things like `.nextLine()` or `.nextInt()`
Line 7: When we are done, its good practice to close our stream
Lines 8-11: In the case that we don't find the file we specified in line 4, we will print it out and print the exact error. It is good practice to implement this to make it more user friendly.

With all these new topics covered, we can finally start making our `DataReader.java` class.

## 2.1.0 The Code
Now is the much anticipated (or dreaded) part, actually coding our `DataReader.java` class
## 2.1.1 `package` and `imports`
Let's start off with our imports and class signature. This is primarily for those who are starting from scratch and did not download a `.zip` file.
```java
package helper;

import java.io.File;
import java.io.FileNotFoundException;  
import java.util.ArrayList;  
import java.util.Scanner;

public class DataReader {

}
```
Line 1: This line denotes the fact that `DataReader.java` is in the `helper/` directory.
Line 3: Import Java's object for streaming in files. We will use this in conjunction with `Scanner` to read in files
Line 4: An error to throw in case we don't find a file at the given location
Line 5: `ArrayList` will be used to store our reviews and labels
Line 6: We will use the `Scanner` in conjunction with `File` to read in from our `.txt` files.

## 2.1.2 Instance variables
Now with the busy work done, we will create our instance variables. In this file, we only have 2:
```java
// imports hidden
public class DataReader {
	private ArrayList<String> reviews = new ArrayList<>();
	private ArrayList<String> labels = new ArrayList<>();
	
}
``` 
Line 3: Create an `ArrayList<String>` for our reviews and initialize it with an empty `ArrayList`
Line 4: Create an `ArrayList<String>` for our labels and initialize it with an empty `ArrayList`

## 2.1.3 Constructors
Lets make a default constructor and overloaded constructor! We will use the default constructor, but in the case we need to search for our data file elsewhere, we can do it with the overloaded constructor. Instead of doing all the grunt work in our constructor, we will use 2 methods to simplify our constructor.
```java
// imports hidden
public class DataReader {
	// variables hidden
	public DataReader() {
		this.readReviewFile("src/data/reviews.txt");
		this.readLabelFile("src/data/labels.txt");
	}
	
	public DataReader(String reviewFileName, String labelFileName) {
		this.readReviewFile(reviewFileName);
		this.readLabelFile(labelFileName);
	}
}
```
Line 4: Our default constructor. We use an absolute path to our data because we know exactly where our data files are located. **If your data files are located somewhere else then you must change the string to accurately reflect it**
Line 5-6: Use 2 methods called `readReviewFile` and `readLabelFile` that takes one `String` as an argument. These methods do all the hard work of reading the data and adding to our `ArrayList`s. We will implement these methods next. 
Line 9-12: Our overloaded constructor. We add this because in the case we want to test a location before making a permanent change, we can do so using this constructor. 

Since both constructors are practically identical, you *can* rewrite the default constructor as:
```java
public DataReader() {
	this("src/data/reviews.txt", "src/data/labels.txt");
}
```
This isn't mandatory, but it simplifies the code a bit.

## 2.1.4 Methods
The following methods will appear daunting, but if you keep reading, you should be able to understand what is happening.
### `readReviewFile` and `readLabelFile`
Next, we will implement our reading methods, `readReviewFile` and `readLabelFile`.
```java
// imports hidden
public class DataReader() {
	// instance variables & constructors hidden
	private void readReviewFile(String reviewFileName) {
		try {
			File reviewFile = new File(reviewFileName);
			Scanner fileReader = new Scanner(reviewFile);
			while (fileReader.hasNextLine()) {
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
}
```
Now I realize that the code looks daunting, but let's take a careful look at the `readReviewFile` method, and you will realize that it isn't that bad.
```java
private void readReviewFile(String reviewFileName){
   try {
	   File reviewFile = new File(reviewFileName);
	   Scanner fileReader = new Scanner(reviewFile);
	   while (fileReader.hasNextLine()) {
		   this.reviews.add(fileReader.nextLine());
	   }
	   fileReader.close();
	} catch (FileNotFoundException e) {
		System.out.println(reviewFileName + " doesn't exist!");
		e.printStackTrace();
	}
}
```
Line 1: We want this method `private` as we don't plan to let users explicitly call this function. It should be used by this class only so that way the end user doesn't mess with their data too much
Line 3: We create our `File` object that creates a stream from the file passed to this function as an argument
Line 4: We create our `Scanner` object. This is what we will use to read data from the `File` we created before
Line 5: This `while` loop helps us iterate through our entire stream so we can read it. `Scanner` has a built in function called `.hasNextLine()` which returns `true` if there is a line we can read and `false` if there isn't
Line 6: Here we add the `String` we get from `fileReader.nextLine()` to our reviews `ArrayList`
Line 8: We close our `Scanner` stream for safety purposes
Line 9-12: Catch any errors relating to a missing file and print out the fact that the file is missing.

We've seen this before earlier in this lesson, but one thing I want to call out specifically is the `while` loop so that we can better understand it. Let's do this with an example. We have a text file that we are going to iterate through:
```
Hello what is your name?
My name is Anish, how about yours?
My name is also Anish, how weird?
You're weird.
```
Now let's use a similar while loop to read through this.
```java
// File and Scanner objects hidden
while (fileReader.hasNextLine()) {
	System.out.println(fileReader.nextLine());
}
```
Now, let's create a table to trace this loop:

|Iteration #|hasNextLine()|fileReader.nextLine()|
|--|--|--|
|0|true|"Hello what is your name?"|
|1|true|"My name is Anish, how about yours?"|
|2|true|"My name is also Anish, how weird?"|
|3|false| "You're weird"|

The confusing part of this loop might be how does `hasNextLine()` magically become `false`? Well the trick is using it in conjunction with `nextLine()`. `nextLine()` not only returns the next line in the stream, but it also advances an imaginary cursor to the end of that line. That way, the next time `hasNextLine()` is called, it's from a different line than last. Let's try to simulate this. This would be our file at the first iteration:
```
Hello what is your name?
^ (our cursor starts here)
...
```
When we call `hasNextLine()`:
```
Hello what is your name?
^
My name is Anish, how about yours? (Check if the next line exists)
...
```
When we call `nextLine()`:
```
Hello what is your name?
                        ^ (returns everything behind the cursor)
My name is Anish, how about yours?
^ (after returning the line, it moves to the start of the next line)
...
```
When we call `hasNextLine()`:
```
...
My name is Anish, how about yours?
^
My name is also Anish, how weird? (This line exists, so return true)				
```
This iteration continues until we are finally out of possible lines that we can read. This is essentially how both `readReviewFile` and `readLabelFile` work, but there is one difference in the loop. In `readLabelFile`, we use `fileReader.nextLine().toUpperCase()` rather than just `fileReader.nextLine()`. This is to ensure that all the labels will be in the uppercase, so that we don't have to worry about mixed case when looking at our labels.

### `prettyPrintReview`
This is a helper function to easily view a review and its label.
```java
// imports hidden
public class DataReader() {
	// instance variables & constructors hidden
	private void readReviewFile(String reviewFileName) {...}
	private void readLabelFile(String labelFileName){...}
	
	public void prettyPrintReview(int index) {
		if (index < this.reviews.size()) {
			System.out.println(this.labels.get(index) + "\t:\t");
			try {
				System.out.println(this.reviews.get(index).substring(0, 80) + "...");
			} catch (IndexOutOfBoundsException e) {
				System.out.println(this.reviews.get(index);
			}
		}
	}
}
```
Line 7 - Our `prettyPrintReview` method signature. We want this to be `public` so that we can call it later in our `DataExploration.java` file and see its output.
Line 8 - Ensure that our index is within bounds of our `ArrayList` objects
Line 9 - Print out our label first (remember `this.labels` and `this.reviews` are `ArrayList` objects), then space out a divider (in our case the divider is a `:`) with tabs (`\t`)
Line 11 - Print out 80 characters of our review and add ellipsis (`...`) to denote theres more to the review
Line 12 - If there are less than 80 characters in a review, Java **will** throw an `IndexOutOfBoundsException`, so  we catch it here
Line 13 - We print out the whole review given that its less than 80 characters

### Getter functions
```java
// imports hidden
public class DataReader() {
	// instance variables & constructors hidden
	private void readReviewFile(String reviewFileName) {...}
	private void readLabelFile(String labelFileName){...}
	public void prettyPrintReview(int index) {...}

	public ArrayList<String> getReviews() {
		return this.reviews;
	}
	
	public ArrayList<String> getLabels() {
		return this.labels;
	}
	
	public int getNumReviews() {
		return this.reviews.size()
	}
	
	public int getNumLabels() {
		return this.labels.size()
	}
}
```
These functions are basic and should be self explanatory.

## 2.2.0 Running `DataReader.java`
All of the code here should be ran in `DataExploration.java`. You can delete the code after you're done with this section.
```java
import helper.DataReader;
import java.util.ArrayList;

public class DataExploration {
	public static void main(String[] args) {
		DataReader data = new DataReader();
		ArrayList<String> reviews = data.getReviews();
		ArrayList<String> labels = data.getLabels();
		
		System.out.println(reviews.size());
		System.out.println(reviews.size() == labels.size());
		System.out.println(data.getNumReviews() == data.getNumLabels());

		for (int i = 0; i < 10; i ++) {
			data.prettyPrintReview(i);
		}
	}
}
```
Line 1: Import our `DataReader.java` class we worked so hard on
Line 2: Import the `ArrayList` object
Line 6: Create a `DataReader` object
Line 7-8: Get our reviews and labels (respectively)
Line 10: Check how many reviews we have
Line 11: Check that the number of reviews is equal to the number of labels
Line 12: Check that the number of reviews and number of labels are equal, this time using getters we defined
Line 14-16: Pretty print 10 reviews.

With this code, you should get an output like the following:
```
25000
true
true
POSITIVE	:	bromwell high is a cartoon comedy . it ran at the same time as some other progra...
NEGATIVE	:	story of a man who has unnatural feelings for a pig . starts out with a opening ...
POSITIVE	:	homelessness  or houselessness as george carlin stated  has been an issue for ye...
NEGATIVE	:	airport    starts as a brand new luxury    plane is loaded up with valuable pain...
POSITIVE	:	brilliant over  acting by lesley ann warren . best dramatic hobo lady i have eve...
NEGATIVE	:	this film lacked something i couldn  t put my finger on at first charisma on the...
POSITIVE	:	this is easily the most underrated film inn the brooks cannon . sure  its flawed...
NEGATIVE	:	sorry everyone    i know this is supposed to be an  art  film   but wow  they sh...
POSITIVE	:	this is not the typical mel brooks film . it was much less slapstick than most o...
NEGATIVE	:	when i was little my parents took me along to the theater to see interiors . it ...
```
This simple test verified that our `DataReader.java` class works, and if you got this output you are ready to move onto the next lesson.

### Troubleshooting
If you don't get this output, check that your code is correct. The pretty printed reviews aren't as important as the number of reviews in total. If there aren't exactly 25,000 reviews, you need to check your code again. 

If you see output similar to:
```
src/data/reviews.txt doesn't exist!
src/data/labels.txt doesn't exist!
...
```
Then you need to pass a different file location to your `DataReader` object, which can be done like:
```java
// ... imports, class header, main function
DataReader data = new DataReader("path/to/reviews.txt", "path/to/labels.txt");
// ...
```
If none of these work, you can view my entire `DataReader.java` file [here](https://github.com/anishg24/JavaSentimentNetwork/blob/master/src/helper/DataReader.java). (It will look a bit different than what we did here)

