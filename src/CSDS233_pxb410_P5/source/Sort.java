package CSDS233_pxb410_P5.source;

/**
 * @author parvbhardwaj
 * This is the sort class which contains different sorting algorithms.
 * The algorithms will also be compared using the Java Timer Class.
 */


import java.util.Arrays;
import java.util.Random;
import java.time.Duration;
import java.time.Instant;

public class Sort {
    /**
     * Sorts a given an array in ascending order
     * Overall Logic:
     *
     * @param arr an array that needs to be sorted
     */
    public void insertionSort(int[] arr) {

        // int to store the size of the array
        int length = arr.length;
        // looping through the array to insert numbers at their right place.

        for (int i = 1; i < length; i++) {
            if (arr[i] < arr[i - 1]) {
                int toInsert = arr[i];
                int j = i;
                while (j > 0 && toInsert < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    j = j - 1;
                }
                arr[j] = toInsert;
            }
        }
    }

    public void quickSort(int[] array) {
        int low = 0;
        int high = array.length - 1;

        quickSort(array, low, high);

    }


    private void quickSort(int arr[], int low, int high) {

        if (low < high) {
			/* pi is partitioning index, arr[pi] is
			now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int arr[], int low, int high) {

        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public void mergeSort(int[] arr) {

        int length = arr.length;
        int[] temp = new int[length];

        mSort(arr, temp, 0, length - 1);

    }

    public void mSort(int[] arr, int[] temp, int start, int end) {
        if (start == end) { // base case

            int middle = (start + end) / 2; // The splitting step
            mSort(arr, temp, start, middle);
            mSort(arr, temp, middle + 1, end);
            merge(arr, temp, start, middle, middle + 1, end);
        }

    }

    static void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart; // index into left subarray
        int j = rightStart; // index into right subarray
        int k = leftStart; // index into temp
        while (i <= leftEnd && j <= rightEnd) {
            if (arr[i] < arr[j])
                temp[k++] = arr[i++];
            else{
                temp[k++] = arr[j++];
            }
        }
        while ( i <= leftEnd) temp[ k++ ] = arr[ i++ ];
        while (j <= rightEnd) temp[ k++ ] = arr[ j++ ];
        for (i = leftStart; i <= rightEnd; i++) arr[ i ] = temp[ i ];

    }


    /***
     *
     * @param n n of ints that need to be in the array
     * @param a the starting bound
     * @param b the ending bound
     * @return returns an int array
     */
    public int[] randomArray(int n, int a, int b) {
        int randIArray[] = new int[n + 2];
        randIArray[0] = a;
        randIArray[randIArray.length - 1] = b;
        Random randI = new Random();
        for (int i = 1; i < randIArray.length - 1; i++) {
            int random_integer = randI.nextInt(b-a) + a;
            randIArray[i] = random_integer;
        }
        return randIArray;

    }

    /* ** Extra Credit ** */
    public void bubbleSort(int[] array) {
        int length = array.length - 1;
        for (int i = length; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
            }
        }

    }

    private void swap(int arr[], int num1, int num2) {
        int temp = arr[num2];
        arr[num2] = arr[num1];
        arr[num1] = temp;

    }


    // Extra credit sorting Algo: Bubble Sort
    public static void main(String args[]) {
        Sort randIArray = new Sort();
        int randIArr[] = randIArray.randomArray(11, 1, 10);
        System.out.println(Arrays.toString(randIArr));
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        long duration = endTime = startTime;


    }

}
