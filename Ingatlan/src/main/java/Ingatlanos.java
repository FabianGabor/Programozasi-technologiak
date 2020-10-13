import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class Ingatlanos {
    static TreeSet <Ingatlan> ingatlanok;
    static int panellakas = 0;

    void feltolt() {
        /**
         * ingatlanok.txt
         * osztály megnevezése#telepules#ar#nm#szobaszam#tipus#emelet#szigetelt(igen/nem) (ez utóbbi kettő csak akkor, ha az osztály Panel)
         */

        //ingatlanok = null;

        try {
            String filename = "src/main/java/ingatlanok.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String currentLine;


            for (currentLine = reader.readLine(); currentLine != null; currentLine = reader.readLine()) {
                panellakas++;

                Ingatlan ingatlan = new Ingatlan();
                String className;
                ingatlan.telepules = "Nyiregyhaza";
                System.out.println(currentLine.split("#"));

                //ingatlanok.add(ingatlan);
                ingatlanok.add(new Ingatlan());
            }

            reader.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }


    }
}
