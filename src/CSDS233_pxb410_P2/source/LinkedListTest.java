package CSDS233_pxb410_P2.source;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void isEmpty() {
        LinkedList l1 = new LinkedList();
        assertEquals( true , l1.isEmpty());
        l1.add(3.4);

        assertEquals(false , l1.isEmpty());
    }

    @Test
    public void size() {
        LinkedList l1  = new LinkedList();
        assertEquals(0 , l1.size());
        l1.add(3.4);
        l1.add(3.456);
        l1.add(1234);
        assertEquals(3 , l1.size());
        l1.remove(1);
        assertEquals(2 , l1.size());



    }

    @Test
    public void contains() {
        LinkedList l1 = new LinkedList();
        assertEquals( false , l1.contains(3.4));
        l1.add(3.4);
        l1.add(3.45);
        l1.add(1234);
        assertEquals(true, l1.contains(3.4));
        assertEquals(false , l1.contains(3.4556));
    }

    @Test
    public void lookup() {
        LinkedList l1 = new LinkedList();
        try {
            assertEquals("", l1.lookup(-1));
        }
        catch (Exception e){
            System.out.println( e);
        }
        assertEquals( false , l1.contains(3.4));
        l1.add(3.4);
        l1.add(3.45);
        l1.add(1234);
        assertEquals(1234.0 , l1.lookup(2) , 0);

    }

    @Test
    public void capacity() {
        LinkedList l1 = new LinkedList();

        assertEquals(Integer.MAX_VALUE , l1.capacity());
    }

    @Test
    public void add() {
        // tested in other methods.
        LinkedList l1 = new LinkedList();
        l1.add(34);
        l1.add(3.4);
        // this shows that the values have been added
       assertEquals( 2 , l1.size());
    }

    @Test
    public void insert() {
        LinkedList linkedList2 = new LinkedList();
        linkedList2.add(3.4);
        linkedList2.add(44);
        assertEquals(44.0 , linkedList2.lookup(1) , 0);
        linkedList2.add(112);
        linkedList2.insert(1, 3.1414);
        assertEquals(3.1414 , linkedList2.lookup(1) ,0);

    }

    @Test
    public void remove() {
        LinkedList l1 = new LinkedList();
        l1.add(3.4);
        l1.add(112);
        l1.add(3.1415);
        l1.remove(1);
        assertEquals(3.1415 ,l1.lookup(1) , 0 );
        assertEquals(2 , l1.size());
    }


    @Test
    public void removeDuplicates() {
        LinkedList l1 = new LinkedList();
        l1.add(12.0);
        l1.add(3.4);
        l1.add(3.4);
        l1.add(1.02);
        l1.add(12.0);
        l1.add(113);
        l1.add(113);
        l1.add(1.02);
        l1.removeDuplicates();
        assertEquals("12.0 3.4 1.02 113.0" , l1.toString());
        // checking the size after removing the elements
        assertEquals(4, l1.size());


    }

    @Test
    public void reverse() {
        LinkedList l3 = new LinkedList();
        l3.add(1.3);
        l3.add(4.5);
        l3.add(112);
        l3.add(11.3);
        l3.add(3.1415);
        l3.reverse();
        assertEquals("3.1415 11.3 112.0 4.5 1.3" , l3.toString());

    }

    @Test
    public void union() {
        LinkedList linkedList3 = new LinkedList();
        linkedList3.add(45);
        linkedList3.add(13);
        linkedList3.add(113);

        LinkedList linkedList4 = new LinkedList();
        linkedList4.add(13);
        linkedList4.add(445);
        linkedList4.add(113);
        linkedList4.add(3456);
        linkedList3.union(linkedList3, linkedList4);
        assertEquals("45.0 13.0 113.0 445.0 3456.0" , linkedList3.toString());
    }

    @Test
    public void isSorted() {
        LinkedList l1 = new LinkedList();
        l1.add(4.5);
        l1.add(4.5);
        l1.add(47.4);
        l1.add(12345);
        assertEquals(true, l1.isSorted());
        l1.add(0.1);
        assertEquals(false , l1.isSorted());


    }

    @Test
    public void testToString() {
        LinkedList l1 = new LinkedList();
        assertEquals("" , l1.toString());
        l1.add(4);
        l1.add(4.5);
        l1.add(1.2);
        assertEquals("4.0 4.5 1.2" , l1.toString());
    }

    @Test
    /*helper method*/
    public void printLinkedList() {
        LinkedList l1 = new LinkedList();
        l1.add(3.4);
        l1.add(4.5);
        System.out.println("Expected answer: 3.4 4.5 \n");
        l1.printLinkedList();

    }
    @Test
    public void testEquals(){
        LinkedList l1 = new LinkedList();
        l1.add(3.4);
        l1.add(56.1);
        l1.add(10.0);
        l1.add(112.3);
        LinkedList l2 = new LinkedList();
        l2.add(3.4);
        l2.add(56.1);
        assertEquals(false, l1.equals(l2));
        l2.add(10.0);
        l2.add(112.3);
        assertEquals(true ,l1.equals(l2));
        l2.add(3.4);
        assertEquals(false, l1.equals(l2));

    }



    @Test
    public void main() {

    }
}