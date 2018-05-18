package Project;

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
    public TreeNode create()
    {
        treeNodeNumber++;
        return root;
    }

    public TreeNode search(int key)
    {
        TreeNode treeNode = root;
        while(treeNode != null)
        {
            if(k < treeNode.getValue())
            {
                treeNode = treeNode.getLeftNode();
            }
            else if(k > treeNode.getValue())
            {
                treeNode = treeNode.getRightNode();
            }
            else
            {
                return treeNode;
            }
        }
        System.out.print("There is no such tree node with key " + key);
    }

}
