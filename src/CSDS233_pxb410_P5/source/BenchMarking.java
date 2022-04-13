package CSDS233_pxb410_P5.source;

/**
 * @author I created a seperate class for BenchMarking
 */
public class BenchMarking {
    // As instructed in the project we need to an array with dfferent array sizes
    // ** n = 10, 20, 50, 100, 200, 500, 1000, 2000, and 5000. **//
    private boolean calcDone;

    private boolean isNotCalculationDone(){

        return true;

    }
  /*  private int[] getArray(){
        Sort arr = new Sort();
        int size[] = {8, 48,98,198,498,998,1998,4998 };
        int count =0;

        while(isNotCalculationDone()){
            if(count == 8){
                break;
            }
            int array[] = arr.randomArray(size[count] ,-1000000 ,100000 );
            count++;
            return array;

        }
        return null;



    } */



    public static void main(String args[]) {
        // array n size = 10
        Sort array1 = new Sort();
        int arr1[] = array1.randomArray(8, -20, 20);
        // First algo: insertion sort
        long sT1 = System.nanoTime();
          array1.insertionSort(arr1);
        long eT1 = System.nanoTime();
        long dr1 = eT1 - sT1;
       // For every algorithm we will require a new unsupported random array.
        // Second algo: quick sort

        Sort array2 = new Sort();
        int arr2[] = array2.randomArray(8, -20, 20);
        long sT2 = System.nanoTime();
        array2.quickSort(arr1);
        long eT2 = System.nanoTime();
        long dr2 = eT2 - sT2;
        // Third algo: merge sort
        long sT3 = System.nanoTime();
        array1.quickSort(arr1);
        long eT3 = System.nanoTime();
        long dr3 = eT3 - sT3;
        // Four algo: bubble sort
        long sT4 = System.nanoTime();
        //
        long eT4 = System.nanoTime();
        long dr4 = eT4 - sT4;

        // array 2 n size = 50
        Sort array5 = new Sort();
        int arr5[] = array5.randomArray(18, -60, 60);
        long sT5 = System.nanoTime();
        // algo goes here
        long eT5 = System.nanoTime();
        long dr5 = eT5 - sT5;

        // First second: quick sort
        long sT6 = System.nanoTime();
        // algo goes here
        long eT6 = System.nanoTime();
        long dr6 = eT6 - sT6;
        // First algo: merge sort
        long sT7 = System.nanoTime();
        // algo goes here
        long eT7 = System.nanoTime();
        long dr7 = eT7 - sT7;
        // First algo: bubble sort
        long sT8 = System.nanoTime();
        // algo goes here
        long eT8 = System.nanoTime();
        long dr8 = eT8 - sT8;

        // array 3: n size = 100
        Sort array9 = new Sort();
        int arr9[] = array9.randomArray(98, -1000, 1000);
        long sT9 = System.nanoTime();
        // algo goes here
        long eT9 = System.nanoTime();
        long dr9 = eT9 - sT9;

        // First second: quick sort
        long sT10 = System.nanoTime();
        // algo goes here
        long eT10 = System.nanoTime();
        long dr10 = eT10 - sT10;
        // First algo: merge sort
        long sT11 = System.nanoTime();
        // algo goes here
        long eT7 = System.nanoTime();
        long dr7 = eT7 - sT7;
        // First algo: bubble sort
        long sT8 = System.nanoTime();
        // algo goes here
        long eT8 = System.nanoTime();
        long dr8 = eT8 - sT8;
        // array 4: n size = 200
        Sort array4 = new Sort();
        int arr4[] = array4.randomArray(198, -1000, 1000);

        // array 5: n size = 500
        Sort array5 = new Sort();
        int arr5[] = array5.randomArray(498, -10000, 10000);
        // array 6: n size = 1000
        Sort array6 = new Sort();
        int arr6[] = array6.randomArray(998, -10000, 10000);
        // array 7 : n size = 2000
        Sort array7 = new Sort();
        int arr7[] = array7.randomArray(1998, -1000000, 100000);

        // array 8: n size = 5000
        Sort array8 = new Sort();
        int arr8[] = array8.randomArray(4998, -1000000, 1000000);

    }
}
