package tree;

public class BinarySearchTree {

    Node root;
    int prev = Integer.MIN_VALUE;
    int count = 0;
    Node prevNode;
    Node first;
    Node last;
    Node middle;

    static class Node {

        int data;
        BinarySearchTree.Node left;
        BinarySearchTree.Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public Node search(Node node, int key) {
        if (node == null || node.data == key) {
            return node;
        }
        return node.data < key ? search(node.right, key) : search(node.left, key);
    }

    public Node insert(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }

        if (node.data < key) {
            node.right = insert(node.right, key);
        } else {
            node.left = insert(node.left, key);
        }
        return node;
    }

    public int max(Node node) {
        if (node != null && node.right != null) {
            return max(node.right);
        } else {
            return node.data;
        }
    }

    public Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key > node.data) {
            node.right = delete(node.right, key);
        } else if (key < node.data) {
            node.left = delete(node.left, key);
        } else {
            if (node.left != null && node.right != null) {
                int leftMax = max(node.left);
                node.data = leftMax;
                node.left = delete(node.left, leftMax);
            } else if (node.left != null) {
                return node.left;
            } else if (node.right != null) {
                return node.right;
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean isBST(Node node) {
        if (node != null) {
            if (!isBST(node.left)) {
                return false;
            }
            if (node.data <= prev) {
                return false;
            }
            prev = node.data;
            if (!isBST(node.right)) {
                return false;
            }
        }

        return true;
    }

    public Node lca(Node node, int key1, int key2) {
        if (node == null) {
            return null;
        }
        if (node.data > key1 && node.data > key2) {
            return lca(node.left, key1, key2);
        } else if (node.data < key1 && node.data < key2) {
            return lca(node.right, key1, key2);
        } else {
            return node;
        }
    }

    public Node lcaIterative(Node node, int key1, int key2) {
        while (node != null) {
            int key = node.data;
            if (key > key1 && key > key2) {
                node = node.left;
            } else if (key < key1 && key < key2) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public Node kthSmallest(Node node, int k) {
        if (node == null) {
            return null;
        }
        Node left = kthSmallest(node.left, k);
        if (left != null) {
            return left;
        }
        count++;
        if (k == count) {
            return node;
        }
        return kthSmallest(node.right, k);
    }

    public void correctBSTUtil(Node node) {
        if (node != null) {
            correctBSTUtil(node.left);
            if (prevNode != null && node.data < prevNode.data) {
                if (first == null) {
                    first = prevNode;
                    middle = root;
                }
                if (last == null) {
                    last = root;
                }
            }
            prevNode = root;
            correctBSTUtil(node.right);
        }
    }

    public void correctBST(Node node) {
        first = last = middle = prevNode = null;
        correctBSTUtil(node);
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        } else if (first != null && middle != null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
        Node node1 = new Node(8);
        Node node2 = new Node(3);
        Node node3 = new Node(10);
        Node node4 = new Node(1);
        Node node5 = new Node(6);
        Node node6 = new Node(14);
        Node node7 = new Node(4);
        Node node8 = new Node(7);
        Node node9 = new Node(13);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node6;

        node6.left = node9;

        node5.left = node7;
        node5.right = node8;

        bst.root = node1;

        bst.root = bst.insert(bst.root, 17);

        System.out.println(bst.search(bst.root, 17));

        System.out.println(bst.delete(bst.root, 17));

        System.out.println(bst.isBST(bst.root));

        System.out.println(bst.lca(bst.root, 4, 10));

        System.out.println(bst.lcaIterative(bst.root, 4, 10));

        System.out.println("kth smallest - " + bst.kthSmallest(bst.root, 4));
    }
}
