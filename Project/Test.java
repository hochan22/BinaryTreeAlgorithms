package Project;

import java.util.ArrayList;

public class Test
{
    public static void main(String[] args)
    {
        int value = 10;
        StandardBinaryTree SBT = new StandardBinaryTree(value);
        TreeNode node1 = SBT.search(5);
        TreeNode node2 = SBT.search(10);
        boolean result = SBT.insert(5);
        TreeNode node3 = SBT.search(5);
        boolean result2 = SBT.insert(25);
        TreeNode node4 = SBT.search(25);
        System.out.println(node4.getValue());
        boolean result3 = SBT.insert(1);
        TreeNode node5= SBT.search(1);
        System.out.print(node5.getValue());
        //System.out.print(node.getValue());
    }
}
