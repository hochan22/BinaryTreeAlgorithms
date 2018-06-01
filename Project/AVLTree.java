package Project;

public class AVLTree {

    private Node rootNode;


    public void insert(int key) {
        rootNode = insertNode(rootNode, key);
    }


    public void delete(int keyToRemove) {
        if (this.rootNode == null) {
            return;
        }
        rootNode = deleteNode(rootNode, keyToRemove);
    }

    public boolean search(int key) {
        return searchRecursive(rootNode, key);
    }


    private Node deleteNode(Node currentNode, int keyToRemove) {

        if (currentNode == null) {
            return null;
        }

        Node leftChild = currentNode.getLeftChild();
        Node rightChild = currentNode.getRightChild();
        int currentkey = currentNode.getkey();

        if (keyToRemove == currentkey) {


            if (leftChild == null && rightChild == null) {
                return null;
            } else if (leftChild == null) {

                currentNode = null;
                return rightChild;
            } else if (rightChild == null) {
                currentNode = null;
                return leftChild;
            } else {
                Node largestInLeftSubtree = getMaxNode(leftChild);

                currentNode.setkey(largestInLeftSubtree.getkey());
                currentNode.setLeftChild(deleteNode(currentNode.getLeftChild(), largestInLeftSubtree.getkey()));

            }
        } else if (keyToRemove < currentkey) {
            currentNode.setLeftChild(deleteNode(leftChild, keyToRemove));
        } else {
            currentNode.setRightChild(deleteNode(rightChild, keyToRemove));
        }

        currentNode.setHeight(calculateTreeHeight(currentNode));

        return balanceTreeAfterDeletion(currentNode);
    }

    private boolean searchRecursive(Node currentNode, int key) {
        if (currentNode == null) {
            return false;
        }
        if (key == currentNode.getkey()) {
            return true;
        }
        return key < currentNode.getkey() ? searchRecursive(currentNode.getLeftChild(), key) : searchRecursive(currentNode.getRightChild(), key);
    }

    private Node balanceTreeAfterDeletion(Node currentNode) {

        int balanceValue = getBalanceValue(currentNode);

        if (balanceValue > 1) {
            if (getBalanceValue(currentNode.getLeftChild()) < 0) {
                currentNode.setLeftChild(leftRotation(currentNode.getLeftChild()));
            }

            return rightRotation(currentNode);
        }

        if (balanceValue < -1) {
            if (getBalanceValue(currentNode.getRightChild()) > 0) {
                currentNode.setRightChild(rightRotation(currentNode.getRightChild()));
            }
            return leftRotation(currentNode);
        }

        return currentNode;
    }

    private Node getMaxNode(Node currentNode) {
        while (currentNode.getRightChild() != null) {
            currentNode = currentNode.getRightChild();
        }
        return currentNode;
    }

    private Node getMinNode(Node currentNode) {
        while (currentNode.getLeftChild() != null) {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode;
    }


    private int height(Node currentNode) {
        if (currentNode == null) {
            return -1;
        }
        return currentNode.getHeight();
    }

    private Node insertNode(Node currentNode, int keyToInsert) {


        if (currentNode == null) {
            return new Node(keyToInsert);
        }

        if (keyToInsert < currentNode.getkey()) {

            Node newLeftChild = new Node(keyToInsert);
            currentNode.setLeftChild(newLeftChild);
        } else {


            Node newRightChild = new Node(keyToInsert);
            currentNode.setRightChild(newRightChild);
        }

        currentNode = balanceTree(currentNode, keyToInsert);


        currentNode.setHeight(calculateTreeHeight(currentNode));

        return currentNode;
    }


    private Node balanceTree(Node currentNode, int keyToInsert) {

        int balanceValue = getBalanceValue(currentNode);

        if (isRightHeavy(balanceValue)
                && keyToInsert > currentNode.getRightChild().getkey()) {
            return leftRotation(currentNode);
        }

        if (isLeftHeavy(balanceValue)
                && keyToInsert < currentNode.getLeftChild().getkey()) {
            return rightRotation(currentNode);
        }

        if (isLeftHeavy(balanceValue) &&
                keyToInsert > currentNode.getLeftChild().getkey()) {
            currentNode.setLeftChild(insertNode(currentNode.getLeftChild(), keyToInsert));
            return rightRotation(currentNode);
        }

        if (isRightHeavy(balanceValue) &&
                (keyToInsert < currentNode.getLeftChild().getkey())) {
            currentNode.setRightChild(insertNode(currentNode.getRightChild(), keyToInsert));
            return leftRotation(currentNode);
        }

        return currentNode;
    }

    private int getBalanceValue(Node currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return height(currentNode.getLeftChild()) - height(currentNode.getRightChild());
    }


    private boolean isBalanced(Node currentNode) {
        return Math.abs(getBalanceValue(currentNode)) < 2;
    }

    private boolean isBalanced(int balanceValue) {
        return balanceValue < 2 && balanceValue > -2;
    }


    private boolean isLeftHeavy(int balanceValue) {
        return balanceValue > 1;
    }


    private boolean isRightHeavy(int balanceValue) {
        return balanceValue < -1;
    }


    private int calculateTreeHeight(Node currentNode) {
        return Math.max(height(currentNode.getLeftChild()), height(currentNode.getRightChild())) + 1;
    }

    private Node rightRotation(Node currentNode) {
        System.out.println("Beginning right rotation ... on node: " + currentNode.getkey());

        Node newRootNode = currentNode.getLeftChild();
        Node rightChildOfLeft = newRootNode.getRightChild();

        newRootNode.setRightChild(currentNode);

        currentNode.setLeftChild(rightChildOfLeft);

        newRootNode.setHeight(calculateTreeHeight(newRootNode));
        currentNode.setHeight(calculateTreeHeight(currentNode));

        return newRootNode;
    }

    private Node leftRotation(Node currentNode) {

        System.out.println("Beginning left rotation ... on node: " + currentNode.getkey());

        Node newRootNode = currentNode.getRightChild();
        Node leftChildOfRight = newRootNode.getLeftChild();

        newRootNode.setLeftChild(currentNode);

        currentNode.setRightChild(leftChildOfRight);

        newRootNode.setHeight(calculateTreeHeight(newRootNode));
        currentNode.setHeight(calculateTreeHeight(currentNode));

        return newRootNode;
    }



}


class Node {
    private int key;
    private Node leftChild;
    private Node rightChild;
    private int height;

    Node(int key) {
        this.key = key;
        leftChild = null;
        rightChild = null;
        height = 0;
    }

    public int getkey() {
        return key;
    }

    public void setkey(int key) {
        this.key = key;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                '}';
    }
}
