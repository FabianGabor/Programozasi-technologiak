// package eretts2018;

import java.io.*;
import java.util.*;

public class Beolvasаs
{
    public static class Idopont
    {
        private Integer ora, perc;

        public Idopont() {
            this.ora = 0;
            this.perc = 0;
        }

        public Idopont(Integer ora, Integer perc) {
            this.ora = ora;
            this.perc = perc;
        }

        public Integer getOra() {
            return ora;
        }

        public void setOra(Integer ora) {
            this.ora = ora;
        }

        public Integer getPerc() {
            return perc;
        }

        public void setPerc(Integer perc) {
            this.perc = perc;
        }
    }

    public static class Ember
    {
        //private Mozgаs mozgas;
        private ArrayList<Mozgаs> mozgаs = new ArrayList<>();

        public Ember() {
        }

        public ArrayList<Mozgаs> getMozgаs() {
            return mozgаs;
        }

        public void setMozgаs(ArrayList<Mozgаs> mozgаs) {
            this.mozgаs = mozgаs;
        }
    }

    public static class Emberek
    {
        Map<Integer,Ember> lista = new HashMap<>();

        public Emberek() {
        }

        public Map<Integer, Ember> getLista() {
            return lista;
        }

        public void setLista(Map<Integer, Ember> lista) {
            this.lista = lista;
        }
    }

    public static void main(String[] args)
    {
        // 1. feladat
        Mozgаs[] mozgasok = null;

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

            mozgasok = new Mozgаs[db];
            raf.seek(0);
            db = 0;
            for( sor = raf.readLine(); sor != null; sor = raf.readLine() )
            {
                mozgasok[db] = new Mozgаs(sor.split(" "));
                db++;
            }
            raf.close();
        }
        catch( IOException e )
        {
            System.out.println("HIBA");
        }

        if (mozgasok != null)
        {
            for( Mozgаs m : mozgasok )
            {
              //System.out.println(m);
            }
        }

        // Írja a képernyőre annak a nőnek az azonosítóját, aki a vizsgált időszakon belül először lépett be az ajtón, és azét, aki utoljára távozott a megfigyelési időszakban!
        // A páros emberID nőt takar, a páratlan sorszám férfit.

/*
        Scanner s = new Scanner(System.in);
        Idopont iStart = new Idopont();
        Idopont iEnd = new Idopont();

        System.out.println("\nVizsgalt idoszak kezdete:");
        System.out.printf("\tOra: ");
        iStart.setOra(s.nextInt());
        System.out.printf("\tPerc: ");
        iStart.setPerc(s.nextInt());

        System.out.println("\nVizsgalt idoszak vege:");
        System.out.printf("\tOra: ");
        iEnd.setOra(s.nextInt());
        System.out.printf("\tPerc: ");
        iEnd.setPerc(s.nextInt());
*/

        Emberek emberek = new Emberek();


        //System.out.println("\n\n" + mozgasok[0]);
        if (mozgasok != null)
        {
            for (Mozgаs m : mozgasok)
            {
                Ember ember = new Ember();

                //ember.setMozgаs();
                ember.mozgаs.add(m);
                emberek.lista.put(m.getAzon(), ember);
                emberek.lista.get(m.getAzon()).mozgаs.add(m);

                System.out.println(emberek);
            }
        }

        /*
        for (Map.Entry<Integer, Ember> e : emberek.lista.entrySet()) {
            for (Mozgаs m : mozgasok) {
                //System.out.println(m.getAzon());
            }
            System.out.println(e.getKey() + " " + e.getValue().mozgas);
        }

         */
    }
}