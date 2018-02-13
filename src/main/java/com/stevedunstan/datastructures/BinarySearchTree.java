package com.stevedunstan.datastructures;

import static com.stevedunstan.datastructures.Color.BLACK;
import static com.stevedunstan.datastructures.Color.RED;

public class BinarySearchTree<Key extends Comparable<Key>,Value> {

    private Node root;

    private int size(Node node) {
        if (node == null) return 0;
        return node.getSubtreeSize();
    }

    // Rotate the node on my right to the left. Return the left rotated node.
    //     B
    //    / \
    //   A   D
    //      / \
    //     C   E
    // Becomes:
    //       D
    //      / \
    //     B   E
    //    / \
    //   A   C
    private Node rotateLeft(final Node node) {
        Node nodeRight = node.getRight();
        Color nodeColor = node.getColor();
        int nodeSubtreeSize = node.getSubtreeSize();

        Node nodeLeft = new Node(
                node.getKey(),
                node.getValue(),
                1 + size(node.getLeft()) + ((nodeRight == null) ? 0 : size(nodeRight.getLeft())),
                RED,
                node.getLeft(),
                ((nodeRight == null) ? null : nodeRight.getLeft()));

        nodeRight = new Node(
                nodeRight.getKey(),
                nodeRight.getValue(),
                nodeSubtreeSize,
                nodeColor,
                nodeLeft,
                nodeRight.getRight());

        return nodeRight;
    }

    // Make money-money. Make money-money. Wikki, wikki, wikki WHOOO!
    private Node rotateRight(final Node node) {
        Node nodeLeft = node.getLeft();
        Color nodeColor = node.getColor();
        int nodeSubtreeSize = node.getSubtreeSize();

        Node nodeRight = new Node(
                node.getKey(),
                node.getValue(),
                1 + size(node.getLeft()) + size(nodeLeft.getRight()),
                RED,
                nodeLeft.getRight(),
                node.getRight());

        nodeLeft = new Node(
                nodeLeft.getKey(),
                nodeLeft.getValue(),
                nodeSubtreeSize,
                nodeColor,
                nodeLeft.getLeft(),
                nodeRight);

        return nodeLeft;
    }

    /**
     * When two red children are detected, flip the parent to red and children to black.
     */
    private Node flipColors(Node node) {
        Node left = new Node(
                node.getLeft().getKey(),
                node.getLeft().getValue(),
                size(node.getLeft()),
                BLACK,
                node.getLeft().getLeft(),
                node.getLeft().getRight());
        Node right = new Node(
                node.getRight().getKey(),
                node.getRight().getValue(),
                size(node.getRight()),
                BLACK,
                node.getRight().getLeft(),
                node.getRight().getRight());
        return new Node(
                node.getKey(),
                node.getValue(),
                node.getSubtreeSize(),
                RED,
                left,
                right);
    }

    private boolean isRed(final Node node) {
        if (node == null) return false;
        return node.isRed();
    }

    private boolean isBlack(final Node node) {
        return !isRed(node);
    }

    private Node put(final Node node, final Key key, final Value value) {
        if (node == null)
            return new Node(key, value, 1, RED, null, null);

        Node newNode;

        int diff = key.compareTo(node.getKey());
        if (diff < 0) newNode = copyWithLeft(node, put(node.getLeft(), key, value));
        else if (diff > 0) newNode = copyWithRight(node, put(node.getRight(), key, value));
        else newNode = copyWithValue(node, value);

        // Now rotate, if necessary
        //if (isRed(newNode.getLeft()) && isBlack(newNode.getRight()))
        //    newNode = rotateLeft(newNode);
        //if (isRed(newNode.getLeft()) && isRed(newNode.getLeft().getLeft()))
        //    newNode = rotateRight(newNode);

        // Now flip colors, if necessary
        //if (isRed(newNode.getLeft()) && isRed(newNode.getRight()))
        //    newNode = flipColors(newNode);

        newNode = incrementSize(newNode);

        return newNode;
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int diff = key.compareTo(node.getKey());
        if      (diff < 0) return get(node.left, key);
        else if (diff > 0) return get(node.right, key);
        else               return node.getValue();
    }

    private Node incrementSize(Node node) {
        return new Node(node.getKey(),
                node.getValue(),
                size(node.getLeft()) + size(node.getRight()) + 1,
                node.getColor(),
                node.getLeft(),
                node.getRight());
    }

    private Node copyWithRight(Node node, Node right) {
        return new Node(node.getKey(),
                node.getValue(),
                node.getSubtreeSize(),
                node.getColor(),
                node.getLeft(),
                right);
    }

    private Node copyWithLeft(Node node, Node left) {
        return new Node(node.getKey(),
                node.getValue(),
                node.getSubtreeSize(),
                node.getColor(),
                left,
                node.getRight());
    }

    private Node copyWithValue(Node node, Value value) {
        return new Node(node.getKey(),
                value,
                node.getSubtreeSize(),
                node.getColor(),
                node.getLeft(),
                node.getRight());
    }

    // Public interface
    public int size() {
        if (root == null) return 0;
        return root.getSubtreeSize();
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private final class Node {
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

        public Key getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public boolean isRed() {
            return color == RED;
        }

        public Color getColor() {
            return color;
        }
    }
}
