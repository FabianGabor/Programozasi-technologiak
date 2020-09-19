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

        TreeMap<LocalTime, HashMap<Integer, Ember>> idonaplo = new TreeMap<>();
        for( Mozgas m : mozgasok ) {
            LocalTime idopont = LocalTime.of(m.getOra(), m.getPerc());
            Ember e = new Ember(m.getAzon(), m.getIrany(), m.isBe());
            if (!idonaplo.containsKey(idopont))
                idonaplo.put(idopont, new HashMap<Integer, Ember>());
            idonaplo.get(idopont).put(m.getAzon(), e);
        }

        LocalTime vizsgaltIdoszakStart = LocalTime.of(9,0);
        LocalTime vizsgaltIdoszakEnd = LocalTime.of(9,15);
        ArrayList<Ember> keresettNok = new ArrayList<>(2);
        keresettNok.add(0, new Ember());

        Iterator idonaploIterator = idonaplo.entrySet().iterator();
        while (idonaploIterator.hasNext()) {
            Map.Entry idopontnaploElement = (Map.Entry)idonaploIterator.next();
            LocalTime key = LocalTime.parse(idopontnaploElement.getKey().toString());

            if ( key.plusSeconds(1).isAfter(vizsgaltIdoszakStart) &&
                    key.minusSeconds(1).isBefore(vizsgaltIdoszakEnd) ) {
                System.out.println(idopontnaploElement.getKey());

                for (Map.Entry<Integer,Ember> ember : idonaplo.get(key).entrySet()) {
                    System.out.println("\t" + ember.getValue().getAzon() + " " + ember.getValue().getIrany());
                    if (ember.getValue().getAzon() % 2 == 0 && ember.getValue().isBe() && keresettNok.get(0).getAzon() == 0)
                        keresettNok.add(0, ember.getValue());
                    if (ember.getValue().getAzon() % 2 == 0 && ember.getValue().isBe() == false)
                        keresettNok.add(1, ember.getValue());
                }
            }
        }

        System.out.println("Elso no aki belepett: " + ((keresettNok.get(0).getAzon() != 0) ? keresettNok.get(0).getAzon() : "nincs"));
        System.out.println("Utolso no aki tavozott: " + ((keresettNok.get(1).getAzon() != 0) ? keresettNok.get(1).getAzon() : "nincs"));

    }
}