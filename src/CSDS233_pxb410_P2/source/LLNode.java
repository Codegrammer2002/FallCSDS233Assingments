package CSDS233_pxb410_P2.source;

/**
 * The node of a linked list
 */
public class LLNode {
    /** the element stored in the node */
    private double element;

    /** a reference to the next node of the list */
    private LLNode next;

    /**
     * the constructor
     *
     * @param element the element to store in the node
     * @param next    a reference to the next node of the list
     */
    public LLNode(double element, LLNode next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Returns the element stored in the node
     *
     * @return the element stored in the node
     */
    public double getElement() {
        return element;
    }

    /**
     * Returns the next node of the list
     *
     * @return the next node of the list
     */
    public LLNode getNext() {
        return next;
    }

    /**
     * Changes the node that comes after this node in the list
     *
     * @param next the node that should come after this node in the list. It can be
     *             null.
     */
    public void setNext(LLNode next) {
        this.next = next;
    }

}
