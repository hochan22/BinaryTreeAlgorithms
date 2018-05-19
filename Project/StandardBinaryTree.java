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
    public TreeNode search(int key)
    {
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
                System.out.println("Find the search key " + key);
                return treeNode;
            }
        }
        System.out.println("There is no such tree node with key " + key);
        return nodeParent;
    }

    /**
     * insert a new key object into the standard binary tree
     * @param key the insert key value
     * @return if insert successfully, return true
     */
    public boolean insert(int key)
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


}
