package Project;
import java.util.Random;
public class Treap
{
    private static final Random rand = new Random();
    private Node root;

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (data < node.data) {
            node.left = insert(node.left, data);
            if (node.priority > node.left.priority)
                return rotateRight(node);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
            if (node.priority > node.right.priority)
                return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node node) {
        Node lnode = node.left;
        node.left = lnode.right;
        lnode.right = node;
        return lnode;
    }

    private Node rotateLeft(Node node) {
        Node rnode = node.right;
        node.right = rnode.left;
        rnode.left = node;
        return rnode;
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node node, int data) {
        if (node != null) {
            if (data < node.data) {
                node.left = delete(node.left, data);
            } else if (data > node.data) {
                node.right = delete(node.right, data);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    node.data = first(node.right);
                    node.right = delete(node.right, node.data);
                }
            }
        }
        return node;
    }

    public boolean search(int data) {
        Node node = root;
        while (node != null) {
            if (data < node.data) node = node.left;
            else if (data > node.data) node = node.right;
            else return true;
        }
        return false;
    }

    public int first() {
        return first(root);
    }

    private int first(Node searchNode) {
        Node node = searchNode;
        while (node.left != null) node = node.left;
        return node.data;
    }
/*
    @Override
    public String toString() {
        return "Treap{" +
                "root=" + root +
                '}';
    }
*/
    private static class Node {
        public Node right, left;
        public final int priority = rand.nextInt(500000);
        public int data;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + data +
                    ", priority=" + priority +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
