package Project;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;

public class StandardBinaryTree
{
    private TreeNode root;
    private int treeNodeNumber = 0;

    /**
     * constructor of the Standard Binary Tree with a root tree node
     * @param value is the value of the root to split the subtrees
     */
    public StandardBinaryTree(int value)
    {
        TreeNode treeNode = new TreeNode(value);
        this.root = treeNode;
    }

    /**
     * create a tree without elements
     */
    public void create()
    {
        treeNodeNumber++;
    }

    /**
     * search for certain key value in the standard binary tree
     * @param key the value to be searched
     * @return if find the key, return the node object with that value, if not find, return the parent node of that value
     */
    private boolean searchRecursive(TreeNode currentNode, int key)
    {
        if(currentNode == null)
        {
            return false;
        }
        if(key == currentNode.getValue())
        {
            return true;
        }
        return key < currentNode.getValue()? searchRecursive(currentNode.getLeftNode(),key) : searchRecursive(currentNode.getRightNode(),key);
    }

    public boolean search(int key)
    {
        return searchRecursive(root, key);
    }
    /**
     * insert a new key object into the standard binary tree
     * @param key the insert key value
     * @return if insert successfully, return true
     */
    private TreeNode insertRecursive(TreeNode current, int key) {
        if (current == null) {
            return new TreeNode(key);
        }

        if (key < current.getValue()) {
            current.setLeftNode(insertRecursive(current.getLeftNode(), key));
        } else if (key > current.getValue()) {
            current.setRightNode(insertRecursive(current.getRightNode(), key));
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }
    /*public boolean insert(int key)
    {
        boolean result = false;
        TreeNode treeNode = root;
        TreeNode nodeParent = root;
        while(treeNode != null)
        {
            if(key < treeNode.getValue())
            {
                nodeParent = treeNode;
                treeNode = treeNode.getLeftNode();
            }
            else if(key > treeNode.getValue())
            {
                nodeParent = treeNode;
                treeNode = treeNode.getRightNode();
            }
            else
            {
                System.out.println("the key exists");
                result = true;
                return result;
            }
        }
        if(key < nodeParent.getValue())
        {
            TreeNode newNode = new TreeNode(key);
            nodeParent.setLeftNode(newNode);
            result = true;
        }
        else
        {
            TreeNode newNode = new TreeNode(key);
            nodeParent.setRightNode(newNode);
            result = true;
        }
        return result;
    }
*/
    public int findMin(TreeNode node)
    {
        if(node.getLeftNode() == null)
        {
            return node.getValue();
        }
        return findMin(node.getLeftNode());
    }

    public TreeNode findMax(TreeNode node) {
        if(node == null)
        {
            return null;
        }
        else if(node.getRightNode().getRightNode() == null)
        {
            return node;
        }
        return findMax(node.getRightNode());
    }

    private TreeNode deleteRecursive(TreeNode currentNode, int key)
    {
        if(currentNode == null)
        {
            return null;
        }
        if(key == currentNode.getValue())
        {
            if(currentNode.getLeftNode() == null && currentNode.getRightNode() == null)
            {
                return null;
            }
            if(currentNode.getRightNode() == null)
            {
                return currentNode.getLeftNode();
            }
            if(currentNode.getLeftNode() == null)
            {
                return currentNode.getRightNode();
            }
            int minKey = findMin(currentNode.getRightNode());
            currentNode.setValue(minKey);
            currentNode.setRightNode(deleteRecursive(currentNode.getRightNode(), minKey));
            return currentNode;
        }
        if(key < currentNode.getValue())
        {
            currentNode.setLeftNode(deleteRecursive(currentNode.getLeftNode(), key));
            return currentNode;
        }
        currentNode.setRightNode(deleteRecursive(currentNode.getRightNode(), key));
        return currentNode;
    }

    public void delete(int key)
    {
        deleteRecursive(root, key);
    }
}
