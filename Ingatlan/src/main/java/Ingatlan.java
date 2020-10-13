public class Ingatlan implements IngatlanInterface{
    String telepules;
    double ar; // negyzetmeter ar
    int nm;
    double szobaszam;

    /**
     * a paraméterben egész számként megadott százalékkal csökkenti az ingatlan egy négyzetméterre jutó árát
     * @param szazalek
     * @return
     */
    @Override
    public double akcio(int szazalek) {
        return this.getAr() * (1.0 - szazalek  / 100.0);
    }

    /**
     * ez a metódus egész számmal visszatér az ingatlan teljes árával, amit az ar és nm mezők segítségével számol ki.
     * Az árat befolyásolja az, hogy melyik településen van az ingatlan, ezért ha az ingatlan Budapesten van,
     * akkor 30%-al, ha Debrecenben, akkor 20%-al, ha Nyíregyházán, akkor 15%-al nagyobb árat kell visszaadni a
     * kiszámolt értéknél.
     * @param ar
     * @param nm
     * @param telepules
     * @return
     */
    @Override
    public int teljesar(double ar, int nm, String telepules) {
        double szorzo;
        switch (telepules) {
            case "Budapest": szorzo = 1.3;
            case "Debrecen": szorzo = 1.2;
            case "Nyiregyhaza": szorzo = 1.15;
            default: szorzo = 1;
        }
        return (int) (ar * nm * szorzo);
    }

    /**
     * kiszámolja és visszaadja valós számként, hogy hány négyzetméter jut átlagosan egy szobára az adott ingatlan esetében.
     * @param nm
     * @param szobaszam
     * @return
     */
    @Override
    public double atlagos(double nm, double szobaszam) {
        return nm/szobaszam;
    }

    /**
     * az ingatlan adatainak kiíratása a teljes árral és a szobákra eső átlagos négyzetméterszámmal egyetemben.
     * @return
     */
    /*
    @Override
    public String toString() {
        return "Ingatlan{" +
                "telepules='" + telepules + '\'' +
                ", ar=" + ar +
                ", nm=" + nm +
                ", szobaszam=" + szobaszam +
                ", tipus=" + tipus +
                ", teljesAr=" + teljesar(ar, nm, telepules) +
                ", atlagos=" + atlagos(nm, szobaszam) +
                '}';
    }

     */

    enum Tipus {CSALÁDIHÁZ, TÁRSASHÁZ, HÁZRÉSZ};
    Tipus tipus;

    public Ingatlan() {
    }

    public Ingatlan(String telepules, double ar, int nm, double szobaszam, Tipus tipus) {
        this.telepules = telepules;
        this.ar = ar;
        this.nm = nm;
        this.szobaszam = szobaszam;
        this.tipus = tipus;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public int getNm() {
        return nm;
    }

    public void setNm(int nm) {
        this.nm = nm;
    }

    public double getSzobaszam() {
        return szobaszam;
    }

    public void setSzobaszam(double szobaszam) {
        this.szobaszam = szobaszam;
    }

    public Tipus getTipus() {
        return tipus;
    }

    public void setTipus(Tipus tipus) {
        this.tipus = tipus;
    }
}
