/*
https://github.com/FabianGabor/Programozasi-technologiak/tree/main/Erettsegi2018
 */

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;


public class Beolvasas
{
    public static boolean talalkoztak(Ember e1, Ember e2) {
        if (e1.getKiment().compareTo(e2.getBement()) <= 0)
            return false;
        if (e1.getBement().compareTo(e2.getKiment()) >= 0)
            return false;

        return true;
    }

    public static void f5 (TreeMap<LocalTime, HashMap<Integer, Ember>> idonaplo) {
        System.out.println("\n5. feladat:");

        TreeMap<String, Integer> maxLetszamIntervallum = new TreeMap<>();

        List<LocalTime> list = new ArrayList<LocalTime>(idonaplo.keySet());
        for (int i = 0; i < list.size() - 1; i++) {
            Integer letszam = 0;

            for (int j = 0 ; j < list.size()-1; j++) {
                String keyIntervallum = list.get(i).toString() + "-" + list.get(j + 1).toString();

                for (Map.Entry<Integer, Ember> ember : idonaplo.get(list.get(j)).entrySet()) {
                    if (ember.getValue().isBe())
                        letszam++;
                    else
                        letszam--;
                }

                if (list.get(i).compareTo(list.get(j)) <= 0)
                {
                    if (maxLetszamIntervallum.get(keyIntervallum) == null)
                        maxLetszamIntervallum.put(keyIntervallum, letszam);
                    else
                        maxLetszamIntervallum.replace(keyIntervallum, letszam);
                }
            }
        }

        int maxLetszam = 0;
        for (Map.Entry<String, Integer> ml: maxLetszamIntervallum.entrySet()) {
            System.out.println(ml.getKey() + " : " + ml.getValue());
            if (ml.getValue() > maxLetszam)
                maxLetszam = ml.getValue();
        }
        System.out.println("Max egyideju letszam: " + maxLetszam);
        for (Map.Entry<String, Integer> ml: maxLetszamIntervallum.entrySet()) {
            if (ml.getValue().equals(maxLetszam))
                System.out.println(ml.getKey() + " : " + ml.getValue());
        }
    }

    public static void f6 (Mozgas mozgasok[]) {
        System.out.println("\n6. feladat:");

        TreeMap<Integer, Ember> emberListaBeKi = new TreeMap<>();

        for (int i = 0; i < mozgasok.length; i++) {
            if (emberListaBeKi.get(mozgasok[i].getAzon()) == null) {
                Ember e = new Ember();
                e.setAzon(mozgasok[i].getAzon());
                if (mozgasok[i].isBe())
                    e.setBement(LocalTime.of(mozgasok[i].getOra(), mozgasok[i].getPerc()));
                else
                    e.setKiment(LocalTime.of(mozgasok[i].getOra(), mozgasok[i].getPerc()));

                emberListaBeKi.put(e.getAzon(), e);
            } else {
                if (emberListaBeKi.get(mozgasok[i].getAzon()).getKiment() == null) {
                    if (!mozgasok[i].isBe())
                        emberListaBeKi.get(mozgasok[i].getAzon()).setKiment(LocalTime.of(mozgasok[i].getOra(), mozgasok[i].getPerc()));
                    else
                        emberListaBeKi.get(mozgasok[i].getAzon()).setKiment(LocalTime.of(15, 0));

                }
            }
        }

        ArrayList<Integer> emberListaBeKiList = new ArrayList<Integer>(emberListaBeKi.keySet());

        Map<Integer, TreeMap<Integer, Integer>> egyuttletek = new TreeMap<>();

        for (int i = 0; i < emberListaBeKiList.size(); i++) {
            for (int j = i + 1; j < emberListaBeKiList.size(); j++) {

                Ember e1 = emberListaBeKi.get(emberListaBeKiList.get(i));
                Ember e2 = emberListaBeKi.get(emberListaBeKiList.get(j));

                if (e1.getKiment() == null) e1.setKiment(LocalTime.of(15, 0));
                if (e2.getKiment() == null) e2.setKiment(LocalTime.of(15, 0));

                egyuttletek.putIfAbsent(emberListaBeKiList.get(i), new TreeMap<Integer, Integer>());
                egyuttletek.get(emberListaBeKiList.get(i)).put(emberListaBeKiList.get(j), (int) egyuttIdo(e1, e2).toMinutes());

                System.out.println(emberListaBeKiList.get(i) + " + " + emberListaBeKiList.get(j) + " egyutt voltak: " + egyuttIdo(e1, e2).toMinutes() + " percet."); // 347 perc = 15 oraig akik nem tavoztak
            }
        }

        ArrayList<Integer> egyuttletekList = new ArrayList<Integer>(egyuttletek.keySet());
        Integer maxEgyuttletIdo = 0;

        for (Map.Entry<Integer, TreeMap<Integer, Integer>> e : egyuttletek.entrySet()) {
            Integer key = e.getKey();
            for (Map.Entry<Integer, Integer> ee : egyuttletek.get(key).entrySet()) {
                if (ee.getValue() > maxEgyuttletIdo)
                    maxEgyuttletIdo = ee.getValue();
            }
        }

        System.out.println("\nLegtobb idot egyutt tolto par(ok): ");
        for (Map.Entry<Integer, TreeMap<Integer, Integer>> e : egyuttletek.entrySet()) {
            Integer key = e.getKey();
            for (Map.Entry<Integer, Integer> ee : egyuttletek.get(key).entrySet()) {
                if (ee.getValue() == maxEgyuttletIdo)
                    System.out.println(e.getKey() + " + " + ee.getKey() + " par " + ee.getValue() + " percet volt egyutt");
            }
        }
    }

    public static Duration egyuttIdo(Ember e1, Ember e2) {
        Duration d1, d2;
        if (talalkoztak(e1,e2))
        {
            if (e1.getKiment().compareTo(e2.getBement()) > 0) {
                d1 = Duration.between(e1.getBement(), e2.getKiment());
                //return d;
            }
            if (e1.getBement().compareTo(e2.getKiment()) < 0) {
                d2 = Duration.between(e2.getBement(), e1.getKiment());
                //return d;
            }

            d1 = Duration.between(e1.getBement(), e2.getKiment());
            d2 = Duration.between(e2.getBement(), e1.getKiment());
            //System.out.println(d1.getSeconds()/60 + " - " + d2.getSeconds()/60);

            if (d1.compareTo(d2) > 0) return d2;
            else return d1;
        }


        return Duration.ofSeconds(0);
    }

    public static void main(String[] args) throws IOException {
        // 1. feladat
        Mozgas[] mozgasok = null;

        String filename = "src/main/java/ajto.txt";
        //File file = new File(filename);

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

        /*
        for( Mozgas e : mozgasok )
        {
            System.out.println(e);
        }
         */

        ///////////////////////
        /// innen sajat kod ///
        ///////////////////////

        // azonosito naplo
        Map<Integer, HashMap<LocalTime, Ember>> emberLista = new HashMap<>();
        assert mozgasok != null;
        for( Mozgas m : mozgasok ) {
            if (!emberLista.containsKey(m.getAzon()))
                emberLista.put( m.getAzon(), new HashMap<>());

            Ember e = new Ember(m.getAzon(), LocalTime.of(m.getOra(),m.getPerc()), m.getIrany(), m.isBe());
            emberLista.get(m.getAzon()).put(LocalTime.of(m.getOra(),m.getPerc()), e);
        }

        TreeMap<LocalTime, HashMap<Integer, Ember>> idonaplo = new TreeMap<>();
        for( Mozgas m : mozgasok ) {
            LocalTime idopont = LocalTime.of(m.getOra(), m.getPerc());
            Ember e = new Ember(m.getAzon(), m.getIrany(), m.isBe());
            if (!idonaplo.containsKey(idopont))
                idonaplo.put(idopont, new HashMap<>());
            idonaplo.get(idopont).put(m.getAzon(), e);
        }



        // 1.
        // Írja a képernyőre annak a nőnek az azonosítóját, aki a vizsgált időszakon belül először lépett be az ajtón,
        // és azét, aki utoljára távozott a megfigyelési időszakban!

        System.out.println("1. feladat:");

        LocalTime vizsgaltIdoszakStart = LocalTime.of(9,0);
        LocalTime vizsgaltIdoszakEnd = LocalTime.of(9,15);
        ArrayList<Ember> keresettNok = new ArrayList<>(2);
        keresettNok.add(0, new Ember());

        for (Map.Entry<LocalTime, HashMap<Integer, Ember>> localTimeHashMapEntry : idonaplo.entrySet()) {
            LocalTime key = LocalTime.parse(localTimeHashMapEntry.getKey().toString());

            if (key.plusSeconds(1).isAfter(vizsgaltIdoszakStart) &&
                    key.minusSeconds(1).isBefore(vizsgaltIdoszakEnd)) {
                System.out.println(localTimeHashMapEntry.getKey());

                for (Map.Entry<Integer, Ember> ember : idonaplo.get(key).entrySet()) {
                    System.out.println("\t" + ember.getValue().getAzon() + " " + ember.getValue().getIrany());
                    if (ember.getValue().getAzon() % 2 == 0 && ember.getValue().isBe() && keresettNok.get(0).getAzon() == 0)
                        keresettNok.add(0, ember.getValue());
                    if (ember.getValue().getAzon() % 2 == 0 && !ember.getValue().isBe())
                        keresettNok.add(1, ember.getValue());
                }
            }
        }

        System.out.println("Elso no aki belepett: " + ((keresettNok.get(0).getAzon() != 0) ? keresettNok.get(0).getAzon() : "nincs"));
        System.out.println("Utolso no aki tavozott: " + ((keresettNok.get(1).getAzon() != 0) ? keresettNok.get(1).getAzon() : "nincs"));

        // 1. vege

        // 2.
        // Határozza meg a fájlban szereplő személyek közül, ki hányszor haladt át a társalgó ajtaján!
        // A meghatározott értékeket azonosító szerint növekvő sorrendben írja az athaladas.txt fájlba!
        // Soronként egy személy azonosítója, és tőle egy szóközzel elválasztva az áthaladások száma szerepeljen!

        System.out.println("\n2.feladat:");

        String filenameAthalad = "src/main/java/athalad.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filenameAthalad));

        TreeMap<Integer, Integer> szamlalAthalad = new TreeMap<>();

        System.out.println("ID | Athaladas szama");
        for (Map.Entry<Integer, HashMap<LocalTime, Ember>> integerHashMapEntry : emberLista.entrySet()) {
            Integer szamlal = integerHashMapEntry.getValue().size();
            System.out.printf("%2d | %1d \n", integerHashMapEntry.getKey(), szamlal);
            szamlalAthalad.put(integerHashMapEntry.getKey(), szamlal);
        }

        szamlalAthalad.forEach((key,value) -> {
            try {
                writer.write(key + "," + value + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
        // 2. vege

        // 3. Hány nő volt bent a megfigyelési időszak végén?
        // 4. Kik voltak azok?

        System.out.println("\n3. + 4. feladat:");
        TreeMap<Integer, Ember> bentlevoNok = new TreeMap();

        vizsgaltIdoszakEnd = LocalTime.of(9,15);

        for (Map.Entry<LocalTime, HashMap<Integer, Ember>> localTimeHashMapEntry : idonaplo.entrySet()) {
            LocalTime key = LocalTime.parse(localTimeHashMapEntry.getKey().toString());

            if (key.minusSeconds(1).isBefore(vizsgaltIdoszakEnd)) {
                for (Map.Entry<Integer, Ember> ember : idonaplo.get(key).entrySet()) {
                    if (ember.getValue().getAzon() % 2 == 0)
                        if (ember.getValue().isBe())
                            bentlevoNok.put(ember.getValue().getAzon(), ember.getValue());
                        else
                            bentlevoNok.remove(ember.getValue().getAzon());
                }
            }
        }

        System.out.println("Bentlevo nok szama a megfigyelesi idoszak vegen: " + bentlevoNok.size());
        System.out.print("Bentlevo nok azonositoja: ");
        for (Map.Entry<Integer, Ember> bn : bentlevoNok.entrySet())
        {
            System.out.print(bn.getKey() + " ");
        }
        System.out.println();

        // 5.
        // Adjuk meg azokat az időintervallumokat, amikor a legtöbb személy volt bent, ha több ilyen időintervallum van, mindet!
        f5(idonaplo);

        // 6.
        // Melyik emberpár volt legtöbbet bent együtt? Ha több ilyen pár van, mindet írjuk ki!
        f6(mozgasok);



    } // main end


}