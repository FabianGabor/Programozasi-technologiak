import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;


public class Beolvasas
{
    public static void main(String[] args)
    {
        // 1. feladat
        Mozgas[] mozgasok = null;

        String filename = "src/main/java/ajto.txt";
        File file = new File(filename);

        try
        {
            RandomAccessFile raf = new RandomAccessFile(filename,"r");
            String sor;
            int db = 0;
            for( sor = raf.readLine(); sor != null; sor = raf.readLine() )
            {
                db++;
                //System.out.println(db);
            }

            mozgasok = new Mozgas[db];
            raf.seek(0);
            db = 0;
            for( sor = raf.readLine(); sor != null; sor = raf.readLine() )
            {
                mozgasok[db] = new Mozgas(sor.split(" "));
                db++;
            }
            raf.close();
        }
        catch( IOException e )
        {
            System.out.println("HIBA");
        }

        for( Mozgas e : mozgasok )
        {
            //System.out.println(e);
        }

        ///////////////////////
        /// innen sajat kod ///
        ///////////////////////

        // azonosito naplo
        Map<Integer, HashMap<LocalTime, Ember>> emberLista = new HashMap<Integer, HashMap<LocalTime, Ember>>();
        for( Mozgas m : mozgasok ) {
            if (!emberLista.containsKey(m.getAzon()))
                emberLista.put( m.getAzon(), new HashMap<LocalTime, Ember>());

            Ember e = new Ember(LocalTime.of(m.getOra(),m.getPerc()), m.getIrany(), m.isBe());
            emberLista.get(m.getAzon()).put(LocalTime.of(m.getOra(),m.getPerc()), e);
        }

        // idorendi naplo
        /*
        HashMap<LocalTime, ArrayList<Ember>> idonaplo = new HashMap<>(); // ArrayList-tel kell Ember.id vagy az id legyen a HashMap kulcsa
        for( Mozgas m : mozgasok ) {
            LocalTime idopont = LocalTime.of(m.getOra(), m.getPerc());
            Ember e = new Ember(m.getIrany(), m.isBe());
            if (!idonaplo.containsKey(idopont))
                idonaplo.put(idopont, new ArrayList<Ember>());
            idonaplo.get(idopont).add(e);
        }
         */

        TreeMap<LocalTime, HashMap<Integer, Ember>> idonaplo = new TreeMap<>();
        for( Mozgas m : mozgasok ) {
            LocalTime idopont = LocalTime.of(m.getOra(), m.getPerc());
            Ember e = new Ember(m.getIrany(), m.isBe());
            if (!idonaplo.containsKey(idopont))
                idonaplo.put(idopont, new HashMap<Integer, Ember>());
            idonaplo.get(idopont).put(m.getAzon(), e);
        }

        LocalTime vizsgaltIdoszakStart = LocalTime.of(9,3);
        LocalTime vizsgaltIdoszakEnd = LocalTime.of(9,8);

        Iterator idonaploIterator = idonaplo.entrySet().iterator();
        while (idonaploIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)idonaploIterator.next();
            System.out.println(mapElement.getKey());
        }

    }
}