public class IngatlanAlkalmazas implements IngatlanAlkalmazasInterface{
    @Override
    public double nm_osszeg() {
        return 0;
    }

    @Override
    public int ar_osszeg() {
        return 0;
    }

    @Override
    public double minimum_ar() {
        return 0;
    }

    @Override
    public Ingatlan legdragabb_budapesti_lakas() {
        return null;
    }

    @Override
    public int osszesitett_ar() {
        return 0;
    }

    @Override
    public int teljes_arak_osszege() {
        return 0;
    }

    /**
     * Az ingatlanok átlagos négyzetméter árát
     * A legolcsóbb ingatlan ára
     * A legdrágább Budapesti lakás egy szobára eső átlagos négyzetméter értékét
     * Az ingatlanok összesített árát
     * Azon társasházi ingatlanok felsorolása, melyek teljes ára nem haladja meg az ingatlanok teljes árának átlagát
     * @param args
     */

    public static void main(String[] args) {
        Ingatlanos ingatlanos = new Ingatlanos();
        ingatlanos.feltolt();
    }
}
