import com.google.common.base.Stopwatch;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

public class Kollekciok {
    // Adjon olyan Java-kódot, ami összehasonlítja a TreeSet és a LinkedList adatszerkezetek keresési algoritmusait,
    // tízmilliós-milliárdos nagyságrendű adatszerkezeteken (a JVM heap-jét növelni szükséges lehet) !

    final static int N[] = new int[] {10, 1000, 100000, 1000000 };


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


    public static void main(String[] args) throws IOException {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        numberFormat.setGroupingUsed(true);
        BenchmarkResult result = new BenchmarkResult();
        BenchmarkResult.Find resultFind = new BenchmarkResult.Find();
        BenchmarkResult.Insert resultInsert = new BenchmarkResult.Insert();
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

            result.setSize(N[i]);

            resultInsert.setTreeset(beszur(treeSet, r));
            resultInsert.setLinkedlist(beszur(linkedList, r));
            result.setInsert(resultInsert);

            resultFind.setTreeset(keres(treeSet,1));
            resultFind.setLinkedlist(keres(linkedList,1));
            result.setFind(resultFind);

            results.put(N[i], result);
        }

        result.printResults(results);
        result.writeFileResults(results);
    }
}
