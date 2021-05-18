# Introduction
Welcome to this tutorial on how to create your own binary sentiment classifier network in Java! This project is more advanced than anything CollegeBoard will offer you, but if you have a true passion in programming, computational mathematics, or artificial intelligence, you should definitely consider completing this tutorial!

This tutorial will start off by teaching you how to process a dataset and see if we can figure out a way to quantitatively represent the rather qualitative data we are given. This won't be complex and simple for-loops will yield the results we are looking for. After the data processing step, we can move on to creating our binary classifier network, that we will train and test, then use it for inference. 

You can implement everything I've just said from scratch, but I have also provided code with several methods and functions missing that I will teach you how to implement.

# The Problem
When creating an AI for anything, there needs to be a problem that we plan to answer. The problem in this tutorial is **determining if a sentence has a positive or negative sentiment**. The first question I've been asked a lot while developing this tutorial is "What is sentiment?" According to Google is

> sen·ti·ment (noun) - a view of or attitude toward a situation or event; an opinion.

and our definition will follow the same. If I told you *"That movie was utter trash"*, would you say that is a positive or negative review? We know that it would be a negative thing to say, but how do we make the computer know that our review was negative? 

# Approaches to the Problem

## The Blacklist
The logical way that people would do and what CollegeBoard teaches you is to use a blacklist. But there are clear drawbacks to this method. Let's say we chose to use a list of words that we know are negative, and if a review contains the word, we know the review is negative.

|Word |
|--   |
|trash|
|sucks|
|...|
|stupid|

Now let's see if our review (*"That movie was utter trash"*) has any of our blacklisted words. It shares the word *trash*, meaning our review would be negative. What if I gave you the review *"That movie was stupid cool*? According to our blacklist that review would be negative, but using *stupid* in conjunction with *cool* typically means it's a positive thing to say. This is the biggest limitation of the blacklist method

## The Advanced Blacklist
This is a follow up to the previous method, except instead of just using one blacklist, we'd use two, one for each sentiment. Instead of just checking for one word, we'd create rules to determine if a review is positive or negative. The issue with this is that we cannot create enough rules. The English language is incredibly vast with millions of nuances that one or even a thousand developers couldn't fully program. Its not worth spending the decades of time to create all of the trillions of rules for this simple problem.

## The Classifier Network
This is where we bring in AI to solve our problem. Instead of creating trillions of rules or check thousands of strings for a blacklisted word, we can simply plug our review into a math equation that, when done computing, spits out a sentiment. Because AI is nothing but a mathematical approximation, it isn't correct 100% of the time, but if its correct > 50% of the time, then we have a viable solution to our problem. 

# Getting Started
The tutorial will continue in separate pages. If the section isn't hyperlinked, then it likely means I haven't finished it yet. Check back later if that is the case! 

0. [Setting up your IDE](https://anishg24.github.io/JavaSentimentNetwork/lessons/lesson-1)
1. Reading our data
2. Playing with our data
3. Processing our data
4. Creating `SentimentNetwork.java`
5. Creating `EfficientSentimentNetwork.java`
6. Running our network.

# Terminology
In the case I use words that you don't understand, you can come here to look at my definition.

> Model - synoymous with our classifier network

### Special Thanks
- [Udacity Deep Learning](https://github.com/udacity/deep-learning)
