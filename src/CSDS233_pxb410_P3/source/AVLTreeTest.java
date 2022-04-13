package CSDS233_pxb410_P3.source;

import static org.junit.Assert.*;



import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AVLTreeTest {
    @Test
    public void insert() {
        // **making a tree with key as an integer and value as an integer** //
        AVLTree<Integer, Integer> tree1 = new AVLTree<>();
        tree1.insert(0, 2);
        tree1.insert(1, 1);
        tree1.insert(2, 4);
        assertEquals(new Integer(4), tree1.search(2));

        // **making a new tree with values as String types** //
        AVLTree<Integer, String> tree2 = new AVLTree<>();
        tree2.insert(9, "a");
        tree2.insert(4, "b");
        tree2.insert(1, "c");
        assertEquals("a", tree2.search(9));
        assertEquals("b", tree2.search(4));
        assertEquals("c", tree2.search(1));


    }

    @Test
    public void search() {
        // very similar to the testing we did above
        AVLTree<Integer, String> tree = new AVLTree<>();

        // searching in an empty tree

        assertEquals(null, tree.search(5));

        tree.insert(1, "A");
        tree.insert(2, "D");
        tree.insert(3, "E");

        // normally searching a tree
        assertEquals("A", tree.search(1));
        assertEquals("E", tree.search(3));
        assertEquals("D", tree.search(2));
    }

    @Test
    public void delete() {
        AVLTree<Integer, String> tree = new AVLTree<>();

        tree.insert(1, "a");
        tree.insert(5, "b");
        tree.insert(2, "c");

        assertEquals("a", tree.search(1));
        assertEquals("b", tree.search(5));
        assertEquals("c", tree.search(2));

        tree.delete(10);
        assertEquals(null, tree.search(10));
        try {
            assertEquals(null, tree.search(10));
        } catch (NoSuchElementException e) {
            ;
        }

        AVLTree<Integer, String> tree2 = new AVLTree<>();
        // delete when root is null
        try {
            tree2.delete(4);
        } catch (NoSuchElementException e) {
            ;
        }

        try {
            tree.delete(4);
        } catch (NoSuchElementException e) {
            ;
        }

    }

    /**
     * Comparing a sorted arraylist with the inroder traversal of the binary search tree
     */
    @Test
    public void inroderRec() {

        AVLTree<Integer, Integer> tree = new AVLTree<>();
        tree.insert(20, 20);
        tree.insert(10, 10);
        tree.insert(1, 1);
        tree.insert(15, 15);
        tree.insert(50, 50);

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(1);
        list2.add(10);
        list2.add(15);
        list2.add(20);
        list2.add(50);
        assertEquals(list2, tree.inorderRec());

        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(11);
        list.add(11);
        list.add(12);
        list.add(14);
        list.add(15);
        list.add(17);
        list.add(19);
        list.add(110);
        list.add(111);
        list.add(112);
        list.add(115);
        list.add(120);


        AVLTree<Integer, Integer> tree2 = new AVLTree<>();
        tree2.insert(20, 220);
        tree2.insert(10, 210);
        tree2.insert(4, 24);
        tree2.insert(1, 21);
        tree2.insert(7, 27);

        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(21);
        list1.add(24);
        list1.add(27);
        list1.add(210);
        list1.add(220);

        assertEquals(list1, tree2.inorderRec());


        AVLTree<Integer, Integer> bst1 = new AVLTree<>();
        bst1.insert(2, 102);
        bst1.insert(1, 101);
        bst1.insert(4, 104);
        bst1.insert(5, 105);
        bst1.insert(9, 109);
        bst1.insert(3, 103);
        bst1.insert(7, 107);
        bst1.insert(10, 1010);
        bst1.insert(12, 1012);
        bst1.insert(11, 1011);

        bst1.delete(4);

        ArrayList<Integer> list3 = new ArrayList<Integer>();
        list3.add(101);
        list3.add(102);
        list3.add(103);
        list3.add(104);
        list3.add(107);
        list3.add(109);
        list3.add(1010);
        list3.add(1011);
        list3.add(1012);
        assertEquals(list3, bst1.inorderRec());

    }


    @Test
    public void kthSmallest() {
        AVLTree<Integer, Integer> tree = new AVLTree<>();

        // kthSmallest of an empty tree returns null
        assertEquals(null, tree.kthSmallest(1));

        tree.insert(2, 2);
        tree.insert(1, 1);
        tree.insert(4, 4);
        tree.insert(5, 5);

        // when k<0 returns null
        assertEquals(null, tree.kthSmallest(-1));
        // 2nd smallest
        assertEquals(new Integer(2), tree.kthSmallest(2));

        // smallest
        assertEquals(new Integer(1), tree.kthSmallest(1));


    }

    @Test
    public void main() {
        System.out.println("All the methods are compiled succesfully ");
    }
}