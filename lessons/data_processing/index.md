---
layout: default
title: Chapter 2
nav_order: 3
has_children: true
---

# Chapter 2: Data Processing
If you downloaded `SentimentNetwork-Medium.zip` from Chapter 1, it is recommended that you look over `Dictionary.java` as it might provide clarity.
## 2.0.0 Exploring our data
This chapter is dedicated to playing with our data, finding correlations in it, finding anything special that we might be able to exploit and create a network with. But to do this, we should first figure out what data do we really have.

We have 2 files, `reviews.txt` and `labels.txt`. `reviews.txt` contains thousands of movie reviews, and `labels.txt` contains a *positive* or *negative* corresponding to each review. The reviews were hand **labelled** by humans as either positive or negative. Traditionally, data files like this wouldn't be stored in `.txt` files, as it would be too inconsistent. But, since our dataset is small (<1 GB), this shouldn't be an issue. 

Each review in `reviews.txt` is separated by a *newline* character. We know this because if we opened `reviews.txt` in Google Docs or some other text editing document, we'd see that every review is in its own "paragraph". The same goes for `labels.txt`. Because we know of the delimiter, we can create a table to represent our data:

|Index|Review|Label|
|--|--|--|
|0|this isn't the come...|positive|
|1|story of a man who ...|negative|
|2|i don't know who to...|negative|
|...|...|...|
|24999|this is one of the...|negative|

While we can't see the full story from this simple table, we do know that there is a clear relation between `reviews.txt` and `labels.txt`, and thats the index. The index of a given review helps us find the label for the review. We will use this idea in processing our data.
## 2.1.0 Why do we process data?
Computers don't know English. No, seriously. Just because they can have English characters displayed to us, sing us English songs, or show English movies, computers do not know English at all. What they do know really well however, is numbers. Whenever we type in something on our keyboard, we don't send a literal English character to the computer. We instead send a byte to the computer, a byte that represents a `char`. 

You may have learned this already, Java has a primitive data type called `char`, which can store both an integer as well as a singular character within single quotes.  
```java
char theLetterA = 65;  // Equivalent to 'A'
char theLetterB = 'B'; // Equivalent to 66
```
Because our computers don't know what English is, we need to figure out a way to represent our review in numbers. This representation needs to be constant throughout our project. We can easily achieve this via **tokenization**.

## 2.2.0 What is "Tokenization"?
Tokenization is assigning unique identifiers to a pieces of data. To understand how this fits our situation, let's tokenize the following phrase:
> Hello my name is Anish and my favorite thing to do is program

Now this review isn't what we would call our "piece of data" as its too broad. How often would someone say that exact phrase? Not very often, so feeding a representation of the review to our computer would be pointless. Let's take a step down then.

What if our "pieces of data" were words within a review? People often use the same words, so it would have value for us to feed a representation to our computer.

|Piece of Data|Unique Identifier|
|--|--|
|Hello|???|
|my|???|
|name|???|
|is|???|
|Anish|???|
|and|???|
|favorite|???|
|thing|???|
|to|???|
|do|???|
|program|???|

Notice how the second *my* and *is* don't show up again in the list. Since we are assigning **unique** identifiers, we don't want any potential repeats making a piece of data having multiple identifiers.

Now we need to figure out a representation for each word in a review, and the answer is more simple than you'd think. Let's just use the index of the word in the given review! That is a numerical representation that the computer would work with, and it would be unique to every word. 

|Piece of Data|Unique Identifier|
|--|--|
|Hello|0|
|my|1|
|name|2|
|is|3|
|Anish|4|
|and|5|
|favorite|6|
|thing|7|
|to|8|
|do|9|
|program|10|

The idea we've represented with the table above is typically called as a **Dictionary** or **Tokenizer**. Now if we were to tokenize our example and prepare it for the computer, we'd simply find the unique identifier of each word by looking it up in the dictionary. We'd do this for each word and put it in a vector:
```
Hello my name is Anish and my favorite thing to do is program
  0   1   2   3   4    5   1     6       7   8  9   3   10
```
This would yield the vector:
```
[0, 1, 2, 3, 4, 5, 1, 6, 7, 8, 9, 3, 10]
```
This is the computers' "English" now.

## 2.3.0 Closing Remarks
As you may have seen, there are several webpages grouped under Chapter 2. Take the time to read each one, and work through each one fully. If at any time you are stumped, I suggest you really think through the problem. If you still can't figure it out, you can see my full code [here](https://github.com/anishg24/JavaSentimentNetwork/tree/master/src). My code however was written using more advanced Java features, such as `streams`. Understanding `streams` is not difficult, but may appear daunting. 
