/*
 * Article class reads new articles, calculates statistics on itself 
 */
import java.util.*;
import java.io.*;

public class Article {

    //global variables
    //stop words
    private static HashSet<String> stopWords = new HashSet<String>(
        Arrays.asList("a","able","about","across","after","all","almost","also","am","among","an",
        "and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did",
        "do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her",
        "hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like",
        "likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on",
        "only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than",
        "that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was",
        "we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your")
    );
    private char [] punctuation = {',', '.', ';', '?', '(', ')', '"', '/', '\\', '!', '[', ']', ':'};

    private String articlePath;  
    private String content;
    private ArrayList<String> filteredContent = new ArrayList<>();

    private int positiveCount = 0;
    private int negativeCount = 0;
    private String ArticleName;

    //contructor
    public Article(String articlePath) {
        this.articlePath = articlePath;   //sets global var to local var
        readArticle();                    //calls readArticle function
    }

    //method to read articles and store as a string 
    private void readArticle() {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(articlePath));  //reading file 
            ArticleName = reader.readLine();
            while((line = reader.readLine()) != null){                               
                content = content + line + " ";                                       //add lines to content
            }
            reader.close();     
            content.trim();  //removes white spaces before or after 
        } catch (IOException e) {   //catch error
            e.printStackTrace();
        }  
    }

    public String getArticleName(){
        return ArticleName;
    }

    //method to remove stop words in an array list
    public String removeStopWords() {
        String returnString = "";
        //removes punctuation from articles
        for (char punc : punctuation){
            content = content.replace(punc, ' ');
        }
        //removes stopwords from articles
        String [] words = content.trim().split(" ");      
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {      //conditional statement for if article does no contains stopword
                filteredContent.add(word);                      //add word to filtered contents 
            }
        }
        //convert to string
        for (String word : filteredContent){                    
            returnString = returnString + word + " ";
        }
        return returnString;
    }



    //convert content in arraylist to string
    public String toString() {
        return content;         
    }

    //count number of senteces in each article
    public int countSen() {
        int sentenceCount = 0;
        for(int i = 0; i < content.length(); i++){      //loop through contents of article
            char c = content.charAt(i);                 //condition for counting num of sentences
            if(c == '.'|| c == '!'|| c == '?'){         //If string has sentence finisher characters --> '.', '!', '?' number of sentences +1
                sentenceCount++;
            }
        }
        return sentenceCount;
    }

    //count num of characters in each article
    public int countChar() {
        return content.length();        //returns length of content of articles --> counts characters
    }

    //count words in each article
    public int countWords() {
        String [] words = content.split("\\s+");      //stores words in article into array by splitting content
        return words.length;                                //return number of words 
     }

    
    // method to count and display word frequency
    public Hashtable <String, Integer> wordFrequency(){
        Hashtable <String, Integer> wordFreq = new Hashtable<String, Integer>();        //use hashtable dictionary to store (String word, int frequency)
        int tempFrequency;
        for (String word : filteredContent){                                   //loops words in filtered contents
            if(wordFreq.containsKey(word.toLowerCase())){                      //checks if word already occured 
                tempFrequency = wordFreq.get(word.toLowerCase());              
                wordFreq.put(word.toLowerCase(), tempFrequency + 1);           //adds 1 for every occurence
            } else {
                wordFreq.put(word.toLowerCase(), 1);                     //sets new word to frequency 1
            }
        }
        return wordFreq;            //return hashtable
    }

    //method for analyzing sentiment --> positive or negative attitude 
    public String analyzeSentiment() {
        ArrayList<String> positiveWords = new ArrayList<>();
        ArrayList<String> negativeWords = new ArrayList<>();
    
        //read postive words
        try (BufferedReader reader = new BufferedReader(new FileReader("positive-words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                positiveWords.add(line.trim().toLowerCase()); //store words in lowercase
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read negative words
        try (BufferedReader reader = new BufferedReader(new FileReader("negative-words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                negativeWords.add(line.trim().toLowerCase()); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //loop through each word in filtered content (after reomving stop words) 
        for(String word: filteredContent){
            word = word.toLowerCase();
            if(positiveWords.contains(word)){               //compare word to positive words
                positiveCount++;                            //count number of positive words in article
            }else if (negativeWords.contains(word)){        //compare word to negative words
                negativeCount++;                            //count number of negative words in article
            }
        }

        //return sentiment of article if number positive words > number negative words, article is positive vice versa
       if (positiveCount > negativeCount) { 
            return "Positive";
        } else if (negativeCount > positiveCount) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
}
