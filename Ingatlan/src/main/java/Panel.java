public class Panel extends Ingatlan implements PanelInterface{
    int emelet;
    boolean szigetelt;

    /**
     * Ha a panellakás a 0 – 2 emeleten található, akkor +5%-al, ha a 10 emeleten, akkor -5%-al,
     * ha szigetelt, akkor további +5%-al módosítsuk a kiszámolt teljes ár értékét.
     * @param ar
     * @param nm
     * @param telepules
     * @return
     */
    @Override
    public int teljesar(double ar, int nm, String telepules) {
        double szorzo = 1;

        if (emelet >=0 && emelet <=2) szorzo += .05;
        if (emelet == 10) szorzo -= .05;

        if (szigetelt) szorzo += .05;

        return (int) (super.teljesar(ar, nm, telepules) * szorzo);
    }

    /**
     * igazat ad vissza, ha az Ingatlan osztálybeli bemenő paraméterének ugyanakkora a teljesar metódus által visszaadott értéke
     * @return
     */
    @Override
    public boolean ugyanannyi() {
        return super.teljesar(ar, nm, telepules) == this.teljesar(ar, nm, telepules);
    }

    @Override
    public int szobaar() {
        return (int) (ar * nm / szobaszam);
    }
}
