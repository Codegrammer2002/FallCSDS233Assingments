package CSDS233_pxb410_P4.source;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Testing is done using a String array[] it's much more efficient to make the test  by taking a string array
 * mainly because both file and array have the operations done in the constructor and it's in submission.
 * .txt files might change but this strrng array will stay here
 *
 */
public class WordStatTest {
    // testing with a string array
    String[] array = new String[] {"fruit","cherry","pear","banana","mango", "mango", "banana", "banana", "pear", "banana" , "banana," , "mango"};
    String[] array2 = new String[]{"dog", "cat", "dog", "dog", "cat", "dog", "dog", "lion" , "cat"};
    WordStat wordstat = new WordStat(array);

    @Test
    public void wordCountTest() {
        // test to check the wordCount of different words.
        assertEquals(1, wordstat.wordCount("fruit"));
        assertEquals(5, wordstat.wordCount("banana"));
        assertEquals(-1, wordstat.wordCount("toy"));
        assertEquals(-1 , wordstat.wordCount("dog"));
    }
    // test to check the wordPar
    @Test
    public void wordPairCountTest() {
        assertEquals(2, wordstat.wordPairCount("pear", "banana"));
        assertEquals(2, wordstat.wordPairCount("banana", "mango"));

    }

    @Test
    public void wordRankTest() {
        assertEquals(1, wordstat.wordRank("banana"));
        assertEquals(2, wordstat.wordRank("mango"));
        assertEquals(0, wordstat.wordRank("toy"));

    }

    /*
     * this is the tes
     */
    @Test
    public void mostCommonWordsTest() {
        assertEquals("[banana]", Arrays.toString(wordstat.mostCommonWords(1)));
        assertEquals("[banana, mango]", Arrays.toString(wordstat.mostCommonWords(2)));

    }

    @Test
    public void leastCommonWordsTest() {
        WordStat wordstat1 = new WordStat(array2);
        assertEquals("[lion]", Arrays.toString(wordstat1.leastCommonWords(1)));
    }
    @Test
    public void wordPairRankTest() {
        assertEquals(0, wordstat.wordPairRank("name","lastName"));

    }


    @Test
    public void collocsTest() {
        String[] arr1 = new String[]{"java", "java", "java", "python", "python", "elm", "flutter", "javascript", "code", "program", "software"};
        WordStat wordstat1 = new WordStat(arr1);
      //  assertEquals("[is] ,[this]", Arrays.toString(wordstat1.mostCommonCollocs(2,"my",-1)));
        assertEquals("[javascript, code]" , Arrays.toString(wordstat1.mostCommonCollocs(2 , "flutter" , 1)));
    }
    @Test
    public void generateWordsTest(){
        WordStat ws = new WordStat("/Users/parvbhardwaj/Desktop/wordStat.txt");

        System.out.println( ws.generateWordString(2 , "is"));
           assertEquals("is is parv" , ws.generateWordString(2 ,"is"));
    }


   
}

