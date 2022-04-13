package CSDS233_pxb410_P4.source;



/**
 * This is the Hash Table implemented from Scratch.
 * Uses a helper class HashEntry to make entries in the HashTable
 */

public class HashTable  {
    // private fields
    protected HashEntry[] table;
    private int tableSize;

    // this is a helper class to be used in the hash table
    public class HashEntry {
        // private fields
        private String key;
        private int value;


        // constructor
        public HashEntry(String key, int value) {
            this.key = key;
            this.value = value;
        }

        // getter setter methods
        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * First constructor of the HashTable takes no input and initializes a table size.
     *
     */
    public HashTable() {

        tableSize = 1000;
        table = new HashEntry[tableSize];
    }

    /**
     * Second constructor of the HashTable.
     *
     * @param size takes an int which is the size of the hashtable.
     */
    public HashTable(int size) {
        tableSize = size;
        table = new HashEntry[size];
    }
    // gets the size the table
    public int size() {
        return tableSize;

    }

    /**
     * put method to add key-value pairs to the HashTable
     * @param key takes a String key which is later hashed.
     * @param value taken an int value.
     */
    public void put(String key, int value) {
        try {

            int i = probe(key, Math.abs(key.hashCode()));
            if (i == -1) {
                rehash();
                i = probe(key, Math.abs(key.hashCode()));
            }
            table[i] = new HashEntry(key, value);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *  put method with the hashcode
     * @param key takes a key which is later hashed.
     * @param value takes a value.
     * @param hashCode takes a hashCode.
     */
    public void put(String key, int value, int hashCode) {
        int i = probe(key, Math.abs(hashCode));
        if (i == -1) {
            rehash();
            i = probe(key, Math.abs(hashCode()));
        }
        table[i] = new HashEntry(key, value);

    }

    /**
     * update method to update key-value pairs
     * @param key takes a key which is to be updated
     * @param value the new value which is to be udated.
     */
    public void update(String key, int value) {
        try {
            int i = find(key, Math.abs(key.hashCode()));
            if (i != -1) {
                table[i].setValue(value);
            } else
                put(key, value);
        }
        catch (NullPointerException e){
            System.out.println("Key cant' be null");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * the get function for the hash table gets value of the entered key.
     * @param key the key for the key value pair required to get the value
     * @return value returns the value
     */
    public int get(String key) {
        int i = find(key, Math.abs(key.hashCode()));
        if (i == -1) {
            return -1;
        }
        return table[i].getValue();
    }

    /**
     * extended get function
     * @param key the key for the key value pair required to get the value
     * @param hashCode the hashCode with which the key is hashed
     * @return value returns an int which is the value
     */
    public int get(String key, int hashCode) {
        int i = find(key, Math.abs(key.hashCode()));
        // if the key is equal to -1 it returns -1
        if (i == -1) {
            return -1;
        }
        return table[i].getValue();
    }

    /**
     * Linear Probing to prevent collisions
     * @param key takes
     * @param hashCode hashCode
     * @return returns a free index where the value can be inserted.
     */
    private int probe(String key, int hashCode) {
        int i = Math.abs(hashCode) % table.length;
        int iterations = 0;

        while (table[i] != null) {
            i = (i + 1) % table.length;
            if (iterations++ > table.length) {
                return -1;
            }
        }
        return i;
    }

    // rehasing the table
    private void rehash() {
        int oldSize = tableSize;
        HashEntry[] oldTable = table;
        // uses next prime
        tableSize = nextPrime(2 * oldSize);
        table = new HashEntry[tableSize];
        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] != null) {
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }

    /**
     * Gives us the next prime after an input num
     * @param num takes a num and gives the next prime after than num. used in resizing the table
     * @return returns an int which is a prime
     */
    private int nextPrime(int num) {

        num++;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                num++;
                i = 2;
            } else {
                continue;
            }
        }
        return num;
    }

    /**
     * helper method to find keys/indices
     * @param key takes a key
     * @param hashCode takes a hashcode
     * @return an int
     */

    private int find(String key, int hashCode) {
        int i = Math.abs(hashCode) % tableSize;
        int iterations = 0;
        while (table[i] != null) {

            if (table[i].getKey().equals(key)) {
                return i;
            }
            i = (i + 1) % table.length;
            if (iterations++ > table.length) {

            }
        }
        return -1;
    }




}