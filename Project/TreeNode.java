package Project;

import java.util.ArrayList;

public class TreeNode
{
    private TreeNode leftNode;
    private TreeNode rightNode;
    private int value;

    /**
     * TreeNode class' constructor with initial value
     *
     * @param value Initial value for this TreeNode
     */
    public TreeNode(int value)
    {
        leftNode = null;
        rightNode = null;
        this.value = value;
    }

    /**
     * return the value of this TreeNode
     *
     * @return the value of this TreeNode
     */
    public int getValue()
    {
        return value;
    }

    /**
     * set the value of this TreeNode after the construction
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * return the left child node
     * @return the left child node
     */
    public TreeNode getLeftNode()
    {
        return leftNode;
    }

    /**
     * set the left child node with the input tree node
     * @param treeNode the input left child node
     */
    public void setLeftNode(TreeNode treeNode)
    {
        leftNode = treeNode;
    }

    /**
     * return the right child node
     * @return the right child node
     */
    public TreeNode getRightNode()
    {
        return rightNode;
    }

    /**
     * set the right child node with the input tree node
     * @param treeNode the input right child node
     */
    public void setRightNode(TreeNode treeNode)
    {
        rightNode = treeNode;
    }


}
