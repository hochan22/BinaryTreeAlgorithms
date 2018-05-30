package Project;

import java.util.ArrayList;
import java.util.Random;

public class Test
{
    public static void main(String[] args)
    {
        StandardBinaryTree SBT = new StandardBinaryTree(100);
        AVLTree AVLT = new AVLTree();
        Treap TR = new Treap();
        int size = 500000;
        Random random = new Random();
        int[] keys = new int[size];
        for(int i = 0; i < size; i++)
        {
            keys[i] = random.nextInt(size);
        }
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            AVLT.insert(keys[i]);
        }
        long endTime = System.currentTimeMillis();
        long insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);


        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            SBT.insert(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);

        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            TR.insert(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);


        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            AVLT.search(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);



        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            SBT.search(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);

        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            TR.search(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);


        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            AVLT.delete(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);


        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            SBT.delete(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);



        startTime = System.currentTimeMillis();
        for(int i = 0; i < size; i++)
        {
            TR.delete(keys[i]);
        }
        endTime = System.currentTimeMillis();
        insertTimeSBT = (endTime - startTime);
        System.out.println(insertTimeSBT);

    }
}
