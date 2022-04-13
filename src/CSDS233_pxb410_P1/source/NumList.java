package CSDS233_pxb410_P1.source;

/** NumList interface with the required and additional methods.
 * @author parvbhardwaj
 *
 */
public interface NumList  {
   boolean contains( double value);
   double lookup(int i);
   int size();
   int capacity();
   void add( double value);
   void insert( int i, double value);
   void remove(int i );
   boolean equals(NumList otherList);
   void removeDuplicates();
   void reverse();
   void union(NumList list1, NumList list2);
}
