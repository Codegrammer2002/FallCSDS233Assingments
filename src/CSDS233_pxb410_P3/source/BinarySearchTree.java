
package CSDS233_pxb410_P3.source;
/**
 * ADT implementation of the Binary Search Tree
 *
 * @author Parv Bhardwaj
 * helper methods are private to succesfully implement an ADT class.
 */

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BinarySearchTree<T extends Comparable<T>, V> {
    /**
     * A nested BST node class for our main class.
     */
    private class BSTnode {
        // *** fields *** //
        private T key;
        private V value;
        private BSTnode left, right;


        // *** constructor *** //
        private BSTnode(T key, V value, BSTnode left, BSTnode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        // *** methods *** //


        private V getValue() {
            return value;
        }

        private void setValue(V newV) {
            value = newV;
        }

        private void setKey(T key) {
            this.key = key;

        }

        private T getKey() {
            return key;

        }

        private BSTnode getLeft() {
            return left;
        }

        private BSTnode getRight() {
            return right;
        }

        private void setRight(BSTnode right) {
            this.right = right;

        }

        private void setLeft(BSTnode left) {
            this.left = left;

        }
    }

    // *** fields *** //
    private BSTnode root; // ptr to the root of the BST
    private ArrayList<V> list = new ArrayList<>();

    /**
     * Constructor for the binary search tree
     */
    public BinarySearchTree() {
        root = null;
    }

    // *** methods ***

    /**
     * @param key key is the place node where value will be inserted
     * @param value value is the value that needs to be added
     */
    public void insert(T key, V value) {
        root = insert(key, root, value);
    }

    /**
     * helper private method for the main insert method
     * @param key key is where the value needs to added
     * @param n n is the node
     * @param value value is added to the key and the binary search node
     * @return BSTnode returns a node
     */
    private BSTnode insert(T key, BSTnode n, V value) {
        if (n == null) {
            return new BSTnode(key, value, null, null);
        }

        if (key.compareTo(n.getKey()) < 0) {
            // add k to the left subtree
            n.setLeft(insert(key, n.getLeft(), value));
            return n;
        } else {
            // add k to the right subtree
            n.setRight(insert(key, n.getRight(), value));
            return n;
        }
    }

    /**
     * returns a value in key
     * @param key takes a key that needs to be searched
     * @return returns a value in key
     */
    public V search(T key) {
        return search(root, key);
    }

    /**
     * helper private method for the main search method
     * @param n takes n node
     * @param key takes a key
     * @return returns a value in key
     */
    private V search(BSTnode n, T key) {
        if (n == null) {
            // returns null if n is null
            return null;
        }

        if (n.getKey().equals(key)) {
            // print(n); this is a previous method I created but removed it in the final submission
            return n.getValue();
        }

        if (key.compareTo(n.getKey()) < 0) {
            // key < this node's key; look in left subtree
            return search(n.getLeft(), key);
        } else {
            // key > this node's key; look in right subtree
            return search(n.getRight(), key);
        }
    }

    /**
     * main m
     * @return List<E> of values after their inorder traversal
     */
    public List<V> inorderRec() {
        list.clear();
        if (root != null)
            return inorderRec(root);
        else
            return null;
    }


    /**
     * helper private inorder metohd
     * @param root takes the root
     * @return returns a list of values after in-order traversal
     */
    private List<V> inorderRec(BSTnode root) {

        // Traverse the left subtree
        if (root.getLeft() != null) {
            inorderRec(root.getLeft());
        }

        // Display the val
        list.add(root.getValue());

        // Traverse the right subtree

        if (root.getRight() != null) {
            inorderRec(root.getRight());
        }


        return list;


    }

    /**
     * Usese the in-order traversal function which returns a List. Typecasts it to ArrayList and then returns the k-1
     * value. Logic: the inorder traversal outputs values ascending order
     * @param k is nth smallest element
     * @return V a generic type which is the kth smallest element
     */
    public V kthSmallest(int k) {
        if (root == null || k < 0) {
            ;
        } else {
            ArrayList<V> arr = (ArrayList<V>) inorderRec();
            return arr.get(k - 1);

        }
        return null;
    }

    /** deletes a key of type T
     * @param key the key which needs to be deleted
     */
    public void delete(T key) {
        // Find the node and its parent.
        BSTnode parent = null;
        BSTnode trav = root;
        while (trav != null && trav.getKey() != key) {
            parent = trav;
            if (key.compareTo(trav.getKey()) < 0)
                trav = trav.getLeft();
            else
                trav = trav.getRight();
        }
        // Delete the node (if any) and return the removed item.
        if (trav == null) // no such key
            ;
        else {
            V removedData = trav.getValue();
            deleteNode(trav, parent);
            //return removedData;
        }
    }

    /**
     * this is a helper method to make the BinarySearchTree an ADT
     * @param toDeleteNode
     * @param root
     */
    private void deleteNode(BSTnode toDeleteNode, BSTnode root) {
        if (toDeleteNode.getLeft() == null || toDeleteNode.right == null) {
            // Cases 1 and 2
            BSTnode toDeleteChild = null;
            if (toDeleteNode.getLeft() != null)
                toDeleteChild = toDeleteNode.getLeft();
            else
                toDeleteChild = toDeleteNode.getRight();

            // both Cases are included. In case 1 toDeleteChild==null
            if (toDeleteNode == this.root)
                this.root = toDeleteChild;
            else if (toDeleteNode.getKey().compareTo(root.getKey()) < 0)
                root.setLeft(toDeleteChild);
            else
                root.setRight(toDeleteChild);
        } else {
            BSTnode replacementParent = toDeleteNode;
            BSTnode replacement = toDeleteNode.getRight();

            while (replacement.getLeft() != null) {
                replacementParent = replacement;
                replacement = replacement.getLeft();
            }

            // Replace toDeleteNode's key and data
            toDeleteNode.setKey(replacement.getKey());
            toDeleteNode.setValue(replacement.getValue());

            // Recursively delete the replacement item's old node.
            deleteNode(replacement, replacementParent);
        }
    }

    /**
     *
     * @param args takes the arguments and passes them into the main method
     */
    public static void main(String args[]) {
       /* Instructions for the main method
       Construct an empty BinarySearchTree.
        2. Insert: 2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11.
        3. Delete 4 then delete 9.
        4. Print the keys using inorder traversal.
        5. Search 12 then search 4.
        6. Find the 3rd smallest element in the tree.
        7. Show that your tree is generic by using two other types.
        8. Also include an example using the key-value pairs.
        */

        // ** key is an int and double will be the values ** //
        BinarySearchTree<Integer, Double> tree2 = new BinarySearchTree<Integer, Double>();

        tree2.insert(2, 2.11);
        tree2.insert(1, 1.10);
        tree2.insert(4, 4.9);
        tree2.insert(5, 5.8);
        tree2.insert(9, 9.7);
        System.out.println("\n Key 9 has value " + tree2.search(9));
        tree2.insert(3, 3.6);
        tree2.insert(6, 6.5);
        tree2.insert(7, 7.4);
        tree2.insert(10, 10.3);
        tree2.insert(12, 12.2);
        tree2.insert(11, 11.1);
        System.out.println("\n In order traversal of tree 2 before deleting keys: " + tree2.inorderRec());
        tree2.delete(4);
        tree2.delete(9);
        System.out.println("\n In order traversal of tree 3 after deleting keys " + tree2.inorderRec());
        System.out.println("The answer should be null " + tree2.search(9));
        System.out.println("The answer should be null " + tree2.search(4));
        System.out.println("The third smallest value in the tree is " + tree2.kthSmallest(3));

        // ** Different Generics **//
        BinarySearchTree<Integer, String> tree3 = new BinarySearchTree<Integer, String>();
        tree3.insert(2, "apple");
        tree3.insert(1, "art");
        tree3.insert(4, "ay");
        tree3.insert(5, "cat");
        tree3.insert(9, "ball");
        System.out.println("\n Key 9 has value " + tree3.search(9));
        tree3.insert(3, "dog");
        tree3.insert(6, "top");
        tree3.insert(7, "pot");
        tree3.insert(10, "high");
        tree3.insert(12, "jog");
        tree3.insert(11, "kite");
        System.out.println(tree3.inorderRec());
        tree3.delete(4);
        tree3.delete(9);
        System.out.println(tree3.inorderRec());
        System.out.println("The answer should be null " + tree3.search(9));
        System.out.println("The answer should be null " + tree3.search(4));
        System.out.println("The third smallest value in the tree is " + tree3.kthSmallest(3));

        // ** key-value pair ** //
        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<Integer, Integer>();
        tree1.insert(2, 2);
        tree1.insert(1, 1);
        tree1.insert(4, 4);
        tree1.insert(5, 5);
        tree1.insert(9, 9);
        System.out.println("\n Key 9 has value " + tree1.search(9));
        tree1.insert(3, 3);
        tree1.insert(6, 6);
        tree1.insert(7, 7);
        tree1.insert(10, 10);
        tree1.insert(12, 12);
        tree1.insert(11, 11);
        System.out.println(tree1.inorderRec());
        tree1.delete(4);
        tree1.delete(9);
        System.out.println(tree1.inorderRec());
        System.out.println("The answer should be null " + tree1.search(9));
        System.out.println("The answer should be null " + tree1.search(4));
        System.out.println("The third smallest value in the tree is " + tree1.kthSmallest(3));

        //** Timing different methods using the timer class **//

        // creating a binary tree to  compare the time of different operations
        BinarySearchTree<Integer, Double> tree4 = new BinarySearchTree<>();
        long startTime = System.nanoTime();
        tree4.insert(19, 10.12);
        tree4.insert(18, 9.12);
        tree4.insert(7, 8.12);
        tree4.insert(10, 7.12);
        tree4.insert(11, 6.12);
        tree4.insert(2, 5.12);
        tree4.insert(25, 15.13);
        tree4.insert(21, 12.13);
        tree4.insert(22, 11.3);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Elapsed time after inserting elements in nanoseconds is " + duration);

        // for searching keys and values in nanoseconds
        long start1 = System.nanoTime();
        tree4.search(25);
        tree4.search(21);
        tree4.search(2);
        tree4.search(11);
        tree4.search(10);
        tree4.search(1);
        long end1 = System.nanoTime();
        long duration1 = (end1 - start1);
        System.out.println("Elapsed Time after searching keys in nanoseconds: " + (duration1));


        long start2 = System.nanoTime();
        tree4.delete(25);
        tree4.delete(8);
        tree4.delete(1);
        long end2 = System.nanoTime();
        long duration2 = end2 - start2;
        System.out.println("Elapsed Time after deleting keys in nanoseconds: " + duration2);

        long start3 = System.nanoTime();
        tree4.inorderRec();
        long end3 = System.nanoTime();
        long duration3 = end3 - start3;
        System.out.println("Elasped time after the inOrder recursion is:" + duration3);

    }
    // Explanation of the analysis.
    /* In the searching of different keys in the AVL tree the time taken is less than the time taken for a BST tree
     * The time differences we see in AVL code for insertion and deletion also incorporate the time of
     * the secondary operations like balancing get height balance factor etc.
     * But overall an average case rum time for an AVL tree is much shorter than a BST tree  */


    }











