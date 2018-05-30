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

    /*
    public void traverse() {
        if (isEmpty()) {
            return;
        }
        preOrderTraversal(this.rootNode);
    }
    */

    /*
    public boolean isEmpty() {
        return this.rootNode == null;
    }
    */


    /*
     * ==============================
     * =    Private methods here    =
     * ==============================
     * */

    /**
     * Recursive implementation of removing key from a tree
     * Three cases:
     * 1. No child nodes.
     * 2. Single child.
     * 3. Two children.
     *
     * @return the node that is to be removed. Return null if no key is removed.
     */
    private Node deleteNode(Node currentNode, int keyToRemove) {

        // Base case
        if (currentNode == null) {
            return null;
        }

        Node leftChild = currentNode.getLeftChild();
        Node rightChild = currentNode.getRightChild();
        int currentkey = currentNode.getkey();

        if (keyToRemove == currentkey) {

            //System.out.println("Found the key that we want to remove: " + currentkey);

            if (leftChild == null && rightChild == null) {
                //System.out.println("Removing a leaf node");
                return null;
            } else if (leftChild == null) {
                //System.out.println("Removing a node with a right child");
                currentNode = null;
                return rightChild;
            } else if (rightChild == null) {
                //System.out.println("Removing a node with a left child");
                currentNode = null;
                return leftChild;
            } else {
                //System.out.println("Removing a node with two children");
                // Find the largest node on the left sub-tree
                Node largestInLeftSubtree = getMaxNode(leftChild);

                // Swap the root node with the largest in left sub-tree
                currentNode.setkey(largestInLeftSubtree.getkey());
                // Set left-child recursively. Remove the copy left of the largest left child
                currentNode.setLeftChild(deleteNode(currentNode.getLeftChild(), largestInLeftSubtree.getkey()));

            }
        } else if (keyToRemove < currentkey) {
            //System.out.println("Traversing to the left ---");
            currentNode.setLeftChild(deleteNode(leftChild, keyToRemove));
        } else {
            //System.out.println("Traversing to the right ---");
            currentNode.setRightChild(deleteNode(rightChild, keyToRemove));
        }

        // Update the height parameter
        currentNode.setHeight(calculateTreeHeight(currentNode));

        // Check on every delete operation whether tree has become unbalanced
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

    /**
     * Check whether the tree is unbalanced after a delete operation
     *
     * @return Node The node that is deleted.
     */
    private Node balanceTreeAfterDeletion(Node currentNode) {

        int balanceValue = getBalanceValue(currentNode);

        // Left heavy situation. Can be left-left or left-right
        if (balanceValue > 1) {
            // Left-right rotation required. Left rotation on the right child of the root node.
            if (getBalanceValue(currentNode.getLeftChild()) < 0) {
                currentNode.setLeftChild(leftRotation(currentNode.getLeftChild()));
            }

            return rightRotation(currentNode);
        }

        // Right heavy situation. Can be right-right or right-left
        if (balanceValue < -1) {
            // right - left situation. Left rotation on the right child of the root node.
            if (getBalanceValue(currentNode.getRightChild()) > 0) {
                currentNode.setRightChild(rightRotation(currentNode.getRightChild()));
            }
            // left rotation on the root node
            return leftRotation(currentNode);
        }

        return currentNode;
    }

    /**
     * Get the maximum node is a particular sub-tree
     *
     * @param currentNode the root node of the sub-tree
     *                    we will be examining
     */
    private Node getMaxNode(Node currentNode) {
        while (currentNode.getRightChild() != null) {
            currentNode = currentNode.getRightChild();
        }
        return currentNode;
    }

    /**
     * Get the minimum node in a particular sub-tree
     *
     * @param currentNode the root node of the sub-tree
     *                    we will be examining
     */
    private Node getMinNode(Node currentNode) {
        while (currentNode.getLeftChild() != null) {
            currentNode = currentNode.getLeftChild();
        }
        return currentNode;
    }

    /**
     * Used internally
     * <p>
     * Height is calculated via the following formula
     * height = max (leftSubTreeHeight, rightSubTreeHeight) + 1
     *
     * @return -1 if the node is null.  Otherwise, return the height.
     */
    private int height(Node currentNode) {
        if (currentNode == null) {
            return -1;
        }
        return currentNode.getHeight();
    }

    /**
     * Implement recursively to avoid storing a node pointer to the parent.
     * Remember that in the JavaScript implementation of the Binary Search Tree,
     * we required the pointer to the parent.
     * <p>
     * This method will ALWAYS return the root.
     *
     * @param currentNode
     * @param keyToInsert
     */
    private Node insertNode(Node currentNode, int keyToInsert) {

        // The current root node is empty. Create a new node here
        if (currentNode == null) {
            return new Node(keyToInsert);
        }

        // Is key to insert smaller than the current key value.
        // Go to the left.
        if (keyToInsert < currentNode.getkey()) {
            //currentNode.setLeftChild(insertNode(currentNode.getLeftChild(), keyToInsert));
            Node newLeftChild = new Node(keyToInsert);
            currentNode.setLeftChild(newLeftChild);
        } else {
            //currentNode.setRightChild(insertNode(currentNode.getRightChild(), keyToInsert));

            Node newRightChild = new Node(keyToInsert);
            currentNode.setRightChild(newRightChild);
        }

        currentNode = balanceTree(currentNode, keyToInsert);

        // Finally, update the height calculateTreeHeight(rootNode)
        currentNode.setHeight(calculateTreeHeight(currentNode));

        return currentNode;
    }

    /**
     * After the insertion/removal method, check to see if tree is balanced.
     * Check for the following four cases
     * 1. Left rotation       - right heavy situation
     * 2. Right rotation      - Left heavy situation
     * 3. Left-right rotation - right heavy situation
     * 4. Right-left rotation - Left heavy situation
     */
    private Node balanceTree(Node currentNode, int keyToInsert) {

        int balanceValue = getBalanceValue(currentNode);

        // Right heavy situation - left rotation
        if (isRightHeavy(balanceValue)
                && keyToInsert > currentNode.getRightChild().getkey()) {
            return leftRotation(currentNode);
        }

        // Left heavy situation - Right rotation
        if (isLeftHeavy(balanceValue)
                && keyToInsert < currentNode.getLeftChild().getkey()) {
            return rightRotation(currentNode);
        }

        // Left right
        if (isLeftHeavy(balanceValue) &&
                keyToInsert > currentNode.getLeftChild().getkey()) {
            currentNode.setLeftChild(insertNode(currentNode.getLeftChild(), keyToInsert));
            return rightRotation(currentNode);
        }

        // right-left
        if (isRightHeavy(balanceValue) &&
                (keyToInsert < currentNode.getLeftChild().getkey())) {
            currentNode.setRightChild(insertNode(currentNode.getRightChild(), keyToInsert));
            return leftRotation(currentNode);
        }

        return currentNode;
    }

    /**
     * Get the balance value of the current node. If difference between the height
     * of the left and right subtree is greater than 1, that means it is skewed disproportionately
     * to the left. It is therefore left heavy situation.
     * If however, the difference is -2 or less, that means it is skewed disproportionately
     * to the right, meaning it is a right heavy situation.
     * Otherwise, it is balanced.
     */
    private int getBalanceValue(Node currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return height(currentNode.getLeftChild()) - height(currentNode.getRightChild());
    }

    /**
     * Check if the current sub-tree is balanced. If difference between the height
     * of the left and right subtree is 1 or zero, then it is balanced.
     * Otherwise, it is unbalanced.
     *
     * @param currentNode
     */
    private boolean isBalanced(Node currentNode) {
        return Math.abs(getBalanceValue(currentNode)) < 2;
    }

    /**
     * Check to see if current element is balanced based on balance value
     *
     * @param balanceValue
     */
    private boolean isBalanced(int balanceValue) {
        return balanceValue < 2 && balanceValue > -2;
    }

    /**
     * Check if tree is left heavy based on balance value.
     * Left heavy trees have more items on the left sub-tree than the right.
     */
    private boolean isLeftHeavy(int balanceValue) {
        return balanceValue > 1;
    }

    /**
     * Check if tree is right heavy based on balance value\
     * Right heavy trees have more items on the right sub-tree than the left.
     */
    private boolean isRightHeavy(int balanceValue) {
        return balanceValue < -1;
    }

    /**
     * Get the height of the tree and increment it by 1 to get the actual height.
     * Get it by taking the greater value between the height of the left and right sub-tree
     * and insert one to that value.
     * Null pointers return a height of zero, because -1 + 1 = 0.
     */
    private int calculateTreeHeight(Node currentNode) {
        return Math.max(height(currentNode.getLeftChild()), height(currentNode.getRightChild())) + 1;
    }

    /**
     * A recursive implementation of the right rotation.
     * We will be utilizing the pseudo code that we wrote in the post
     */
    private Node rightRotation(Node currentNode) {
        System.out.println("Beginning right rotation ... on node: " + currentNode.getkey());

        Node newRootNode = currentNode.getLeftChild();
        Node rightChildOfLeft = newRootNode.getRightChild();

        // Step 1. Set newRootNode as the new root node.
        newRootNode.setRightChild(currentNode);

        // Step 2. Set the right child of the new left child of the new root node. Quite a tongue twister right?
        currentNode.setLeftChild(rightChildOfLeft);

        // Step 3. Update the height of the trees that were updated.
        newRootNode.setHeight(calculateTreeHeight(newRootNode));
        currentNode.setHeight(calculateTreeHeight(currentNode));

        return newRootNode;
    }

    private Node leftRotation(Node currentNode) {

        System.out.println("Beginning left rotation ... on node: " + currentNode.getkey());

        Node newRootNode = currentNode.getRightChild();
        Node leftChildOfRight = newRootNode.getLeftChild();

        // Step 1. set the left child of the new root node
        newRootNode.setLeftChild(currentNode);

        // Step 2. set the right child of the new left child
        currentNode.setRightChild(leftChildOfRight);

        // Step 3. Update the height of the trees that were updated.
        newRootNode.setHeight(calculateTreeHeight(newRootNode));
        currentNode.setHeight(calculateTreeHeight(currentNode));

        return newRootNode;
    }


    /**
     * In order traversal implementation
     * 1. Visit left sub-tree
     * 2. Visit root
     * 3. Visit right sub-tree
     *
     * @param currentNode the node that we are currently at
     * */
    /*
    private void inOrderTraversal(Node currentNode) {

        Node leftChild = currentNode.getLeftChild();

        if (leftChild != null) {
            inOrderTraversal(leftChild);
        }

        System.out.print(currentNode + " --> ");

        Node rightChild = currentNode.getRightChild();

        if (rightChild != null) {
            inOrderTraversal(rightChild);
        }

    }

*/
    /**
     * Pre order traversal implementation
     * 1. Visit root
     * 2. Visit left sub-tree
     * 3. Visit right sub-tree
     *
     * @param currentNode the node that we are currently at
     * */
    /*
    private void preOrderTraversal(Node currentNode) {

        System.out.print(currentNode + " --> ");

        Node leftChild = currentNode.getLeftChild();

        if (leftChild != null) {
            preOrderTraversal(leftChild);
        }

        Node rightChild = currentNode.getRightChild();

        if (rightChild != null) {
            preOrderTraversal(rightChild);
        }
    }*/

    /**
     * Post order traversal
     * 1. Visit the left sub-tree
     * 2. Visit the right sub-tree
     * 3. Visit root
     *
     * @param currentNode the node that we are currently at
     * */
    /*
    private void postOrderTraversal(Node currentNode) {
        Node leftChild = currentNode.getLeftChild();

        if (leftChild != null) {
            preOrderTraversal(leftChild);
        }

        Node rightChild = currentNode.getRightChild();

        if (rightChild != null) {
            preOrderTraversal(rightChild);
        }

        System.out.print(currentNode + " --> ");
    }*/

}

    /**
     * The node class for the tree.
     * Make sure that it can only be instantiated by classes inside of this package.
     */
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
