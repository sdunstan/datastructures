package com.stevedunstan.datastructures;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private static final Logger LOG = Logger.getLogger(BinarySearchTreeTest.class.getName());

    @Test
    void putAndGetRoot() {
        BinarySearchTree bst = new BinarySearchTree<String, String>();
        assertEquals(0, bst.size());
        bst.put("key", "value");
        assertEquals(1, bst.size());
        assertEquals("value", bst.get("key"));
    }

    @Test
    void putAndGetStrings() {
        BinarySearchTree bst = new BinarySearchTree<String,String>();
        bst.put("B", "0");
        bst.put("S", "1");
        bst.put("E", "2");
        bst.put("A", "3");
        bst.put("R", "4");
        bst.put("C", "5");
        bst.put("H", "6");

        assertEquals(7, bst.size());
        assertEquals("0", bst.get("B"));
        assertEquals("1", bst.get("S"));
        assertEquals("2", bst.get("E"));
        assertEquals("3", bst.get("A"));
        assertEquals("4", bst.get("R"));
        assertEquals("5", bst.get("C"));
        assertEquals("6", bst.get("H"));
    }

    @Test
    void performanceTest() {
        // Putting ordered items into an unbalanced binary search tree results
        // in O(N) worst-case search time on average. We are trying to get to O(2 lg N).

        // In addition, the recursive algorithms for the put and get suffer
        // stack overflows when even a moderate number of ordered items are added.

        // To test, put a whole bunch of stuff in order in the BST and get the
        // largest one a whole bunch of times. If the BST is not balanced
        // we should expect that this takes a lot longer than getting the
        // first one.
        final int LO = 20000;
        final int HI = LO*2;

        final String HI_KEY = "" + (HI-1);
        final Integer HI_VAL = new Integer(HI-1);
        final String LO_KEY = "" + LO;
        final Integer LO_VAL = LO;

        BinarySearchTree bst = new BinarySearchTree<String,Integer>();
        long startTime = System.currentTimeMillis();

        for (int i = LO; i < HI; i++) {
            bst.put(""+i, i);
        }

        long putTime = System.currentTimeMillis();
        LOG.info("Put time millis: " + (putTime-startTime));

        for (int i = LO; i < HI; i++) {
            Object val = bst.get(HI_KEY);
            assertEquals(HI_VAL, val);
        }

        long getLastTime = System.currentTimeMillis();
        LOG.info("Get largest time millis: " + (getLastTime-putTime));

        for (int i = 1000000; i < 2000000; i++) {
            Object val = bst.get(LO_KEY);
            assertEquals(LO_VAL, val);
        }

        long endTime = System.currentTimeMillis();
        LOG.info("Get smallest time millis: " + (endTime-getLastTime));
    }

}