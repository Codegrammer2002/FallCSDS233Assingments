package CSDS233_pxb410_P3.source;
/**
 * A generic implementation of the AVL trees.
 */

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class AVLTree<T extends Comparable<T>, V> {
    /**
     * helper class to make with helper methods
     */
    public class AVLNode {
        T key;
        V value;
        int height;
        AVLNode left;
        AVLNode right;

        AVLNode(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public AVLNode getLeft() {
            return left;
        }

        public AVLNode getRight() {
            return right;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }
    }

    private AVLNode root;
    private ArrayList<V> list = new ArrayList<>();


    public V search(T key) {

        return search(root, key);
    }

    /**
     * helper private method for the main search method
     *
     * @param node takes node node
     * @param key  takes a key
     * @return returns a value in key
     */
    private V search(AVLNode node, T key) {
        if (node == null) {
            // returns null if n is null
            return null;
        }

        if (node.getKey().equals(key)) {
            // this is a previous method I created but removed it in the final submission
            return node.getValue();
        }

        if (key.compareTo(node.getKey()) < 0) {
            // key < this node's key; look in left subtree
            return search(node.getLeft(), key);
        } else {
            // key > this node's key; look in right subtree
            return search(node.getRight(), key);
        }
    }

    /**
     * inserts a value at a specific key
     *
     * @param key   takes a kewy in which the node will be inserted
     * @param value value which is to be inserted at the key
     */
    public void insert(T key, V value) {
        root = insert(root, key, value);
    }

    /**
     * deletes a node at a specific
     *
     * @param key takes a key. the associated node is deleted
     */
    public void delete(T key) {
        root = delete(root, key);
    }


    private AVLNode insert(AVLNode node, T key, V value) {
        if (node == null) {
            return new AVLNode(key, value);
        } else if (node.getKey().compareTo(key) > 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        } else if (node.getKey().compareTo(key) < 0) {
            node.setRight(insert(node.getRight(), key, value));
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    /**
     * helper method to delete node
     * deleted node and a specific key
     *
     * @param node takes a node node
     * @param key  takes a key where the node is supposed to be deleted
     * @return
     */
    private AVLNode delete(AVLNode node, T key) {
        if (node == null) {
            return node;
        } else if (node.getKey().compareTo(key) > 0) {
            node.setLeft(delete(node.getLeft(), key));
        } else if (node.getKey().compareTo(key) < 0) {
            node.setRight(delete(node.getRight(), key));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            } else {
                AVLNode smallestLeaf = smallestLeaf(node.getRight());
                node.setKey(smallestLeaf.getKey());
                node.setRight(delete(node.getRight(), node.getKey()));
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    /**
     * gets the smallest value in the tree
     *
     * @param node takes node
     * @return AVLNode returns the smallest left node
     */
    private AVLNode smallestLeaf(AVLNode node) {
        AVLNode current = node;
        /* loop to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    /**
     * @param imbalNode takes an imbalanced node that is to be balanced.
     * @return a balanced node afterwards after perform rotation operations.
     */
    private AVLNode rebalance(AVLNode imbalNode) {
        updateHeight(imbalNode);
        int balance = getBalance(imbalNode);
        if (balance > 1) {
            if (height(imbalNode.getRight().getRight()) > height(imbalNode.getRight().getLeft())) {
                imbalNode = rotateLeft(imbalNode);
            } else {
                imbalNode.setRight(rotateRight(imbalNode.right));
                imbalNode = rotateLeft(imbalNode);
            }
        } else if (balance < -1) {
            if (height(imbalNode.getLeft().getLeft()) > height(imbalNode.getLeft().getRight())) {
                imbalNode = rotateRight(imbalNode);
            } else {
                imbalNode.setLeft(rotateLeft(imbalNode.left));
                imbalNode = rotateRight(imbalNode);
            }
        }
        return imbalNode;
    }

    /**
     * imbalanced node that is to be rotated right
     *
     * @param imbalNode that is to be rotated
     * @return a right-rotated node
     */
    private AVLNode rotateRight(AVLNode imbalNode) {
        AVLNode newLeftNode = imbalNode.getLeft();
        AVLNode newRightNode = newLeftNode.getRight();
        newLeftNode.setRight(imbalNode);
        imbalNode.setLeft(newRightNode);
        updateHeight(imbalNode);
        updateHeight(newLeftNode);
        return newLeftNode;
    }

    /**
     * @param imbalNode is node that is to be rotated left
     * @return an AVLNode that has been left rotated
     */
    private AVLNode rotateLeft(AVLNode imbalNode) {
        AVLNode newRightNode = imbalNode.getRight();
        AVLNode newLeftNode = newRightNode.getLeft();
        newRightNode.setLeft(imbalNode);
        imbalNode.setRight(newLeftNode);
        updateHeight(imbalNode);
        updateHeight(newRightNode);
        return newRightNode;
    }

    /**
     * updates the height of a node
     *
     * @param node takes a node
     */
    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * @param node takes a node n
     * @return int height of the node
     */
    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

    /**
     * @param node takes a node node
     * @return int the balance factor of the node
     */
    public int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.getRight()) - height(node.getLeft());
    }

    /**
     * in-order traversal
     *
     * @return List<V> a list with values in ascending order
     */
    public List<V> inorderRec() {
        list.clear();
        if (root != null)
            return inorderRec(root);
        else
            return null;
    }

    /**
     * helper method for the inOrderRecursion method
     *
     * @param rootNode rootNode is the rootNode
     * @return a list which it passes to the main method
     */

    private List<V> inorderRec(AVLNode rootNode) {


        if (rootNode == null) {
            return null;
        }

        inorderRec(rootNode.getLeft());
        list.add(rootNode.value);
        inorderRec(rootNode.right);

        return list;


    }

    /**
     * returns the kth smallest value in a list obtained from the AVL tree
     *
     * @param k takes an int k which is the kth smallest value in the list
     * @return int which is the kth smallest value in the list
     */
    public V kthSmallest(int k) {
        if (root == null || k < 0) {
            //
        } else {
            ArrayList<V> arr = (ArrayList<V>) inorderRec();
            return arr.get(k - 1);

        }
        return null;
    }

    /**
     * main method of the class
     *
     * @param args takes the arguments and passes them into the main method.
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


        AVLTree<Integer, Double> tree2 = new AVLTree<>();

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
        AVLTree<Integer, String> tree3 = new AVLTree<>();
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
        AVLTree<Integer, Integer> tree1 = new AVLTree<>();
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
        AVLTree<Integer, Double> tree4 = new AVLTree<>();
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
        System.out.println("Elapsed Time after deleting keys in nanoseconds " + duration2);
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