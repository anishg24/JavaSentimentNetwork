---
layout: default
title: Counter.java
parent: Chapter 2
nav_order: 2
---
# `Counter.java`
Our network is going to be based on words that have either a positive or negative connotation. But how can we quantitatively represent *connotation*? This is where a counter would come in handy, so let's create one! You'll see the importance of `Counter.java` later in this section when we explore our data with it.
## 2.0.0 The Underlying Logic
Most of the processing to convert a review into a series of words can be easily achieved and implemented in the `DataExploration.java` file (it's essentially a runner file as far as we care), so all of that work **won't** show up in our `Counter.java` class.

What we do care about is how we store this data. We need to store it so that for every word, there is a corresponding counter. Let's take this example review:

> This movie is really funny, really dumb, and really long. But, this is one of my favorite movies!

Now, lets create a counter that stores a word and the number of times that word occurred:

|Word|Count|
|--|--|
|this|2|
|movie|1|
|is|2|
|really|3|
|funny|1|
|dumb|1|
|and|1|
|long|1|
|but|1|
|one|1|
|of|1|
|my|1|
|favorite|1|
|movies!|1|

It's that simple! Just count the number of time a word occurs in a review and store it neatly. A few things to notice here, nothing is capitalized and *movie* only comes up once. These choices are intentional. 

We should lowercase all the words to cover for any edge cases, where a review might have awkward capitalization:

> this MovIE iS rEaLly funnY, realLy dUMb, ANd rEAlLY LONG. but, tHis IS oNE Of MY FaVOritE mOviES!

or

> THIS MOVIE IS REALLY FUNNY, REALLY DUMB, AND REALLY LONG. BUT, THIS IS ONE OF MY FAVORITE MOVIES!

While the former case is unlikely to really happen (keep in mind these are genuine reviews written by humans), the latter case shows us that regardless of capitalization, the general sentiment is the same, so it wouldn't hurt us to lowercase everything to keep things consistent.

You might say that some cases the capitalization matters, when you capitalize a single word for emphasis, and while that may be true, the amount of times that actually occurs and impacts the corresponding label is negligible, so there's no point wasting the computational resources to actually figure those cases out.

## 2.0.1 `Map`

So likely the biggest question you have is *"How do I create the relationship between the word and the frequency of those words?"*

This is where Java's `Map` object comes in. A `Map` is a *data structure* that stores key-value pairs. Let's take an example:

|Key|Value|
|--|--|
|"password"|"s00p3rSecr3tPa55w0rD!"|
|"age"|18|
|404|"Not Found"|
|"Miles Walked"|18|

Now tell me the value of "age". (It's 18)

Tell me the value of "password". (It's "s00p3rSecr3tPa55w0rD!")

For every **key** there needs to be a corresponding **value**. What I asked you to find were keys, and what you told me were values. There can be duplicate values, but there can **never** be duplicate keys. Also notice that the data type for each key-value pair doesn't need to be the same. A `String` could be the key, and an `int` could be the value (or vice-versa). But, when you declare your `Map` object, you tell the data types of the pair, and are expected to stick to it (like you would with an `ArrayList`)

Now a `Map` in Java is an **interface**. An interface is essentially the building blocks of a class file. An interface has all the method signatures and class variables, but it doesn't have the actual code for it to work. 

Think of it like this, for a car to be a car, we can say it needs 4 wheels and an engine. How these wheels are connected to the engine, what the engine runs on, how many doors it has, or other nuances are up to manufacturers to add. From a Tesla to a Toyota Corolla, the car has at the very least, 4 wheels and an engine.

This means we **can't** do something like

```java
Map<String, Integer> counts = new Map<>();
```

because there aren't any constructors defined for interfaces. Instead, we will have to use another variant of `Map` provided by Java.

## 2.0.2 `HashMap`

The `HashMap` is one of Java's variant of the `Map` interface. A `HashMap` is a great basic implementation of a `Map`, as it does exactly what we want, to store a key-value pair, at blazing speeds. One thing about the `HashMap` (and why we aren't using it) is that it doesn't provide an order to the data. 

You're already familiar with ordered data structures, the main example being arrays and `ArrayList`. You can index these data structures, but with `HashMap`, this is no longer the case. While this sacrifice is acceptable because we access data with keys in a `Map` rather than indexes, it's better to maintain some sort of order. This is why we will use a `LinkedHashMap` for `Counter.java`.


## 2.0.3 `LinkedHashMap`

The `LinkedHashMap` is a child of `HashMap`, and behaves pretty similar except for one key difference: a `LinkedHashMap` keeps the insertion order stored. This sort of order will provide us some benefits in functions defined below. This addition of order however make the `LinkedHashMap` slightly slower than `HashMap`, and with a bigger memory footprint. Both of these won't be in the slightest bit noticeable, but this is something to keep in mind.

Because `LinkedHashMap` is a child of `HashMap`, all the functions provided by `HashMap` are used in `LinkedHashMap`.

To learn more about the differences between these maps, [click here](https://www.javatpoint.com/linkedhashmap-vs-hashmap-in-java#:~:text=The%20Major%20Difference%20between%20the,the%20ordering%20of%20the%20elements.&text=The%20HashMap%20and%20LinkedHashMap%20both,class%20and%20implements%20Map%20interface.).

To learn how to **use** a `LinkedHashMap`, [click here](https://www.geeksforgeeks.org/linkedhashmap-class-java-examples/).

## 2.0.4 `Set`

For now, we will think of a `Set` as an array that **can't** contain duplicate elements. For example, if we had an `ArrayList` containing the following:
```
[1, 2, 2, 3, 3, 3, 4, 4, 4, 4]
```

and turned it into a `Set`, we would get the following:

```
[1, 2, 3, 4]
```

## 2.1.0 Instructions

Implement the class based off the instructions below.

## 2.1.1 `Counter.java`

**Instance Variables:**

* Create a private instance variable `counts` of type `Map<String, Integer>` and initialize it to an empty `LinkedHashMap<>()`

**Instance Methods:**

* Create a public `void` method called `add` that takes a key of type `String` as an argument. This method should increment the value in `counts` corresponding to the key.
* Create a public `int` method called `getCount` that takes a key of type `String` as an argument. This method should return the number of times that word is counted in `counts` or 0 if that word doesn't exist in `counts` (Hint: Use [`.getOrDefault()`](https://www.geeksforgeeks.org/hashmap-getordefaultkey-defaultvalue-method-in-java-with-examples/))
* Create a public `List<String>` method called `getMostCommon` that takes **no** arguments. The method should return a **list of words** (`ArrayList`, not a `Map`) sorted from highest count to lowest count provided from `counts`.
* Create a public `Set<String` method called `getWords` that takes **no** arguments. The method should return the words in the key of `counts` (Hint: Use [`.keySet()`](https://www.geeksforgeeks.org/hashmap-keyset-method-in-java/))

## 2.1.2 `DataExploration.java`

Hopefully you have the `reviews` and `labels` variables from the previous chapter. If not, then please declare and initialize these variables as these will be used. The `System.out.println()` calls and the `for` loop from the previous chapter aren't needed anymore, you can safely comment or delete them.

**Main Function:**

* Create 3 counters, `positiveCounts`, `negativeCounts`, `totalCounts`
* Using a for loop, iterate through each review in `reviews`:
	* If the review is "POSITIVE", then:
		* Using an enhanced for loop, access each word in the positive review (Hint: Use [`.split(" ")`](https://www.geeksforgeeks.org/split-string-java-examples/))
		* Add every word to `positiveCounts` and `totalCounts`
	
	* If the review is"NEGATIVE", then:
		* Using an enhanced for loop, access each word in the negative review (Hint: Use [`.split(" ")`](https://www.geeksforgeeks.org/split-string-java-examples/))
		* Add every word to `negativeCounts` and `totalCounts`
* Print the output of `getMostCommon()` for all 3 counters and notice any similarities or differences.

## 2.2.0 The Code

Phew that was a lot of background information, but the benefit of thoroughly understanding that is that the class is incredibly simple to write, despite how complex the instructions seem.

## 2.2.1 `package` and `imports`

As usual, we will start off with our imports and class signature:
```java
package helper;

import java.util.*;

public class Counter {

}
```
Line 1: This line denotes the fact that `Counter.java` is in the `helper/` directory

Line 3: Import everything from the `java.util` library. This includes the data structures we are interested in, like `Map`, `ArrayList`, and `Set`.

Line 5: Standard class signature.

## 2.2.2 Instance variables
```java
// imports hidden
public class Counter {
	private Map<String, Integer> counts = new LinkedHashMap<>();
}
```

Line 3: The only instance variable we will have, using the format for declaring and initializing an empty `Map`. You can also make this variable `final`, but that doesn't matter too much. Because we are initializing our instance variable right here, we do not need a constructor. 

## 2.2.3 Methods

This is the meat of our `Counter.java` class.

### `getCount`
```java
// imports hidden
public class Counter {
	// instance variables hidden
	public int getCount(String word) {
		return counts.getOrDefault(word, 0);
	}
}
```

Line 4: Our method signature

Line 5: `.getOrDefault(key, default)` is a method provided by `LinkedHashMap`, which allows us to get a value from a key, and if it is not found, return a default value. In this case, we are trying to get the value of the argument `word`, and if it doesn't exist that means we need to add it, so we will have a default value of 0 (which will become 1)

We knocked out this method first because we will use it in two other methods for this class.


### `add`
```java
// imports hidden 
public class Counter {
	// instance variables hidden
	public void add(String word) {
		counts.put(word, this.getCount(word) + 1);
	}
	
	public int getCount(String word) {...}
}
```

Line 4: Our method signature

Line 5: `.put(key, value)` is yet another method provided by `LinkedHashMap`, which allows us to add or put a key-value pair. If a key already exists, then `.put()` replaces the value with the new value provided

Pretty simple right? This is the heart of our `Counter.java` class. Everything else are just *nice to have* methods.

### `getWords`
```java
// imports hidden
public class Counter {
	// instance variables hidden
	public void add(String word) {...}
	public int getCount(String word) {...}
	
	public Set<String> getWords() {
		return counts.keySet();
	}

	public List<String> getMostCommon() {...}	
}
```

Line 7: Our method signature

Line 8: Simply return the words we have in the keys for `counts`

### `getMostCommon`
```java
// imports hidden
public class Counter {
	// instance variables hidden
	public void add(String word) {...}
	public int getCount(String word) {...}
	public Set<String> getWords() {...}

	public List<String> getMostCommon() {
		ArrayList<String> result = new ArrayList<>(this.getWords());
		for (int i = 1; i < result.size(); i ++){
			int j = i;
			String word = result.get(j);
	
			while (j > 0 && this.getCount(result.get(j - 1)) < this.getCount(word)) {
				result.set(j, result.get(j - 1));
				j -= 1;
			}
			result.set(j, word);
			System.out.print("Sorted " + i + "/" + result.size() + "\r");
		}
		return result;
	}
}
```
Line 8: Our method signature

Line 9: The `List<String>` we are returning. Notice that I copied every word into the `ArrayList` by passing in `this.getWords()` to the constructor.

Line 10-19: Reversed insertion sort algorithm

Ok this looks daunting, but its nothing you haven't seen before. Remember, the goal of this method was to return a list of words based on its values. The desired order was from increasing to decreasing. This means we will end up sorting something, and as an AP CS student you should know 3 sorting algorithms: Bubble, Merge, and Insertion Sort.

For the keen eyed, my program implements the [**Insertion Sort algorithm**](https://www.geeksforgeeks.org/insertion-sort/), albeit modified to work with our use case. Now is a good time to explain why I chose insertion over the chad merge sort. Despite insertion sort having a greater time complexity (O(n^2) compared to merge sorts' O(nlogn)), our use case actually provides a hidden boost to the insertion sort algorithm. When it comes to data thats partially sorted, insertion sort can outshine merge sort.

If we think about it, our data is already partially sorted. `LinkedHashMap` has an order based on insertion, so the sooner we insert something, the closer it'll be to the beginning of our data structure. Since we are ordering our list around the idea that we want the **most** frequent elements, chances are we will encounter the most frequent words before anything else, making most of our work already done for us. 

Think of it this way, we have this odd review:

> cow the the the the the movie the the albatross licks cow

When we create our `LinkedHashMap`,
|Index|Key|Value|
|--|--|--|
|0|cow|2|
|1|the|7|
|2|movie|1|
|3|albatross|1|
|4|licks|1|

Notice how "the" is already pretty close to the front. These are the cases where insertion sort can outshine merge sort. Because words like "the" are incredibly common in the English language (I've used ~111 in this chapter itself), they are more likely to be added to the list near the start. This provides the boost to the insertion sort algorithm. Also, the merge sort algorithm is harder to implement in our use case. 

With this method finally over, we are done with our class file. Now to test it out in `DataExploration.java`

## 2.3.0 Running `DataReader.java`
All of the work in the instructions are provided below. The code isn't difficult to understand and therefore won't come with an explanation.

```java
import helper.Counter;
import helper.DataReader;

public class DataExploration {
	public static void main(String[] args) {
		DataReader data = new DataReader();
		ArrayList<String> reviews = data.getReviews();
		ArrayList<String> labels = data.getLabels();

		Counter positiveCounts = new Counter();
		Counter negativeCounts = new Counter();
		Counter totalCounts = new Counter();

		for (int i = 0; i < labels.size(); i ++) {
			if (labels.get(i).equals("POSITIVE")) {
				for (String word: reviews.get(i).split(" ") {
					positiveCounts.add(word);
					totalCounts.add(word);
				}
			} else {
				for (String word: reviews.get(i).split(" ") {
					negativeCounts.add(word);
					totalCounts.add(word);
				}
			}
		}
		System.out.println(totalCounts.getMostCommon());
	}
}
```

After you run this code, you will have to wait a bit (depending on how fast your computer is), but you can monitor the speed its going at.

With this code, you should get this (annotated) output:
```
[, the, ., and, a, of, to, is, br, it, in, i, this, that, s, was, as, for, with, ...]
```

The output represents the most common "words" in all of the reviews. 

If you have reached this far, then you are on good track to continuing the rest of this chapter! It doesn't get much harder than this at all. In fact, the next chapter, `RatioCounter` contains similar logic to what we did here!




