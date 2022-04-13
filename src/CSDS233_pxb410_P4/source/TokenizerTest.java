package CSDS233_pxb410_P4.source;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/* this is the test for the tokenizer class
*the tokenizer class only contains one public method and we will use different test cases in that public method
*to test edge cases, null files, normalization using regex, etc.
*this test will be very similat to the demonstration we did in the main method for the tokenizer class.
* */

public class TokenizerTest {

    @Test
    public void wordList() {
        String arr[] = {"One" ," Two", "Three"};
        boolean isTrue = true;
        Tokenizer tk = new Tokenizer("/Users/parvbhardwaj/Desktop/tkTestClass.txt");
        ArrayList<String> words1 =  tk.wordList();
        Tokenizer tk2 = new Tokenizer(arr);
        ArrayList<String> words2 =  tk2.wordList();
        System.out.println(words1.size());
        System.out.println(words2.size());

        for(int i = 0 ; i < words2.size(); i++){
            System.out.println("Word "+words1.get(i));
            System.out.println("Word "+words2.get(i));
           if(!words1.get(i).equals(words2.get(i))){
               isTrue = false;

           }
        }


        assertEquals( true , isTrue);

    }

}