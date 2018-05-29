package Project;

import java.util.ArrayList;

public class Test
{
    public static void main(String[] args)
    {
        int value = 10;
        StandardBinaryTree SBT = new StandardBinaryTree(value);
        SBT.insert(5);
        boolean result1 = SBT.search(5);
        System.out.println(result1);
        SBT.insert(20);
        boolean result2 = SBT.search(20);
        System.out.println(result2);
        boolean result3 = SBT.search(30);
        System.out.println(result3);
        SBT.delete(5);
        result1 = SBT.search(5);
        System.out.println(result1);
        SBT.insert(4);
        SBT.insert(7);
        SBT.insert(15);
        SBT.insert(14);
        SBT.insert(9);
        SBT.insert(21);
        SBT.insert(18);
        SBT.insert(1);
        SBT.insert(2);
        boolean result4 = SBT.search(18);
        System.out.println(result4);
        SBT.delete(14);
        boolean result5 = SBT.search(14);
        System.out.println(result5);
        //System.out.print(node.getValue());
    }
}
