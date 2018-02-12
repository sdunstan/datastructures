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

}