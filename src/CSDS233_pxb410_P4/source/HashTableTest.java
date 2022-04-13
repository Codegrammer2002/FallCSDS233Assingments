package CSDS233_pxb410_P4.source;
/* This is the testing class for the HashTable class */
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    // testing the size method
    @Test
    public void sizeTest() {
        HashTable hashTable1 = new HashTable(11);
        assertEquals(11, hashTable1.size());
    }

    // testing the put method of the hash table
    @Test
    public void putTest() {
        HashTable hashTable2 = new HashTable(11);
        try {
            hashTable2.put(null, 2);
        }
        catch(IllegalArgumentException e) {

        }
        hashTable2.put("Hello", 10);
        // should return 10 because value is 10
        assertEquals(10, hashTable2.get("Hello"));


    }
    // testing the get method of the hash table.
    @Test
    public void getTest() {
        // creating a new hash table to test the get method.
        HashTable hashTable3 = new HashTable();
        // putting a key with a value 2
        hashTable3.put("test", 2);
        // should return 2 because "test" is on index 2
        assertEquals(2, hashTable3.get("test"));

        // Checking for the null case.
        try{
            hashTable3.get(null);

        }
        catch (Exception e){
            System.out.println("They key can't be null");
        }
        // the code should try catch the null pointer exception.
        try{
            hashTable3.get(null);

        }
        catch (Exception e){
            System.out.println("The key can't be null.");
        }
        //the key does not exist so get() should return -1
        assertEquals(-1, hashTable3.get("notTest"));
    }

    /**
     * Test to check update method of the hash table
     */
    @Test
    public void updateTest() {
        HashTable updatedHashTable = new HashTable(10);
        // catches exceptions  mainly when the update key is null.
        try{
            updatedHashTable.update(null , 5);
        }
        catch (Exception e){
            System.out.println("The key can't be null");
        }

        updatedHashTable.put("test" , 2);
        assertEquals(2 , updatedHashTable.get("test"));
        updatedHashTable.update("test", 10);

        assertEquals(10 , updatedHashTable.get("test"));


    }
}