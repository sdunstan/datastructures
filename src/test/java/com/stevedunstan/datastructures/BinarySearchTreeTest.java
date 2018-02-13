package com.stevedunstan.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

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

}