
package CSDS233_pxb410_P1.source;

import java.util.NoSuchElementException;

/**
 * NumArrayList class is the main class of this project.
 *
 * @author parvbhardwaj
 * Note: The logic and implementation of every method is explained in detail in the README file.
 */
public class NumArrayList implements NumList {
    private NumArrayList numArrayList;
    private int capacity = 0;
    private double parentDoubleArray[] = new double[1];
    private int index = -1;
    private boolean isRemoveCalled = false;
    private int removeIndex = 0;


    /**
     * First constructor of NumArrayList. Initializes an empty
     *
     * @return null.
     */
    public NumArrayList() {
        numArrayList = null;


    }

    /**
     * 2nd Constructor that taken an int as capacity.
     *
     * @param capacity capacity. Capacity of the numArrayList
     */
    public NumArrayList(int capacity) {
        this.capacity = capacity;


    }


    /**
     * returns the capacity
     *
     * @return int. capacity of the numArrayList
     */
    @Override
    public int capacity() {
        // checks which one is bigger and then returns it.
        int biggerSize = (this.capacity >= this.size()) ? this.capacity : this.size();

        return biggerSize;

    }

    /**
     * Returns the size of the numArrayList
     *
     * @return int. size of the list
     */
    @Override
    public int size() {
        if (isRemoveCalled) {
            removeIndex++;
            return (index - removeIndex);


        } else {
            return (index);
        }

    }

    /**
     * Checks if the array is empty
     *
     * @return boolean.
     */
    public boolean isEmpty() {
        return (index == -1);


    }

    /**
     * adds the element to the numArrayList
     *
     * @param value double. a double value that's expected to be added in the array
     */
    public void add(double value) {
        isRemoveCalled = false;
        // if add method is called index is assigned to 1 if it's -1.
        if (index == -1) {
            index = 0;

        }
        double tempArray[] = new double[parentDoubleArray.length + 1];

       /* The loop runs till length of the parentDoubleArray. There's always an extra index in the temparray.
          temparray copies elements from parentDoubleArray to retrieve the previous element**/
        for (int i = 0; i < parentDoubleArray.length; i++) {
            // indices in temparray copy all the elements in parentDoubleArray
            tempArray[i] = parentDoubleArray[i];

        }
        // whenever add method is called newarr is always re-assigned to tempArray.
        // i.e newarr.length == parentDoubleArray.length + 1;
        double newarr[] = tempArray;

        // index increments itself after value has been added to new arr.
        newarr[index++] = value;

        // parentDoubleArray is now assigned to a newarr
        // parentDoubleArray now contains the newly added double and the previously added elements.

        parentDoubleArray = newarr;
        if (this.capacity() == 0) {

        }


    }

    /**
     * @param i     i is the index at which the value will be inserted.
     * @param value value is the element that will be inserted into the array.
     */
    @Override
    public void insert(int i, double value) {

        isRemoveCalled = false;

        // checks whether i is out of bounds or less than 0, then adds value using add method.
        if (i >= parentDoubleArray.length || i < 0) {
            add(value);
        } else {
            // local variable indexTemp to add elements at indexTemp in parentDoubleArray
            int indexTemp = 0;
            double tempArray[] = new double[parentDoubleArray.length + 1];
            for (int k = 0; k < parentDoubleArray.length; k++) {
                ;
                if (k == (i)) {

                    tempArray[k] = value;

                } else {
                    tempArray[k] = parentDoubleArray[indexTemp];
                    indexTemp++;

                }

            }
            parentDoubleArray = tempArray;
        }
    }

    /**
     * removes the elment at i
     *
     * @param i i is the idex which is to be removed
     * @return removes the index i and
     */
    @Override
    public void remove(int i) {
        isRemoveCalled = true;

        if (i >= parentDoubleArray.length || i < 0) {
            // ArrayOutOfBoundsException can be thrown as well
            System.out.println("Please enter correct index because Index entered is out of bounds");
            throw new NoSuchElementException();
        } else {
            int indexTemp = 0;
            double tempArray[] = new double[parentDoubleArray.length];
            for (int k = 0; k < parentDoubleArray.length; k++) {

                if (k == (i)) {


                } else {
                    tempArray[indexTemp] = parentDoubleArray[k];
                    indexTemp++;

                }

            }
            parentDoubleArray = tempArray;
        }
    }

    /**
     * Checks if an element is in the numList
     *
     * @param value
     * @return boolean
     */
    @Override
    public boolean contains(double value) {
        boolean doesContain = false;
        for (int i = 0; i < parentDoubleArray.length - 1; i++) {
            if (parentDoubleArray[i] == value) {
                doesContain = true;

            }
        }
        return doesContain;
    }

    /**
     * return the element at i.
     *
     * @param i takes an index.
     * @return double. if the i is within the bounds it returns.
     */
    @Override
    public double lookup(int i) {
        // if the index is not found -1.0 is returned.
        double foundNumber = -1.0410;
        if (isEmpty()) {
            throw new NullPointerException();


        } else if (i > (parentDoubleArray.length - 1) || i < 0) {
            /*handling an exception by throwing it. */

            throw new NoSuchElementException();


        } else {
            foundNumber = parentDoubleArray[i];

            return foundNumber;
        }


    }

    /**
     * Compares the equality of two lists.
     *
     * @param otherList
     * @return boolean. true or false
     */
    @Override
    public boolean equals(NumList otherList) {

        boolean isEqual = true;

        if (this.size() == otherList.size()) {

            int smallerCapacity = (this.capacity() < otherList.capacity()) ? this.capacity() : otherList.capacity();
            for (int i = 0; i < smallerCapacity; i++) {
                if (this.lookup(i) != otherList.lookup(i)) {
                    isEqual = false;
                }

            }

        } else if (this.size() != otherList.size()) {
            isEqual = false;
        }
        return isEqual;

    }

    /**
     * removes duplicates in the parentDoubleArray
     */
    @Override
    public void removeDuplicates() {

         int length = parentDoubleArray.length;
         int i = 0;
         int index = 0;
         double tempArray[] = new double[length - 1];
         while (i < length - 1) {
         //    tempArray[i] = parentDoubleArray[i];
         int duplicateValIndex;
         boolean doesContain = false;
         for (int j = 0; j < parentDoubleArray.length - 1; j++) {
         if (tempArray[j] == parentDoubleArray[i]) {
         doesContain = true;
         duplicateValIndex = j;

         }

         }
         if (!doesContain) {

         tempArray[index] = parentDoubleArray[i];
         index++;

         }
         i++;
         }

         parentDoubleArray = tempArray;




}

    @Override
    public void reverse() {
        int start = 0;
        int end = parentDoubleArray.length - 1;
        double temp;
        double firstVal = lookup(0);

        while (start <= end) {
            temp = parentDoubleArray[start];
            parentDoubleArray[start] = parentDoubleArray[end];
            parentDoubleArray[end] = temp;
            start++;
            end--;
        }
        add(firstVal);
    }

    @Override
    public void union(NumList list1, NumList list2) {


        for(int i = 0; i < list1.size(); i++){
            if(!contains(list1.lookup(i))){
                add(list1.lookup(i));
            }
        }
        for (int j= 0; j < list2.size(); j++ ){
            if (!contains(list2.lookup(j))){
                add(list2.lookup(j));
            }
        }

    }

    /**
     * Method to convert the NumArrayList into a String with all the elements in it.
     *
     * @return String. returns a string i.e the elements in the NumArrayList
     */
    @Override
    public String toString() {
        // returns "" when the arrayList is empty
        if (isEmpty()) {
            return "";

        }
        else {
            // stringbuilder appends the array elements into it and then uses the tostring method to convert to array
            // into a string.
            StringBuilder stb = new StringBuilder();
            for (int i = 0; i < parentDoubleArray.length - 1; i++) {
                // doesn't remove the last space.
                if (i != (parentDoubleArray.length - 1) && parentDoubleArray[i] != 0.0) {
                    stb.append(parentDoubleArray[i] + " ");
                    if (i == parentDoubleArray.length - 1) {

                    }
                }
            }
            //removes the last space now.
            return stb.toString().trim();

        }
    }


    /**
     * prints theNumArrayList
     */
    public void printArray() {

        for (int i = 0; i < parentDoubleArray.length; i++) {
            if (i != (parentDoubleArray.length - 1) && parentDoubleArray[i] != 0.0) {
                System.out.print(parentDoubleArray[i] + ", ");
            }
        }
    }
    public boolean isSorted(){
            for (int i = 0; i < parentDoubleArray.length - 2; i++) {
                if ( parentDoubleArray[i] >  parentDoubleArray[i + 1])
                    return false;
            }
            return true;


    }


    /**
     * main method
     **/
    public static void main(String args[]) {
        NumArrayList nal = new NumArrayList();
        nal.add(15);
        nal.add(10);
        nal.add(20);
        nal.add(30);
        System.out.println("\nadd method: The arrayList is below");
        nal.printArray();

        /* insert method */

        nal.insert(1, 3.14);
        System.out.println("\n\ninsert method: added 3.14 at 1 to previous list");
        nal.printArray();

        /* equals method */
        NumArrayList nal2 = new NumArrayList();
        nal2.add(2.0);
        nal2.add(3.0);
        nal2.add(12.0);
        NumArrayList nal3 = new NumArrayList();
        nal3.add(2.0);
        nal3.add(3.0);
        nal3.add(12.0);
        System.out.println("\n \n equals method: checking if nal2 and nal3 are equal. They are equal");
        System.out.println(nal2.equals(nal3));


        /* this is the remove method */
        NumArrayList nal4 = new NumArrayList();
        nal4.add(3.2);
        nal4.add(1.2);
        nal4.add(3.4);
        nal4.add(0.1);
        System.out.println("\n remove method: arrayNumList before the remove method");
        nal4.printArray();
        nal4.remove(1);

        System.out.println("\n\n remove method: Remove the 1 index in the arrayList and then printing it");
        nal4.printArray();


        /* removeDuplicate method */
        NumArrayList nal5 = new NumArrayList();
        nal5.add(13);
        nal5.add(12);
        nal5.add(13);
        nal5.add(11);
        nal5.add(4);
        nal5.add(113);
        nal5.add(13);
        nal5.add(12);
        nal5.add(4);
        nal5.add(4);
        System.out.println("\n\nremoveDuplicates: This is the arrayList before removing the duplicates in it.");
        nal5.printArray();
        nal5.removeDuplicates();
        System.out.println("\n\nremoveDuplicates: This is the arrayList after removing the duplicates in it.");
        nal5.printArray();

        /* Lookup method */
        NumArrayList nal6 = new NumArrayList(5);

        System.out.println("\n");
        nal6.add(3.14);
        nal6.add(14);
        nal6.add(11);
        nal6.add(13);
        nal6.add(4);
        System.out.println("Lookup method: Element at index 2 is " + nal6.lookup(2));
        try {
            nal6.lookup(8);
        } catch (Exception e) {
            System.out.println(e);
        }

        /* Null pointer exception in lookup method */
        NumArrayList nal7 = new NumArrayList();
        try {
            nal7.lookup(3);
        } catch (Exception e) {
            System.out.println(e);
        }
        // adding values for toString demonstration
        nal7.add(1.2);
        nal7.add(3.4);
        nal7.add(11);
        nal7.add(13);
        /* toString method demonstration */
        System.out.println("\nTo string method demonstration\n");
        System.out.println(nal7.toString());
        /* union method */
        NumArrayList nal8 = new NumArrayList();
        nal8.add(13.1);
        nal8.add(113);
        nal8.add(345);
        nal8.add(3);
        NumArrayList nal9 = new NumArrayList();
        nal9.add(13.1);
        nal9.add(2.3);
        nal9.add(3);
        nal9.add(1346.7);
        NumArrayList nal10 = new NumArrayList();
        nal10.union(nal8, nal9);
        System.out.println("\nThis is the union method "+ nal10.toString() + "\n");

        /* reverse method */
        nal10.reverse();
        System.out.println("This is the reverse method " + nal10.toString() + "\n" );

        /* isSorted method */
        NumArrayList nal11 = new NumArrayList();
        nal11.add(13.0);
        nal11.add(45.0);
        nal11.add(45.7);
        nal11.add(3);

        System.out.println("\nIs sorted method demonstration: " + nal11.isSorted() + "\n");
        /* when array is sorted 8*/
        NumArrayList nal12 = new NumArrayList();

        nal12.add(1);
        nal12.add(10);
        nal12.add(1000);
        nal12.add(34343);
        nal12.add(10003345);
        System.out.println("\nIs sorted method demonstration: "+ nal12.isSorted() +"\n");
        
    }

}