package CSDS233_pxb410_P5.source;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SortTest {

    @Test
    public void insertionSort() {
        int arr1[] = {2, 4, 13, 9, 56, 37 ,11, 0 , 56};
        Sort algo1 = new Sort();
        algo1.insertionSort(arr1);
        assertEquals("[0, 2, 4, 9, 11, 13, 37, 56, 56]" , Arrays.toString(arr1));

    }

    @Test
    public void quickSort() {
        int arr1[] = {2, 4, 13, 9, 56, 37 ,11, 0 , 56};
        Sort algo1 = new Sort();
        algo1.quickSort(arr1);
        assertEquals("[0, 2, 4, 9, 11, 13, 37, 56, 56]" , Arrays.toString(arr1));
    }

    @Test
    public void mergeSort() {
        int arr1[] = {2, 4, 13, 9, 56, 37 ,11, 0 , 56};
        int arr2[] = new int[0];
        Sort algo1 = new Sort();
        algo1.quickSort(arr1);
        assertEquals("[0, 2, 4, 9, 11, 13, 37, 56, 56]" , Arrays.toString(arr1));
        algo1.mergeSort(arr2);
    }

    @Test
    public void randomArray() {
        int expectedCont = 4;
        int count =0;
        Sort randIArr = new Sort();
        randIArr.randomArray(4  , 3 , 20);
        for(int i = 1; i <  randIArr.randomArray(4  , 3 , 20).length - 1 ; i++){
            count++;

        }
        assertEquals(expectedCont , count);

    }

    // the extra credit bubble sort testing
    @Test
    public void bubbleSort(){
        int arr1[] = {2, 4, 13, 9, 56, 37 ,11, 0 , 56};
        Sort algo1 = new Sort();
        algo1.bubbleSort(arr1);
        assertEquals("[0, 2, 4, 9, 11, 13, 37, 56, 56]" , Arrays.toString(arr1));
    }


}