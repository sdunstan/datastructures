package com.stevedunstan.datastructures;

public class BinarySearchTree<Key,Value> {

    private Node root;

    private Node rotateLeft(Node node) {
        return null;
    }

    private Node rotateRight(Node node) {
        return null;
    }

    private void flipColors(Node node) {

    }

    // Public interface
    public int size() {
        if (root == null) return 0;
        return root.getSubtreeSize();
    }

    public void put(Key key, Value value) {
        root = new Node(key, value, 1, Color.RED, null, null);
    }

    public Value get(Key key) {
        return root.getValue();
    }

    final class Node {
        private final Key key;
        private final Value value;
        private final Node left, right;
        private final int subtreeSize;
        private final Color color;

        public Node(Key key, Value value, int size, Color color, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.subtreeSize = size;
            this.color = color;
            this.left = left;
            this.right = right;
        }

        public int getSubtreeSize() {
            return subtreeSize;
        }

        public Value getValue() {
            return value;
        }

    }
}
