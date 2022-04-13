package CSDS233_pxb410_P2.source;


import java.util.NoSuchElementException;



/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList implements NumList {
    /**
     * the first node of the list, or null if the list is empty
     */
    private LLNode firstNode;

    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }

    /**
     * Returns the first node.
     */
    protected LLNode getFirstNode() {
        return firstNode;
    }



    /**
     * Changes the first node.
     *
     * @param node the first node of the new linked list
     */
    protected void setFirstNode(LLNode node) {
        this.firstNode = node;
    }


    /**
     * Return whether the list is empty
     *
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }

    /**
     * Returns the length of the linked list
     *
     * @return the number of nodes in the list
     */
    public int size() {
        int count = 0; // counts number of nodes seen
        LLNode nodeptr = getFirstNode();
        while (nodeptr != null) {
            count++;
            nodeptr = nodeptr.getNext();
        }
        return count;
    }


    @Override
    public boolean contains(double value) {
        LLNode nodeptr = getFirstNode();
        while (nodeptr != null) {
            if (nodeptr.getElement() == value) {
                return true;
            }
            nodeptr = nodeptr.getNext();
        }
        return false;
    }

    @Override
    public double lookup(int index) {
        if (index < 0 || index >= size()) {
            System.out.println("Please enter a valid index number");
            throw new NoSuchElementException();


        }
        LLNode nodeptr = getFirstNode();
        for (int i = 0; i < index; i++) {
            nodeptr = nodeptr.getNext();
        }
        return nodeptr.getElement();
    }


    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void add(double value) {
        if (isEmpty()) {
            setFirstNode(new LLNode(value, getFirstNode()));

        } else {
            LLNode nodeptr = getFirstNode();
            // the loop will end with nodeptr looking at the last node in list
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode(value, null));


        }
    }

    @Override
    public void insert(int index, double value) {
        /** logic 1:
         * tempnode to store the original node
         *  previous node links to the new node and new node links to the next node
         *  **/
        LLNode nodeptr = getFirstNode();
        for (int i = 0; i < index - 1; i++) {
            nodeptr = nodeptr.getNext();
        }

        if (nodeptr.getNext() != null) {
            LLNode tempNode = new LLNode(value, nodeptr.getNext());
            nodeptr.setNext(tempNode);
        } else {
            LLNode tempNode = new LLNode(value, null);

            nodeptr.setNext(tempNode);
        }

    }

    @Override
    public void remove(int index) {
        int size = size();
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else if (index >= size || index < 0) {
            throw new NoSuchElementException();


        }
        else {

            LLNode nodeptr = getFirstNode();
            for (int i = 0; i < index - 1; i++) {
                nodeptr = nodeptr.getNext();
            }
            if (nodeptr.getNext().getNext() != null) {
                nodeptr.setNext(nodeptr.getNext().getNext());
            } else {
                nodeptr.setNext(null);
            }

        }
    }

    /**
     * This is the remove method
     **/
    @Override
    public void removeDuplicates() {

        LLNode nodeptr1 = null, nodeptr2; // dup = null;
        nodeptr1 = getFirstNode();

        /* Pick elements one by one */
        while (nodeptr1 != null && nodeptr1.getNext() != null) {
            nodeptr2 = nodeptr1;

            /* Compare the picked element with rest
                of the elements */
            while (nodeptr2.getNext() != null) {

                /* If duplicate then delete it */
                if (nodeptr1.getElement() == nodeptr2.getNext().getElement()) {

                    /* sequence of steps is important here
                     */
                    nodeptr2.setNext(nodeptr2.getNext().getNext());
                }
                else /* This is tricky */ {
                    nodeptr2 = nodeptr2.getNext();
                }
            }
            nodeptr1 = nodeptr1.getNext();
        }
    }


    @Override
    public void reverse() {
        LLNode prev = null;
        LLNode current = getFirstNode();
        LLNode next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        setFirstNode(prev);

    }





    @Override
    public void union(NumList list1, NumList list2) {
        if (list1 != null) {
            for (int i = 0; i < list1.size(); i++) {
                if (!contains(list1.lookup(i))) {
                    add(list1.lookup(i));
                }
            }
        }
        if (list2 != null) {
            for (int j = 0; j < list2.size(); j++) {
                if (contains(list2.lookup(j))) {
                    ;
                } else {
                    add(list2.lookup(j));
                }
            }
        }
        if (list1 == null && list2 == null) {
            System.out.println("Both lists are null");
        }
    }

    @Override
    public boolean isSorted() {
        LLNode nodeptr = getFirstNode();
        while (nodeptr.getNext() != null) {
            if (nodeptr.getElement() > nodeptr.getNext().getElement()) {
                return false;
            }
            nodeptr = nodeptr.getNext();

        }
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "";
        else {

            StringBuilder stb = new StringBuilder();
            LLNode nodeptr = getFirstNode();
            while (nodeptr != null) {
                stb.append(nodeptr.getElement() + " ");
                nodeptr = nodeptr.getNext();

            }
            return stb.toString().trim();
        }
    }

    public boolean equals(NumList otherList) {
        if (otherList.size() != size()) {
            return false;
        } else {
            for (int i = 0; i < size(); i++) {
                if (lookup(i) != otherList.lookup(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Additional method to print the LinkedList
     **/
    public void printLinkedList() {
        for (LLNode nodeptr = getFirstNode(); nodeptr != null; nodeptr = nodeptr.getNext()) {
            System.out.print(nodeptr.getElement() + " ");
        }
    }

    /** main method **/
    public static void main(String args[]) {
        LinkedList linkedList = new LinkedList();
        // Adding elements to the linkedlist. Demonstrating the add method.
        linkedList.add(3.4);
        linkedList.add(22);
        // Also demostarting the lookup method/
        System.out.println("\n\nLookup method: " + linkedList.lookup(1) + "\n");
        System.out.println("\n\nChecking the size: " + linkedList.size() + "\n");
        // adding a few more elements.
        linkedList.add(33.2);
        linkedList.add(43);
        // size method demonstration
        System.out.println("\n\nChecking the size after adding some more: " + linkedList.size() + "\n");

        /* remove method demonstration */
        // removing index 1.
        linkedList.remove(1);
        System.out.println("\n\nRemove method: Previous index 1 element was 22 " + linkedList.lookup(1) + "\n");
        // checking the size again
        System.out.println("\n\nChecking size again " + linkedList.size() + "\n");

        /* contains method demonstration */
        System.out.println("\n\nContains 3.4: " + linkedList.contains(3.4));
        // doesn't contain a double
        System.out.println("\n\n Contains 14141: " + linkedList.contains(14141));

        /* Insert method demonstartion */
        LinkedList linkedList2 = new LinkedList();
        linkedList2.add(3.4);
        linkedList2.add(44);
        linkedList2.add(112);
        linkedList2.insert(1, 3.1414);
        System.out.println("\n\nThe new index 2 is: " + linkedList2.lookup(3) + "\n\n");
        linkedList2.printLinkedList();

        /* Union method */
        System.out.println("\n\n This is the union method\n\n");
        LinkedList linkedList3 = new LinkedList();
        linkedList3.add(45);
        linkedList3.add(13);
        linkedList3.add(113);
        linkedList.add(13);
        /* Demonstrating the print method and the union method **/
        LinkedList linkedList4 = new LinkedList();
        linkedList4.add(13);
        linkedList4.add(445);
        linkedList4.add(113);
        linkedList4.add(3456);
        linkedList3.union(linkedList3, linkedList4);
        System.out.println("\n\n The below printed list is the union of the two lists. Union method testing");
        linkedList3.printLinkedList();

        System.out.println("\n\n");

        /* Demonstrating the removeDuplicates */
        LinkedList list4 = new LinkedList();
        list4.add(334);
        list4.add(112);
        list4.add(112);
        list4.add(334);
        list4.add(12345);
        list4.removeDuplicates();
        System.out.println("\n\n Remove Duplicates method " + list4.toString());


        /* Demonstration of the equals method */
        LinkedList l1 = new LinkedList();
        l1.add(3.4);
        l1.add(4.5);
        l1.add(1.3);
        LinkedList l2 = new LinkedList();
        l2.add(3.4);
        l2.add(4.5);
        l2.add(1.1);
        System.out.println("\n\n  Demonstration of the equals method:" + l1.equals(l2));

        /* Demonstrating the reverse method now */
        LinkedList l3 = new LinkedList();
        l3.add(1.3);
        l3.add(4.5);
        l3.add(112);
        l3.add(11.3);
        l3.reverse();
        // demonstration of the toString method
        System.out.println("\n\n Reverse method: " + l3.toString());


        /** repeating some demonstrations to enure that every method has been covered */
        LinkedList list5 = new LinkedList();
        /* max capacity demonstration */
        System.out.println("The max capacity of the the linked list is " + list5.capacity());

        /*size method*/
        LinkedList list6 = new LinkedList();
        list6.add(3.4);
        list6.add(1.2);
        System.out.println("Checking the size " + list6.size() );
        /* isSort method demonstration */
        LinkedList l11 = new LinkedList();
        l11.add(4.5);
        l11.add(4.5);
        l11.add(47.4);
        l11.add(12345);
        // true right now
        System.out.println("\n demons. isSorted: " +l11.isSorted());

        l11.add(0.1);
        //false now
        System.out.println("\n demos. isSorted: " + l11.isSorted());

        /* equals method demonstration */
        LinkedList l12 = new LinkedList();
        l12.add(3.4);
        l12.add(56.1);
        l12.add(10.0);
        l12.add(112.3);
        LinkedList l13 = new LinkedList();
        l13.add(3.4);
        l13.add(56.1);
        System.out.println("equals method: " +l12.equals(l13));
        l13.add(10.0);
        l13.add(112.3);
        System.out.println("equals method: " + l12.equals(l13));
        l13.add(3.4);
        System.out.println("equals method: " + l2.equals(l13));
        System.out.println("\n\nEnd of the main method");



    }

}