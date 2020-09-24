import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;

public class BenchmarkResult {
    //long treeset;
    //long linkedlist;
    int size;
    Insert insert;
    Find find;

    public BenchmarkResult() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Insert getInsert() {
        return insert;
    }

    public void setInsert(Insert insert) {
        this.insert = insert;
    }

    public Find getFind() {
        return find;
    }

    public void setFind(Find find) {
        this.find = find;
    }

    static class Insert {
        long treeset;
        long linkedlist;

        public Insert() {
        }

        public long getTreeset() {
            return treeset;
        }

        public void setTreeset(long treeset) {
            this.treeset = treeset;
        }

        public long getLinkedlist() {
            return linkedlist;
        }

        public void setLinkedlist(long linkedlist) {
            this.linkedlist = linkedlist;
        }
    }

    static class Find {
        long treeset;
        long linkedlist;

        public Find() {
        }

        public long getTreeset() {
            return treeset;
        }

        public void setTreeset(long treeset) {
            this.treeset = treeset;
        }

        public long getLinkedlist() {
            return linkedlist;
        }

        public void setLinkedlist(long linkedlist) {
            this.linkedlist = linkedlist;
        }
    }

    public void printResults(TreeMap<Integer, BenchmarkResult> results) {
        for (Map.Entry <Integer, BenchmarkResult> r : results.entrySet()) {
            System.out.println("\n" + r.getKey() + " elem:");

            System.out.println("TreeSet beszuras: " + r.getValue().getInsert().getTreeset() + " ms");
            System.out.println("LinkedList beszuras: " + r.getValue().getInsert().getLinkedlist() + " ms");
            System.out.println("TreeSet - LinkedList: " + String.valueOf(r.getValue().getInsert().getTreeset() - r.getValue().getInsert().getLinkedlist()) + " ms");
            System.out.println();

            System.out.println("TreeSet kereses: " + r.getValue().getFind().getTreeset() + "ms");
            System.out.println("LinkedList kereses: " + r.getValue().getFind().getLinkedlist() + "ms");
            System.out.println("TreeSet kereses: " + (r.getValue().getFind().getLinkedlist() / r.getValue().getFind().getTreeset()) + "x gyorsabb");
        }
    }

    public void writeFileResults(TreeMap<Integer, BenchmarkResult> results) throws IOException {
        RandomAccessFile writer = new RandomAccessFile("kollekciok.json", "rw");

        writer.writeBytes("{ \"Kollekciok\" : {");

        writer.writeBytes("\"Beszuras\" : [");
        for (Map.Entry <Integer, BenchmarkResult> r : results.entrySet()) {
            writer.writeBytes("{");
            writer.writeBytes("\"Size\" :" + String.valueOf(r.getKey()) + ",");
            writer.writeBytes("\"TreeSet\" :" + r.getValue().getInsert().getTreeset() + ",");
            writer.writeBytes("\"LinkedList\" : " + r.getValue().getInsert().getLinkedlist() + ",");
            writer.writeBytes("\"TreeSet - LinkedList\" :" + String.valueOf(r.getValue().getInsert().getTreeset() - r.getValue().getInsert().getLinkedlist()) + " },");
        }
        writer.writeBytes("],"); // beszuras ]

        writer.writeBytes("\"Kereses\" : [");
        for (Map.Entry <Integer, BenchmarkResult> r : results.entrySet()) {
            writer.writeBytes("{");
            writer.writeBytes("\"Size\" :" + String.valueOf(r.getKey()) + ",");
            writer.writeBytes("\"TreeSet\" :" + r.getValue().getFind().getTreeset() + ",");
            writer.writeBytes("\"LinkedList\" : " + r.getValue().getFind().getLinkedlist() + ",");
            writer.writeBytes("\"TreeSet / LinkedList\" :" + String.valueOf(r.getValue().getFind().getTreeset() / r.getValue().getFind().getLinkedlist()) + " },");
        }
        writer.writeBytes("]"); // Kereses ]

        writer.writeBytes("}}");

        writer.close();
    }
}
