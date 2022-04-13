package CSDS233_pxb410_P4.source;
/**
 * This is the main class. Word stat classes uses the hash table and the tokenzier class
 * to do word processing. This class includes the required method and a demonstartion.
 **/

import java.io.*;
import java.util.*;

public class WordStat {
    // declaration of the private fields.
    public HashTable wrdCountTable;
    public HashTable wordPairTable;
    // this is the tokenzier
    private Tokenizer wrdTokenizer;
    // using priority queues for the mostCommonCollocs section.
    public PriorityQueue<String> wordQueue;
    public PriorityQueue<String> wordPairQueue;
    // arraylist that stores the most common Words. also used for word count
    public ArrayList<String> cmnWrds;
    // arraylist storing the most common words pairs. also used for word pair count.
    private ArrayList<String> cmnWrdPairs;

    /**
     * This is the first constructor that we
     *
     * @param path
     */
    public WordStat(String path) {
        wrdTokenizer = new Tokenizer(path);
        runConstructorOperations();


    }

    public WordStat(String[] array) {
        // creating a tokeniser
        wrdTokenizer = new Tokenizer(array);
        runConstructorOperations();


    }


    /**
     * Since both the constructors are virtually similar I made a common method that I run in both the constructors.
     * runConstructorOperations takes nothing as a param and returns nothing.
     * Main operations of this function are: wordCount, wordRank,etc.
     */
    private void runConstructorOperations() {
        cmnWrds = wrdTokenizer.wordList();
        cmnWrdPairs = wrdTokenizer.wordPairList();
        doWordCount();
        doWordPairCount();
        // updating the common words
        ArrayList<String> newList = new ArrayList<String>();
        for (int i = 0; i < cmnWrds.size(); i++) {
            if (!newList.contains(cmnWrds.get(i))) {
                newList.add(cmnWrds.get(i));
            }
        }
        cmnWrds = newList;
        // updating the common word pairs
        ArrayList<String> newList2 = new ArrayList<String>();
        for (int i = 0; i < cmnWrdPairs.size(); i++) {
            if (!newList2.contains(cmnWrdPairs.get(i))) {
                newList2.add(cmnWrdPairs.get(i));
            }
        }
        cmnWrdPairs = newList2;

        wordQueue = new PriorityQueue<String>((a, b) -> wrdCountTable.get(b) - wrdCountTable.get(a));
        for (int i = 0; i < cmnWrds.size(); i++) {
            wordQueue.add(cmnWrds.get(i));
        }

        wordPairQueue = new PriorityQueue<String>((a, b) -> wordPairTable.get(b) - wordPairTable.get(a));
        for (int i = 0; i < cmnWrdPairs.size(); i++) {
            wordPairQueue.add(cmnWrdPairs.get(i));
        }

    }

    /**
     * a private helper method that does the word pair count in the constructor.
     * Thi
     */
    private void doWordCount() {
        /* increasing the HashTable size to avoid scenarios where the HashTable overflows. */
        wrdCountTable = new HashTable(2 * cmnWrds.size());
        // taking the count of the individual words from wordList and updating the wordCountTable hash
        for (int i = 0; i < cmnWrds.size(); i++) {
            // check if the words doesn't exist if yes add it otherwise update
            if (wrdCountTable.get(cmnWrds.get(i)) == -1) {
                wrdCountTable.put(cmnWrds.get(i), 1);
            } else {
                wrdCountTable.update(cmnWrds.get(i), wrdCountTable.get(cmnWrds.get(i)) + 1);

            }
        }
    }

    /**
     * This is again a helper private method that I am using for the constructor for better readability
     * This function does the word pair count.
     */
    private void doWordPairCount() {
        // similarly, we take the count of the word pairs in the wordList and updating the word.
        // just like we did in the wordCount functions
        wordPairTable = new HashTable(2 * cmnWrdPairs.size());
        for (int i = 0; i < cmnWrdPairs.size(); i++) {
            // check if the words doesn't exist if yes add it otherwise update
            if (wordPairTable.get(cmnWrdPairs.get(i)) == -1) {
                wordPairTable.put(cmnWrdPairs.get(i), 1);
            } else {
                wordPairTable.update(cmnWrdPairs.get(i), wordPairTable.get(cmnWrdPairs.get(i)) + 1);

            }
        }
    }

    /**
     * gets the word count
     *
     * @param word takes a string word
     * @return int returs the count of the word
     */
    public int wordCount(String word) {
        return wrdCountTable.get(word.toLowerCase());
    }

    /**
     * wordPairCount gives us the word pair conut of two words
     *
     * @param word1 takes w1 and w2
     * @param word2 takes w1 and 2
     * @return the word count of the word pair.
     */
    public int wordPairCount(String word1, String word2) {
        return wordPairTable.get(word1.toLowerCase() + word2.toLowerCase());
    }

    /**
     * returns the wordRank of the project
     *
     * @param word tkaes the word which needs to be ranked
     * @return
     */
    public int wordRank(String word) {
        word = word.toLowerCase();
        int rank = 1;
        for (String i : wordQueue) {
            if (i.equals(word)) {
                return rank;
            }
            rank++;
        }
        return 0;
    }

    /**
     * @param w1 takes the wordPairRank word 1
     * @param w2 takes wordPair Rank word 2
     * @return int which is the rank of the word pair
     */
    public int wordPairRank(String w1, String w2) {
        String s = w1.toLowerCase() + " " + w2.toLowerCase();
        int rank = 1;
        for (String i : wordPairQueue) {
            if (i.equals(s)) {
                return rank;
            }
            rank++;
        }
        return 0;
    }

    /**
     * @param k prints the k most common  words.
     * @return A String array which contains the most common words
     */
    public String[] mostCommonWords(int k) {
        String[] s = new String[k];
        int count = 0;
        for (String i : wordQueue) {
            if (count < k) {
                s[count++] = i;
            }
        }
        return s;
    }

    /**
     * returns an array of the least common words
     *
     * @param k takes the k which is k least common words
     * @return String array which contains the least common words
     */
    public String[] leastCommonWords(int k) {
        ArrayList<String> temp = new ArrayList<String>();
        for (String i : wordQueue) {
            temp.add(i);
        }
        Collections.reverse(temp);
        String[] s = new String[k];
        for (int i = 0; i < k; i++) {
            s[i] = temp.get(i);
        }
        return s;
    }

    /**
     * returns an array of the most common words in the file or array.
     *
     * @param k k most elements will be returned
     * @return String array
     */
    public String[] mostCommonWordPairs(int k) {
        String[] str = new String[k];
        int counter = 0;

        for (String i : wordPairQueue) {
            if (counter < k) {
                str[counter++] = i;
            }
        }
        return str;
    }

    /**
     * @param k        is the no of most common words that we need before/after the baseWord
     * @param baseWord baseWord is the word which serves as a refrence point for the k most common element
     * @param i        int. +1 means on the right of the baseWord -1 on the left of the
     * @return
     */
    public String[] mostCommonCollocs(int k, String baseWord, int i) {
        // creating an array
        ArrayList<String> wrdLst = wrdTokenizer.wordList();
        // this is temporary array list.
        ArrayList<String> tempo = new ArrayList<String>();
        // checking for i if 1 and checks if the word is in the wordLst
        if ((i == 1 || i == -1) && wrdLst.contains(baseWord.toLowerCase())) {
            // index
            int index = 0;
            // running the loop till we get the base word
            for (int j = 0; j < wrdLst.size(); j++) {
                if (wrdLst.get(j).equals(baseWord)) {
                    break;
                }
                index++;
            }
            // running the loop till we find the element's index greather than index
            if (i == -1) {
                for (int y = 0; y < index; y++) {
                    tempo.add(wrdLst.get(y));
                }
            } else {
                for (int y = index + 1; y < wrdLst.size(); y++) {
                    tempo.add(wrdLst.get(y));
                }
            }
            // using priority queue here
            PriorityQueue<String> collocQueue = new PriorityQueue<String>((a, b) -> wrdCountTable.get(b) - wrdCountTable.get(a));
            int count = 0;
            tempo = removeDuplicates(tempo);
            for (String j : tempo) {
                collocQueue.add(j);
                if (count == k - 1) {
                    break;
                }
                count++;
            }
            return collocQueue.toArray(new String[collocQueue.size()]);
        } else
            return wordQueue.toArray(new String[wordQueue.size()]);
    }

    /**
     * this is a helper method we use to remove duplicates in ArrayList to avoid bugs
     *
     * @param lst takes a list and then removes thec duplicates in the list. very similar to what we did
     *            in one of the previous projects to remove duplicated
     * @return
     */

    private ArrayList<String> removeDuplicates(ArrayList<String> lst) {
        ArrayList<String> newList = new ArrayList<String>();

        for (String element : lst) {
            /* is the element isn't already in the list we change it  */
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
    // with this we're done with the main code and it's time to do the extra credit.
    // I am going to update the logbook as well.
    // extra credit methods.
    /**
     *
     * @param k k
     * @param startWord
     * @return
     */
    public String generateWordString(int k, String startWord) {

        // calling the k most colloc method
        String[] wrdArr = mostCommonCollocs(k, startWord, 1);
        // making a string to append the words.
        StringBuilder stb = new StringBuilder(startWord);
        // for each loop for appending the words
        for (String words : wrdArr) {
            stb.append(" ");
            stb.append(words);

        }
        //  StringBuilder.toString to return the string.
        return stb.toString();
    }
    /**
     * this function is similar to the function that we used inMostCommonCollocs
     * @param k  k is the k most common words we want before or after i
     * @param baseWord the baseword
     * @param i i is either -1 or 1
     * @param exclusions excludes the words given in the array
     * @return a string containing k most elements
     */
    public String[] mostCommonCollocs(int k, String baseWord, int i, String[] exclusions) {
        ArrayList<String> words = wrdTokenizer.wordList();
        // creaitng a temp ArrayList
        ArrayList<String> temp = new ArrayList<String>();
        if((i == 1 || i == -1) && words.contains(baseWord.toLowerCase())) {
            int index = 0;
            for (int j = 0; j < words.size();j++) {
                if (words.get(j).equals(baseWord)){
                    break;
                }
                index++;
            }
            if (i == - 1) {
                for (int j = 0; j < index; j++) {
                    temp.add(words.get(j));
                }
            }
            else {
                for (int j = index + 1; j < words.size();j++) {
                    temp.add(words.get(j));
                }
            }
            //Removing all words in exclusions
            temp.removeAll(Arrays.asList(exclusions));
            // using the priority queue.
            PriorityQueue<String> collocQueue = new PriorityQueue<String>((a, b) -> wrdCountTable.get(b) - wrdCountTable.get(a));
            int count = 0;
            temp = removeDuplicates(temp);
            for(String j: temp) {
                collocQueue.add(j);
                if(count == k - 1) {
                    break;
                }
                count++;
            }
            return collocQueue.toArray(new String[collocQueue.size()]);
        }
        else
            return wordQueue.toArray(new String[wordQueue.size()]);
    }

    /**
     * This is the demonstration of the WordStat class
     * @param args takes all the arguments and runs it
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        // This is the demonstation of the project.
        WordStat ws = new WordStat("/Users/parvbhardwaj/Desktop/wordStat.txt");
        // the expected output is 4.
        System.out.println("Word Count of is is" + ws.wordCount("is"));
        // doing the wordrank now
        System.out.println(ws.wordRank("is"));
        // wordrank example 2
        System.out.println(ws.wordRank("parv"));
        // word runk of a word that does'nt exist
        System.out.println(ws.wordRank("level"));
        // wordPair rank of is and parv
        System.out.println(ws.wordPairRank("is", "parv"));
        // printing the most common words
        // using the basic approach of storing the returned array and then just printing the items
        String arr[] = ws.mostCommonWords(2);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        // creating a new line
        System.out.println("\n");
        // doing the same thing here for the word pairs now
        String arr3[] = ws.mostCommonWordPairs(2);
        System.out.println("\n");
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println("\n");
        // Here we can see that the mostCommonCollocs prints both is and parv

        System.out.println(Arrays.toString(ws.mostCommonCollocs(2 , "blue" , -1 )));

        //** extra credit method demonstration. **/
        System.out.println("Generated words:" + ws.generateWordString(2 , "is"));
        String exclude[] = {"is"};
        // Here it only prints the parv exculding the is.
        System.out.println(Arrays.toString(ws.mostCommonCollocs(2 , "blue" , -1 , exclude )));
    }


}
