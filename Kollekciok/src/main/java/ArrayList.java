import com.google.common.base.Stopwatch;
import com.sun.source.tree.Tree;

import java.lang.instrument.Instrumentation;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.*;

import static java.util.concurrent.TimeUnit.*;

public class ArrayList {
    // Adjon olyan Java-kódot, ami összehasonlítja a TreeSet és a LinkedList adatszerkezetek keresési algoritmusait,
    // tízmilliós-milliárdos nagyságrendű adatszerkezeteken (a JVM heap-jét növelni szükséges lehet) !

    //final static int N = 10000000;
    final static int N[] = new int[] {30, 3000, 300000, 10000000 };


    public static long beszur (Collection<Integer> collection, int x) {
        Stopwatch stopwatchTreeSet = Stopwatch.createStarted();
        collection.add(x);
        stopwatchTreeSet.stop();

        return stopwatchTreeSet.elapsed(MICROSECONDS);
    }

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

    public static long keres (Collection<Integer> collection, int x) {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        collection.contains(x);
        stopwatch.stop();

        return stopwatch.elapsed(MICROSECONDS);
    }


    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        numberFormat.setGroupingUsed(true);



        for (int i=0; i<N.length; i++) {
            System.out.println("\nN = " + numberFormat.format(N[i]));

            TreeSet<Integer> treeSet = new TreeSet<>();
            LinkedList<Integer> linkedList = new LinkedList<>();

            System.out.println("Feltoltes START");
            feltolt(treeSet, linkedList, N[i]);
            System.out.println("Feltoltes END\n");

            //System.out.println(ObjectSizeFetcher.getObjectSize(linkedList));

            Stopwatch stopwatchTreeSet = Stopwatch.createUnstarted();
            Stopwatch stopwatchLinkedList = Stopwatch.createUnstarted();

            Random random = new Random();
            int r = random.nextInt();
            long treeSetMillis = beszur(treeSet, r);
            long linkedListMillis = beszur(linkedList, r);

            System.out.println("TreeSet beszuras: " + treeSetMillis + " ms");
            System.out.println("LinkedList beszuras: " + linkedListMillis + " ms");
            System.out.println("TreeSet - LinkedList: " + String.valueOf(treeSetMillis - linkedListMillis) + " ms");
            System.out.println();

            treeSetMillis = keres(treeSet, 1);
            linkedListMillis = keres(linkedList, 1);

            System.out.println("TreeSet kereses: " + treeSetMillis + "ms");
            System.out.println("LinkedList kereses: " + linkedListMillis + "ms");

            System.out.println("TreeSet kereses: " + (linkedListMillis / treeSetMillis) + "x gyorsabb");
        }
    }
}
