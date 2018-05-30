package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test
{
    public static void main(String[] args)
    {
        int times = 10;
        long[] SBTinsearts = new long[times];
        long[] AVLTinsearts = new long[times];
        long[] TRinsearts = new long[times];
        long[] SPTinsearts = new long[times];
        long[] SBTsearchs = new long[times];
        long[] AVLTsearchs = new long[times];
        long[] TRsearchs = new long[times];
        long[] SPTsearchs = new long[times];
        long[] SBTdeletes = new long[times];
        long[] AVLTdeletes = new long[times];
        long[] TRdeletes = new long[times];
        long[] SPTdeletes = new long[times];



        for(int j = 0; j < times; j++)
        {
            int size = 1000000;
            StandardBinaryTree SBT = new StandardBinaryTree(size/2);
            AVLTree AVLT = new AVLTree();
            Treap TR = new Treap();
            SplayTree SPT = new SplayTree();



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
            long runTime = (endTime - startTime);
            AVLTinsearts[j] = runTime;
            //System.out.println(runTime);


            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                SBT.insert(keys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            SBTinsearts[j] = runTime;
            //System.out.println(runTime);

            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                TR.insert(keys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            TRinsearts[j] = runTime;
            //System.out.println(runTime);



            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                SPT.insert(keys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            //System.out.println(runTime);
            SPTinsearts[j] = runTime;


            int[] searchKeys = new int[size];
            for(int i = 0; i < size; i++)
            {
                searchKeys[i] = random.nextInt(size);
            }

            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                AVLT.search(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            //System.out.println(runTime);
            AVLTsearchs[j] = runTime;



            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                SBT.search(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            SBTsearchs[j] = runTime;
            //System.out.println(runTime);

            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                TR.search(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            TRsearchs[j] = runTime;
            //System.out.println(runTime);


            startTime = System.currentTimeMillis();
            for(int i = 0; i < size; i++)
            {
                SPT.search(searchKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            SPTsearchs[j] = runTime;
            //System.out.println(runTime);



            int[] deleteKeys = new int[size];
            for(int i = 0; i < size; i++)
            {
                deleteKeys[i] = random.nextInt(size);
            }

            startTime = System.currentTimeMillis();
            for(int i = 0; i < size-1000; i++)
            {
                AVLT.delete(deleteKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            AVLTdeletes[j] = runTime;
            //System.out.println(runTime);


            startTime = System.currentTimeMillis();
            for(int i = 0; i < size-1000; i++)
            {
                SBT.delete(deleteKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            SBTdeletes[j] = runTime;
            //System.out.println(runTime);



            startTime = System.currentTimeMillis();
            for(int i = 0; i < size-1000; i++)
            {
                TR.delete(deleteKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            TRdeletes[j] = runTime;
            //System.out.println(runTime);


            startTime = System.currentTimeMillis();
            for(int i = 0; i < size-1000; i++)
            {
                SPT.delete(deleteKeys[i]);
            }
            endTime = System.currentTimeMillis();
            runTime = (endTime - startTime);
            SPTdeletes[j] = runTime;
            //System.out.println(runTime);
        }
        long AVLTInsertMean = 0;
        long SBTInsertMean = 0;
        long TRInsertMean = 0;
        long SPTInsertMean = 0;
        long AVLTSearchMean = 0;
        long SBTSearchMean = 0;
        long TRSearchMean = 0;
        long SPTSearchMean = 0;
        long AVLTDeleteMean = 0;
        long SBTDeleteMean = 0;
        long TRDeleteMean = 0;
        long SPTDeleteMean = 0;
        for(int k = 0; k < times; k++)
        {
            AVLTInsertMean += AVLTinsearts[k];
            AVLTSearchMean += AVLTsearchs[k];
            AVLTDeleteMean += AVLTdeletes[k];
            SBTInsertMean += SBTinsearts[k];
            SBTSearchMean += SBTsearchs[k];
            SBTDeleteMean += SBTdeletes[k];
            TRInsertMean += TRinsearts[k];
            TRSearchMean += TRsearchs[k];
            TRDeleteMean += TRdeletes[k];
            SPTInsertMean += SPTinsearts[k];
            SPTSearchMean += SPTsearchs[k];
            SPTDeleteMean += SPTdeletes[k];
        }
        AVLTDeleteMean /= times;
        AVLTInsertMean /= times;
        AVLTSearchMean /= times;
        SBTDeleteMean /= times;
        SBTInsertMean /= times;
        SBTSearchMean /= times;
        TRDeleteMean /= times;
        TRInsertMean /= times;
        TRSearchMean /= times;
        SPTDeleteMean /= times;
        SPTInsertMean /= times;
        SPTSearchMean /= times;
        System.out.println("AVL insert time is " + AVLTInsertMean);
        System.out.println("AVL search time is " + AVLTSearchMean);
        System.out.println("AVL delete time is " + AVLTDeleteMean);
        System.out.println("Search Binary Tree insert time is " + SBTInsertMean);
        System.out.println("Search Binary Tree search time is " + SBTSearchMean);
        System.out.println("Search Binary Tree delete time is " + SBTDeleteMean);
        System.out.println("Treap insert time is " + TRInsertMean);
        System.out.println("Treap search time is " + TRSearchMean);
        System.out.println("Treap delete time is " + TRDeleteMean);
        System.out.println("Splay Tree insert time is " + SPTInsertMean);
        System.out.println("Splay Tree search time is " + SPTSearchMean);
        System.out.println("Splay Tree delete time is " + SPTDeleteMean);

    }



}
