package CSDS233_pxb410_P1.source;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * The final submission will contain two zipped files(Project 1 and Project 2),
 * it will then contain the readme file and final logbook for the project 2.
 */

/**
 * Tester class for the NumArrayList
 *
 * @author parvbhardwaj
 */
public class NumArrayListTest {

    /**
     * test to check the set Capacity method.
     */
    @org.junit.Test
    public void setCapacity() {
        // initial capacity is 3
        NumArrayList nal = new NumArrayList(3);

        // the capacity is 5 now


        // Test for chceking the capacity while not specifying the capacity and adding the elements.
        NumArrayList nal2 = new NumArrayList();
        nal2.add(45.0);
        nal2.add(12.0);
        assertEquals(2, nal2.capacity());
        // specifying the capacity but numArrayList isn't filled completely.
        NumArrayList nal3 = new NumArrayList(4);
        nal.add(1.0);

        assertEquals(4, nal3.capacity());

    }

    /**
     * test to check the getCapacity method
     * In my implementation of ADT an array list is empty when it's index value is -1
     * So when we create a NumArrayyList the size is -1 and hence it's empty
     */
    @org.junit.Test
    public void capacity() {
        NumArrayList nal = new NumArrayList(5);
        assertEquals(5, nal.capacity());

    }

    /**
     * test to check the size method
     */
    @org.junit.Test
    public void size() {
        // nal has no element for now
        NumArrayList nal = new NumArrayList(3);
        // the size must be -1 since there is no element in the list

        assertEquals(-1, nal.size());

        nal.add(3.14);
        // the size should be 1 now but capacity should still be 3
        assertEquals(1, nal.size());

        // the size should be 1 now but capacity should still be 3
        assertEquals(3, nal.capacity());

        NumArrayList nal3 = new NumArrayList(10);
        nal3.add(1.2);
        nal3.add(3.4);
        nal3.add(7.7);
        nal3.add(0.0);
        nal3.add(1.1);
        assertEquals(5, nal3.size());
    }


    /**
     * Testing the add method. I am going to use lookup and contains method to check if the add method works/
     * If look up returns the correct index, then the add method works correctly.
     * If after adding the element contains method returns true, add method works.
     */
    @org.junit.Test
    public void add() {
        NumArrayList nal = new NumArrayList();
        nal.add(3.0);
        assertEquals(true, nal.contains(3.0));

        nal.add(4.0);
        assertEquals(4.0, nal.lookup(1), 0);
    }

    /**
     * Testing the insert method.
     */
    @org.junit.Test
    public void insert() {
        NumArrayList nal = new NumArrayList();
        nal.add(15);
        nal.add(10);
        nal.add(20);
        nal.add(30);
        // checking the position of 10 before the insert method is implemented.
        assertEquals(10.0, nal.lookup(1), 0.0);
        // adding the insert method now
        nal.insert(1, 3.14);
        // checking whether insert method inserted 3.14 at index 1
        assertEquals(3.14, nal.lookup(1), 0.0);
        // now checking where 10.0 is placed in the array. expected answer is 2
        assertEquals(10.0, nal.lookup(2), 0.0);

        /* Creating a new NumArrayList object to test when i is greater than the length of the array
         * Expected result: inserted element should be added at last**/
        NumArrayList nal2 = new NumArrayList();
        nal2.add(15);
        nal2.add(10);
        nal2.add(20);
        nal2.add(30);
        nal2.insert(5, 3.14);
        // 3.14 should be added at last as expected
        assertEquals("15.0 10.0 20.0 30.0 3.14", nal2.toString());


    }

    /**
     * Testing the remove method.
     */
    @org.junit.Test
    public void remove() {
        // Test 1: adding and then removing index 1
        NumArrayList nal = new NumArrayList();
        nal.add(3.2);
        nal.add(1.2);
        nal.add(3.4);
        nal.add(0.1);
        nal.remove(1);
        assertEquals("3.2 3.4 0.1", nal.toString());
        assertEquals(3, nal.size());
        nal.remove(0);
        assertEquals(2, nal.size());
        nal.remove(0);
        assertEquals(1, nal.size());
        nal.remove(0);
        assertEquals(0, nal.size());

        // Test 2: index out of Array bounds
        NumArrayList nal2 = new NumArrayList();
        nal2.add(3.2);
        nal2.add(1.2);
        nal2.add(3.4);
        try {
            nal2.remove(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        // arrayList remains unchanged
        assertEquals("3.2 1.2 3.4", nal2.toString());

    }

    /**
     * Test to check the contains method.
     *
     * @return true or false. if element exists then true otherwise false.
     */
    @org.junit.Test
    public void contains() {
        NumArrayList nal = new NumArrayList();
        // checking on an empty list. expected answer: false
        assertEquals(false, nal.contains(3.0));

        //adding the element and then checking if the list contains the element. expected answer: true
        nal.add(3.0);
        assertEquals(true, nal.contains(3.0));

        // checking an element that's not in the NumArrayList. expected answer: false
        assertEquals(false, nal.contains(4.0));
    }

    /**
     * Testing the lookup method.
     * The lookup method returns -1.0410 when the index entered is invalid
     */
    @org.junit.Test
    public void lookup() {
        NumArrayList nal = new NumArrayList();
        try {

            // Test 1: Array is null. Null pointer exception is catched and printed.
            assertEquals(-1.0410, nal.lookup(2), 0);
        } catch (Exception e) {

            System.out.println(e);
        }
        //Test 2: Looking up 0th index.
        nal.add(2.0);
        assertEquals(2.0, nal.lookup(0), 0);
        // Test 3: ArrayIndexOutOfBounds. Prints an error message and returns the default double(-1.0410)
        try {
            assertEquals(-1.0410, nal.lookup(2), 0);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Test to removeDuplicated
     */
    @org.junit.Test
    public void removeDuplicates() {
        // Test 1: Removing multiple duplicate elements from the
        NumArrayList nal = new NumArrayList();
        nal.add(13);
        nal.add(12);
        nal.add(13);
        nal.add(11);
        nal.add(4);
        nal.add(113);
        nal.add(13);
        nal.add(12);
        nal.add(4);
        nal.add(4);

        nal.removeDuplicates();
        assertEquals("13.0 12.0 11.0 4.0 113.0", nal.toString());
    }


    /**
     * Testing toString method.
     * Testing for the null case and then for the normal case
     */
    @org.junit.Test
    public void testToString() {

        NumArrayList nal = new NumArrayList();

        // testing for the null case
        assertEquals("", nal.toString());
        nal.add(3.0);
        nal.add(45);
        nal.add(30);

        // adding the elements now.
        assertEquals("3.0 45.0 30.0", nal.toString());
    }


    /**
     * test to check the isEmpty method
     * An empty list has an index of -1.
     * isEmpty checks if the index is -1 or not and returns True or False.
     */
    @org.junit.Test
    public void isEmpty() {

        NumArrayList nal = new NumArrayList();
        assertEquals(true, nal.isEmpty());

        // now we add an element to the list and check again what isEmpty returns. It should return false.
        nal.add(3.4);
        assertEquals(false, nal.isEmpty());


    }

    @org.junit.Test
    public void equals() {

        // Test 1: Equal arrays
        NumArrayList nal = new NumArrayList();
        nal.add(2.0);
        nal.add(3.0);
        nal.add(12.0);
        NumArrayList nal2 = new NumArrayList();
        nal2.add(2.0);
        nal2.add(3.0);
        nal2.add(12.0);
        assertEquals(true, nal.equals(nal2));

        // Test2: Same size and elements but different order
        NumArrayList nal3 = new NumArrayList();
        nal3.add(3.14);
        nal3.add(1.414);
        nal3.add(34);
        NumArrayList nal4 = new NumArrayList();
        nal3.add(1.414);
        nal3.add(34);
        nal3.add(3.14);
        assertEquals(false, nal3.equals(nal4));

        // Test3: Different sizes
        NumArrayList nal5 = new NumArrayList();
        nal5.add(3.14);
        nal5.add(112);
        NumArrayList nal6 = new NumArrayList();
        nal6.add(3.14);
        assertEquals(false, nal5.equals(nal6));
        //NOTE: It passes all the tests

    }

    @Test
    public void union() {
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
        assertEquals("13.1 113.0 345.0 3.0 2.3 1346.7", nal10.toString());


    }

    @Test
    public void reverse() {
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
        nal10.reverse();

        System.out.println("1346.7 2.3 3.0 345.0 113.0 13.1" + nal10.toString());
    }

    @Test
    public void isSorted() {
        NumArrayList nal11 = new NumArrayList();
        nal11.add(13.0);
        nal11.add(45.0);
        nal11.add(45.7);
        nal11.add(3);
        nal11.isSorted();
        assertEquals(false, nal11.isSorted());

        /* when array is sorted 8*/
        NumArrayList nal12 = new NumArrayList();

        nal12.add(1);
        nal12.add(10);
        nal12.add(1000);
        nal12.add(34343);
        nal12.add(10003345);
        assertEquals(true, nal12.isSorted());

    }

    @org.junit.Test
    public void main() {
    }
}