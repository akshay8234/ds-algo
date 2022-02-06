package linkedlist;

public class LinkedList {

    Node head;

    static class Node {

        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public void push(int data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    public void appendList(int data) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    public void insertAfterGivenNode(int data, Node node) {
        Node temp;
        Node newNode = new Node(data);
        if (node != null) {
            temp = node.next;
            node.next = newNode;
            newNode.next = temp;
        }
    }

    public void deleteNode(Node node) {
        Node temp = head, prev = null;

        if (temp != null && temp.data == node.data) {
            head = temp.next;
            return;
        }

        while (temp != null && temp.data != node.data) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;
    }

    public void printList() {
        Node temp = head;
        int length = 0;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
            length++;
        }
        System.out.print("null");
        System.out.println(" length of the linkedlist : " + length);
    }

    public int lengthRecursively(Node head) {
        if (head == null) {
            return 0;
        }
        return 1 + lengthRecursively(head.next);
    }

    public boolean searchElementRecursively(Node head, int data) {
        if (head == null) {
            return false;
        }
        if (head.data == data) {
            return true;
        }
        return searchElementRecursively(head.next, data);
    }

    public void nthNodeFromEnd(Node head, int n) {
        Node mainPointer = head;
        Node refPointer = head;
        int i = 0;
        while (refPointer != null && i < 3) {
            refPointer = refPointer.next;
            i++;
        }
        while (refPointer != null && refPointer.next != null) {
            refPointer = refPointer.next;
            mainPointer = mainPointer.next;
        }
        System.out.println(mainPointer.data);
    }

    public void findMid(Node head) {
        Node slow = head;
        Node fast = head;
        while (slow != null && slow.next != null && fast.next != null) {
            slow = slow.next;
            if (fast.next.next == null) {
                break;
            }
            fast = fast.next.next;
        }
        System.out.println("mid " + slow.data);
    }

    public int countOccurance(Node head, int data) {
        if (head.next == null) {
            return 0;
        }
        if (head.data == data) {
            return 1 + countOccurance(head.next, data);
        }
        return countOccurance(head.next, data);
    }

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        // create head node
        list.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        list.head.next = second;
        second.next = third;
        third.next = fourth;

        list.printList();
        list.push(5);
        list.printList();

        list.appendList(6);
        list.printList();

        list.insertAfterGivenNode(7, third);
        list.printList();
        list.findMid(list.head);

        list.deleteNode(list.head);
        list.printList();

        Node temp = list.head;
        System.out.println(list.lengthRecursively(temp));

        System.out.println(list.searchElementRecursively(temp, 7));

        list.nthNodeFromEnd(temp, 3);
        System.out.println("number of occurrence of 3" + list.countOccurance(list.head, 3));
    }
}
