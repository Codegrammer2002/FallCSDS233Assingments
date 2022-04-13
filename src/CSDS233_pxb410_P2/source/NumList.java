package CSDS233_pxb410_P2.source;

import CSDS233_pxb410_P1.source.NumArrayList;

public interface NumList  {
    boolean contains( double value);
    double lookup(int i);
    int size();
    int capacity();
    void add( double value);
    void insert( int i, double value);
    void remove(int i );
    void removeDuplicates();
    void reverse();
    void union(NumList list1, NumList list2);
    boolean isSorted();


}
