package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    Node root;

    static class Node {

        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        // calculate left and right height individually and consider the max of them
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void printCurrentLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }

    public void printLevelOrder(Node node) {
        int height = height(node);
        for (int i = 1; i <= height; i++) {
            printCurrentLevel(node, i);
        }
        System.out.println();
    }

    public void printLevelOrderUsingQueue(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        System.out.println();
    }

    public List<List<Integer>> levelOrderUsingQueue(Node node) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                Node temp = queue.peek();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                tempList.add(queue.poll().data);
            }
            list.add(tempList);
        }
        return list;
    }

    public List<List<Integer>> zigZagLevelOrderUsingQueue(Node node) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        boolean rtl = true;
        while (!queue.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();
            if (rtl) {
                for (int i = 0; i < queue.size(); i++) {
                    Node temp = queue.poll();
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    tempList.add(temp.data);
                }
                rtl = false;
            } else {
                for (int i = 0; i < queue.size(); i++) {
                    Node temp = queue.poll();
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                    tempList.add(temp.data);
                }
                rtl = true;
            }
            list.add(tempList);
        }
        return list;
    }

    public int diameter1(Node node) {
        if (node == null) {
            return 0;
        }
        int ld = diameter1(node.left);
        int rd = diameter1(node.right);

        int lrd = height(node.left) + height(node.right) + 1;

        return Math.max(lrd, Math.max(ld, rd));
    }

    static class DiameterPair {
        int height, diameter;
    }

    public DiameterPair diameter2(Node node) {
        if (node == null) {
            return new DiameterPair();
        }
        DiameterPair lp = diameter2(node.left);
        DiameterPair rp = diameter2(node.right);

        DiameterPair pair = new DiameterPair();
        pair.height = Math.max(lp.height, rp.height) + 1;

        int lrd = lp.height + rp.height + 1;
        pair.diameter = Math.max(lrd, Math.max(lp.diameter, rp.diameter));
        return pair;
    }

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        tree.printLevelOrder(tree.root);
        tree.printLevelOrderUsingQueue(tree.root);
        System.out.println(tree.levelOrderUsingQueue(tree.root));
        System.out.println(tree.zigZagLevelOrderUsingQueue(tree.root));

        System.out.println(tree.diameter1(tree.root));
        System.out.println(tree.diameter2(tree.root).diameter);
    }
}
