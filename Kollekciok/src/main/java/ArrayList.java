import com.google.common.base.Stopwatch;
import com.sun.source.tree.Tree;

import java.time.Instant;
import java.util.*;

import static java.util.concurrent.TimeUnit.*;

public class ArrayList {
    // Adjon olyan Java-kódot, ami összehasonlítja a TreeSet és a LinkedList adatszerkezetek keresési algoritmusait,
    // tízmilliós-milliárdos nagyságrendű adatszerkezeteken (a JVM heap-jét növelni szükséges lehet) !

    final static int N = 1000000;


    static public void beszur (Collection<Integer> collection, int x) {
        collection.add(x);
    }

    public static void main(String[] args) {
        TreeSet <Integer> treeSet = new TreeSet<>();
        LinkedList <Integer> linkedList = new LinkedList<>();
        Random random = new Random();
        int r = random.nextInt();

        System.out.println("Beszuras START");
        for (int i=0; i<N; i++) {
            beszur(treeSet,r);
            beszur(linkedList,r);
            r = random.nextInt();
        }
        System.out.println("Beszuras END");

        Stopwatch stopwatchTreeSet = Stopwatch.createStarted();
        beszur(treeSet,r);
        stopwatchTreeSet.stop();
        long beszurTreeSetMillis = stopwatchTreeSet.elapsed(MICROSECONDS);

        Stopwatch stopwatchLinkedList = Stopwatch.createStarted();
        beszur(linkedList,r);
        stopwatchLinkedList.stop();
        long beszurLinkedListMillis = stopwatchLinkedList.elapsed(MICROSECONDS);

        System.out.println("TreeSet: " + beszurTreeSetMillis + " ms");
        System.out.println("LinkedList: " + beszurLinkedListMillis + " ms");
        System.out.println("TreeSet - LinkedList: " + String.valueOf(beszurTreeSetMillis - beszurLinkedListMillis) + " ms");


        stopwatchTreeSet.start();
        System.out.println(treeSet.contains(1));
        stopwatchTreeSet.stop();
        System.out.println("TreeSet kereses: " + stopwatchTreeSet.elapsed(MICROSECONDS));


        stopwatchLinkedList.start();
        System.out.println(linkedList.contains(1));
        stopwatchLinkedList.stop();
        System.out.println("LinkedList kereses: " + stopwatchLinkedList.elapsed(MICROSECONDS));

        System.out.println("TreeSet kereses: " + (stopwatchLinkedList.elapsed(MICROSECONDS) / stopwatchTreeSet.elapsed(MICROSECONDS)) + "x gyorsabb");

    }
}
