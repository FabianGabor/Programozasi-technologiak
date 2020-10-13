package com.fabiangabor.kollekciokBenchmark;

import com.google.common.base.Stopwatch;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

/**
 * Adjon olyan Java-kódot, ami összehasonlítja a TreeSet és a LinkedList adatszerkezetek keresési algoritmusait,
 * tízmilliós-milliárdos nagyságrendű adatszerkezeteken (a JVM heap-jét növelni szükséges lehet)!
 */
public class Kollekciok {
    final static int N[] = new int[] {10, 1000, 100000, 1000000 };

    /**
     *
     * @param collection
     * @param x
     * @return
     */
    public static long beszur (Collection<Integer> collection, int x) {
        Stopwatch stopwatchTreeSet = Stopwatch.createStarted();
        collection.add(x);
        stopwatchTreeSet.stop();

        return stopwatchTreeSet.elapsed(MICROSECONDS);
    }

    /**
     *
     * @param treeSet
     * @param linkedList
     * @param n
     */
    public static void feltolt (Collection<Integer> treeSet, Collection<Integer> linkedList, int n) {
        Random random = new Random();
        int r = random.nextInt();

        for (int i=0; i<n; i++) {
            System.out.print("\r" + Math.round(i * 1.0 / (n-1) * 100) + "%");

            beszur(treeSet,r);
            beszur(linkedList,r);
            r = random.nextInt();
        }
        System.out.println();
    }

    /**
     *
     * @param collection
     * @param x
     * @return
     */
    public static long keres (Collection<Integer> collection, int x) {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        collection.contains(x);
        stopwatch.stop();

        return stopwatch.elapsed(MICROSECONDS);
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        numberFormat.setGroupingUsed(true);
        BenchmarkResult result = new BenchmarkResult();
        BenchmarkResult.Find resultFind;
        BenchmarkResult.Insert resultInsert;
        TreeMap<Integer, BenchmarkResult> results = new TreeMap<>();


        for (int i=0; i<N.length; i++) {
            System.out.println("\nN = " + numberFormat.format(N[i]));

            TreeSet<Integer> treeSet = new TreeSet<>();
            LinkedList<Integer> linkedList = new LinkedList<>();

            System.out.println("Feltoltes START");
            feltolt(treeSet, linkedList, N[i]);
            System.out.println("Feltoltes END");

            Stopwatch stopwatchTreeSet = Stopwatch.createUnstarted();
            Stopwatch stopwatchLinkedList = Stopwatch.createUnstarted();

            Random random = new Random();
            int r = random.nextInt();

            result = new BenchmarkResult();

            result.setSize(N[i]);

            resultInsert = new BenchmarkResult.Insert();
            resultInsert.setTreeset(beszur(treeSet, r));
            resultInsert.setLinkedlist(beszur(linkedList, r));
            result.setInsert(resultInsert);

            resultFind = new BenchmarkResult.Find();
            resultFind.setTreeset(keres(treeSet,1));
            resultFind.setLinkedlist(keres(linkedList,1));
            result.setFind(resultFind);

            results.put(N[i], result);
        }

        result.printResults(results);
        result.writeFileResults(results);
    }
}
