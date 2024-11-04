/*
 * Topic class creates articles from dataset 
 */

import java.io.File;
import java.util.ArrayList;

public class Topic {

    //global variables
    private static String datasetPath = "dataset//";
    private File topicPath;
    private ArrayList<Article> articles = new ArrayList<>();
    public String name;

    //contructor Topic
    public Topic(String name) {
        this.name = name;
        this.topicPath = new File (datasetPath + name);
        this.addArticles();
    }

    //method to add articles to an arrayList to topic articles
    private void addArticles() {
        File [] articleList = topicPath.listFiles();
        for (int i = 1; i < articleList.length; i++){
            String articlePath = articleList[i].toString();     
            Article a = new Article(articlePath);               //creating new article and calling article constructor
            this.articles.add(a);
        }
    }

    //calls all functions and prints --> stats: words, characters, sentences and filtered articles from removing stop words and word frequency
    public void preprocess(){
        for (Article a : articles){
            // System.out.println("\nORIGINAL ARTICLE: " + a); --> print original article to check 
            System.out.println("\n\n");
            System.out.println(a.getArticleName());
            System.out.println("\n ------ STATS ------ ");
            System.out.println("Word count: "  + a.countWords());
            System.out.println("Character count: " + a.countChar());
            System.out.println("Sentence count: " + a.countSen());
            System.out.println("\n");
            System.out.println("------FILTERED CONTENT------\n" + a.removeStopWords());
            System.out.println("\nWord Frequency: \n" + a.wordFrequency().toString());
            System.out.println("\nArticle Sentiment: " + a.analyzeSentiment());
        }
    }
}
     

