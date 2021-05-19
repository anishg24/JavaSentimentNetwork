---
layout: default
title: Chapter 1
nav_order: 2
---

# Chapter 1: Setting up your IDE

## 1.0.0 What is an IDE?
An IDE (**I**ntegrated **D**eveloper **E**nvironment) is crucial if you are pursuing programming as even a hobby. That being said, you need to install one. Whatever IDE you use is up to your personal preference, but the ones that I recommend are (ranked best to worst):

 1. [IntelliJ IDEA](https://www.jetbrains.com/idea/) (I use this one)
 2. [Visual Studio Code](https://code.visualstudio.com/)
 3. [Eclipse](https://www.eclipse.org/downloads/packages/)

If you don't have any of these installed, I highly recommend installing them unless you know and are very familiar with the IDE you work with instead. This tutorial will be written with **IntelliJ IDEA** in mind.

## 1.1.0 Starting from Scratch
In the case that you are starting from scratch and want the satisfaction of implementing everything yourself, then setup your project structure like the following:
```
src/
├── DataExploration.java
├── NetworkRunner.java
├── jblas-1.2.4.jar
├── data/
│   ├── labels.txt
│   └── reviews.txt
├── helper/
│   ├── Counter.java
│   ├── DataReader.java
│   ├── Dictionary.java
│   └── RatioCounter.java
└── networks/
    ├── EfficientSentimentNetwork.java
    └── SentimentNetwork.java
```
There will be files you need to download and are provided below:

* [`src/data/labels.txt`](https://github.com/anishg24/JavaSentimentNetwork/releases/download/datav1/labels.txt)
* [`src/data/reviews.txt`](https://github.com/anishg24/JavaSentimentNetwork/releases/download/datav1/reviews.txt)
* [`src/jblas-1.2.4.jar`](https://github.com/anishg24/JavaSentimentNetwork/releases/download/scratch/jblas-1.2.4.jar)

When you create Java class files in a subdirectory, you **need** to include the `package` keyword. For example, your `Counter.java` would look like this:
```java
package helper; // <-- We say 'package helper' because Counter.java is in the "helper" folder.

//... imports hidden for brevity

public class Counter {
	//...
}
```

When you are done creating and downloading all 11 files, you can move onto the next section in this chapter. Ignore section 1.1.1 as its for students not pursuing the scratch route, and go straight to section 1.2.0.

## 1.1.1 Starting with my code
This section is for students who aren't fully confident in their ability to turn tutorial to code. This is perfectly fine, and to honor them I have added `.zip` files that contain my code, from varying difficulty. (If the following aren't hyperlinked, I haven't adequately finished creating lessons for them yet)

1. [`SentimentClassification-Easy.zip`](https://github.com/anishg24/JavaSentimentNetwork/releases/download/zips/SentimentClassifier-Easy.zip)
	* The networks have been fully implemented **(skipping chapters 5 and 6)**
	* You will implement the classes we will use for data processing
		* The hardest thing you will do is sorting `Map` objects (and will be explained)
2. [`SentimentClassification-Medium.zip`](https://github.com/anishg24/JavaSentimentNetwork/releases/download/zips/SentimentClassifier-Medium.zip)
	* The data processing classes have been fully implemented **(skipping chapters 2, 3, and 4)**
	* You will implement the network classes
		* The hardest thing you will do is the `train` method, relying on heavy linear algebra (handled by the `jblas` library and will be explained in this tutorial)
3. [`SentimentClassification-Hard.zip`](https://github.com/anishg24/JavaSentimentNetwork/releases/download/zips/SentimentClassifier-Hard.zip)
	* Nothing has been implemented, but all the filler work (creating classes, instance variables, method signatures) has been done for you
	* You will implement all the logic from both data processing to creation of the network

**NONE** of the zip files require you doing any hard matrix commands. As much as I'd love to encourage you to create the training and testing functions for your Sentiment Networks, they are incredibly delicate. I will go over the functions throughout the course, but I will not require you to write it by yourself. Download any of the `.zip` files above (based on what you are interested in) and set it up correctly in your IDE.

## 1.2.0 Setting up jblas
jblas (**J**ava **BLAS**) is a java implementation of BLAS (**B**asic **L**inear **A**lgebra **S**ubprograms). It is incredibly important that we set up jblas **correctly** as it is responsible for all of the math that our network is going to be doing. Whether you started from scratch or you downloaded a `.zip` file from above, you **MUST** tell your IDE that you are using this library, so that when your code compiles, `jblas-1.2.4.jar` is compiled with it.

* [How to do this in IntelliJ IDEA](https://stackoverflow.com/a/1051705/13351776)
* [How to do this in Visual Studio Code](https://stackoverflow.com/a/54535301/13351776)
* [How to do this in Eclipse](https://www.edureka.co/community/4028/how-to-import-a-jar-file-in-eclipse)

If you aren't using any of the IDEs, then you should Google "*Importing external jar file in \<YOUR IDE\>*"

## 1.3.0 Testing our workspace
Whenever you setup a new environment, it's a good idea to test your dependencies to see if you're ready to start developing. Our only dependency is `jblas`, so we will test this really quickly in this section. You can copy and paste the following code in `DataExploration.java`, and **after you are done testing, delete it**.
```java
import org.jblas.DoubleMatrix;
import org.jblas.util.Random;

public class DataExploration {
	public static void main(String[] args) {
		Random.seed(1);
		DoubleMatrix a = DoubleMatrix.ones(2, 3);
		DoubleMatrix b = DoubleMatrix.randn(2, 3);
		System.out.println(a.add(b));
	}
}
```
After running this code, you should get the following output:
```
[2.561581, -0.091228, -0.118283; 0.391817, 0.375460, -0.658322]
```
If you get errors similar to `package org.jblas does not exist` or `cannot find symbol variable Random` or `cannot find symbol class DoubleMatrix`, then you have incorrectly setup jblas, go back to 1.2.0 and try to set it up correctly. If you aren't using IntelliJ IDEA, you might need to change the import statements.

If the output you received matches the one above, then you are ready to start developing!
