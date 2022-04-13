package CSDS233_pxb410_P4.source;
/**
 * @author pxb410 aka Codegrammer2002(Github username)
 * This is the first class for this project.
 * Reads a file, normalizes the words and then stores the words in string arraylist with each word at a seperate index
 * I will be using BufferReader to read the files.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tokenizer {
    // arraylists and string for text file reading and splitting
    private ArrayList wordList = new ArrayList<>();
    private ArrayList wordPairList = new ArrayList();
    private ArrayList<String> sentence = new ArrayList<>();
    // for file reading
    private String filePath;
    // for a sentence in the file.
    private String strLine;

    /**
     * The first constructor. It takes a .txt file and then breaks it into words and word pair using two helper
     * functions.
     *
     * @param filePath a file path of a .txt file that needs to be processed.
     */
    public Tokenizer(String filePath) {
        this.filePath = filePath;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            try {
                strLine = br.readLine();
            } catch (Exception e) {
                System.out.println("The file is empty");
            }
            while (strLine != null) {
                sentence.add(strLine);
                strLine = br.readLine();
                if (strLine == null) {
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        } finally {
            breakSentences(sentence);
            makeWordPairs(sentence);

        }

    }

    /**
     * This is the second constructor. If the users wants to process a String array.
     *
     * @param wordArray takes a wordArray of type String
     */
    public Tokenizer(String[] wordArray) {
        // adding words directly to the wordList
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = wordArray[i].replaceAll("[^\\w]", "");
            wordList.add(wordArray[i].toLowerCase().trim());

        }
        // adding words directly to the wordPairList
        for (int i = 0; i < wordArray.length - 1; i++) {
            wordArray[i] = wordArray[i].replaceAll("[^\\w]", "");
            wordPairList.add("" + wordArray[i].toLowerCase().trim() + wordArray[i + 1].toLowerCase().trim());
        }

    }

    /**
     * a helper function
     * breakes sentences intro words uses regex to eliminate punctuation, spacesc etc.
     *
     * @param sentence takes an arraylist of type String
     */
    private void breakSentences(ArrayList<String> sentence) {

        for (int i = 0; i < sentence.size(); i++) {
            String[] words = sentence.get(i).split("\\s+");
            for (int y = 0; y < words.length; y++) {
                // checks for a non-word character
                words[y] = words[y].replaceAll("[^\\w]", "");
                // converts to lower case
                wordList.add(words[y].toLowerCase().trim());

            }
        }

    }

    /**
     * wordList returns the list of word
     *
     * @return String ArrayList
     */
    public ArrayList<String> wordList() {
        return wordList;

    }

    /**
     * a helper function to make word pairs
     * similar to the break sentences
     *
     * @param sentence takes an arraylist of type String
     */
    private void makeWordPairs(ArrayList<String> sentence) {
        for (int i = 0; i < wordList.size() - 1; i++) {
            wordPairList.add(("" + wordList.get(i)) + wordList.get(i + 1));

        }


    }

    // gets the word pair list
    public ArrayList<String> wordPairList() {
        return wordPairList;

    }

    // Demonstration of the tokenizer class
    public static void main(String args[]) {
        Tokenizer tk = new Tokenizer("/Users/parvbhardwaj/Desktop/p4SampleTextFile.txt");
        ArrayList<String> words;
        words = tk.wordList();
        try {
            for (int i = 0; i < words.size(); i++) {

                System.out.println(words.get(i));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        String str[] = new String[5];
        str[0] = "Code";
        str[1] = "code";
        str[2] = "Program";
        str[3] = "program's";
        str[4] = "l ast ";


        Tokenizer tk2 = new Tokenizer(str);
        ArrayList<String> words2;
        words2 = words = tk2.wordList();


        for (int i = 0; i < words2.size(); i++) {
            // I am using the comma here to distinguish the individaul elements not something that isn't filtered
            // through regex
            System.out.print(words.get(i) + ",");
        }
        Tokenizer tk3 = new Tokenizer("/Users/parvbhardwaj/Desktop/p4SampleTextFile.txt");
        ArrayList wordPairs = tk3.wordPairList();
        for (int i = 0; i < wordPairs.size(); i++) {
            System.out.println(wordPairs.get(i));
        }
    }
}

