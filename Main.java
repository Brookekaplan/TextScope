/*
 * @Authors     Sarah Liu, Brooke Kaplan, Ryan Lindley
 * This program allows the user to select a topic (where each topic has 3 articles --> txt files)
 * and preprocesses each file by reading, removing stop words, counting word frequency and calculating stats: word count, character count, sentence count
 * Then displays the information to the user. 
 * To run, select topic with corresponding integer. 
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String userTopic = topicSelector();  //calls topic selector to get user choice
        run(userTopic);                     //preprocesses articles in topic and display
    }

    //display and return user choice for topic 
    public static String topicSelector(){
        Scanner myScanner = new Scanner(System.in);
        int topic ;
        System.out.println("Please select a topic:\n1. Gaming 2. Stocks 3. Climate Solutions 4. Food and Nutrition 5. Giants vs Cowboys game 6. Iphone 15");
        topic = myScanner.nextInt();
        myScanner.close();
        return "top" + topic;
    }
  
    //run the preprocess method to remove stop words, dispay statistics, and rank words by frequency
    public static void run(String topicName) throws IOException{
        //calls topic constructor and creates new topic
        Topic t = new Topic(topicName);     
        t.preprocess();                    
    }
}