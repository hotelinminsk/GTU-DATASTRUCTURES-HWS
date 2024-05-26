public class AVLTree {
    private int nodeCount;

    private class Node {
        Stock stock;
        Node left, right;
        int height;

        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }

        void copyStockData(Stock data) {
            this.stock.setSymbol(data.getSymbol());
            this.stock.setVolume(data.getVolume());
            this.stock.setPrice(data.getPrice());
            this.stock.setMarketCap(data.getMarketCap());
        }

        void setHeight(int h) { this.height = h; }

        void setRight(Node r) { this.right = r; }

        void setLeft(Node l) { this.left = l; }
    }

    private Node root;

    // Private height getter
    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    // Balance calculation method
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Private helper method to find maximum of given two integers
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    // Private right rotation method for balancing the tree
    private Node rightRotation(Node root) {
        Node leftChild = root.left;
        Node leftRightChild = leftChild.right;

        leftChild.right = root;
        root.left = leftRightChild;

        root.height = max(height(root.left), height(root.right)) + 1;
        leftChild.height = max(height(leftChild.left), height(leftChild.right)) + 1;

        return leftChild;
    }

    // Private helper method to rotate the subtree to left to balance the tree
    private Node leftRotation(Node root) {
        Node rightChild = root.right;
        Node rightLeftChild = rightChild.left;

        rightChild.left = root;
        root.right = rightLeftChild;

        root.height = max(height(root.left), height(root.right)) + 1;
        rightChild.height = max(height(rightChild.left), height(rightChild.right)) + 1;

        return rightChild;
    }

    // Private helper method to find the node with minimum value
    private Node minimumNode(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Balancing method
    private Node balance(Node node) {
        int balanceFactor = getBalance(node);

        // LEFT LEFT CASE
        if (balanceFactor > 1 && getBalance(node.left) >= 0) {
            return rightRotation(node);
        }

        // LEFT RIGHT CASE
        if (balanceFactor > 1 && getBalance(node.left) < 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        // RIGHT RIGHT CASE
        if (balanceFactor < -1 && getBalance(node.right) <= 0) {
            return leftRotation(node);
        }

        // RIGHT LEFT CASE
        if (balanceFactor < -1 && getBalance(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    // Insertion
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    private Node insert(Node node, Stock stock) {
        if (node == null) {
            return new Node(stock);
        }

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) {
            node.left = insert(node.left, stock);
        } else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0) {
            node.right = insert(node.right, stock);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        return balance(node);
    }

    // Deletion
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    private Node delete(Node node, String symbol) {
        if (node == null) {
            return null;
        }

        if (symbol.compareTo(node.stock.getSymbol()) < 0) {
            node.left = delete(node.left, symbol);
        } else if (symbol.compareTo(node.stock.getSymbol()) > 0) {
            node.right = delete(node.right, symbol);
        } else {
            if ((node.left == null) || (node.right == null)) {
                Node temp = node.left != null ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = minimumNode(node.right);
                node.copyStockData(temp.stock);
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        return balance(node);
    }

    // Search
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    private Node search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) {
            return node;
        }

        if (symbol.compareTo(node.stock.getSymbol()) < 0) {
            return search(node.left, symbol);
        } else {
            return search(node.right, symbol);
        }
    }

    // In-order traversal
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }

    // Pre-order traversal
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.stock);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // Post-order traversal
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.stock);
        }
    }
}
