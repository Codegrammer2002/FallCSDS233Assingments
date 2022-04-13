package Worksheets;


import org.w3c.dom.Node;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T> ,V> {

    public class BinaryNode<T,V> {
        private BinaryNode<T ,V> parent;

        T element;            // The data in the node
        BinaryNode<T, V> left;   // Left child
        BinaryNode<T, V> right;  // Right child
        // Constructors

        BinaryNode(T element) {
            this(element, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T,V> lt, BinaryNode<T,V> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }


    }


    private BinaryNode<T,V> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super T> c) {
        root = null;
        cmp = c;
    }

    private int myCompare(T lhs, T rhs) {
        if (cmp != null)
            return cmp.compare(lhs, rhs);
        else
            return ((Comparable) lhs).compareTo(rhs);
    }

    private boolean contains(T x, BinaryNode<T,V> t) {
        if (t == null)
            return false;

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true; // Match
    }


    /*
    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
  /*private BinaryNode<T,V> insert(T x, BinaryNode<T,V> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = myCompare((T) this, (T) t);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ; // Duplicate; do nothing
        return t;
    } */

    public static void main(String args[]) {

    }
}




