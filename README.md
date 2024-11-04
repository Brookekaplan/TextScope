# TextScope
TextScope is a Java project that allows users to select a topic with associated articles, preprocesses each article by performing text analysis, and displays useful statistics. It cleans up each article by removing stop words and punctuation, calculates word frequency, counts words, sentences, characters, and provides a sentiment analysis.

# Project Structure
Main: The entry point of the program. It prompts the user to select a topic and then initiates the text processing workflow.
Topic: Manages articles for a selected topic and triggers preprocessing for each article in the topic.
Article: Reads and processes individual articles, performing tasks such as removing stop words, counting words, and analyzing sentiment.

# Features
Topic Selection: Users can select a topic from a predefined list of topics.
Stop Word Removal: Common stop words are removed to improve analysis.
Statistics Calculation:
  Word Count: Counts the number of words in each article.
  Character Count: Counts the number of characters in each article.
  Sentence Count: Counts the sentences based on punctuation.
Word Frequency Analysis: Shows the frequency of each word after removing stop words.
Sentiment Analysis: Analyzes and displays whether the article has a positive, negative, or neutral sentiment.
# Usage
Select a Topic: Run the program and choose a topic by entering the corresponding number.
View Statistics and Processed Content: The program displays statistics for each article, including word count, character count, and sentence count.
View Filtered Content: Displays the content after removing stop words and punctuation.
Sentiment Analysis: Indicates the article's sentiment based on the presence of positive and negative words.
