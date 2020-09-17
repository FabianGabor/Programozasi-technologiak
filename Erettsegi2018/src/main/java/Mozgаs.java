public class Mozgаs
{
    private int ora;
    private int perc;
    private int azon;
    private String irany;
    private boolean be;

    public Mozgаs(String[] tomb )
    {
        ora = Integer.parseInt(tomb[0]);
        perc = Integer.parseInt(tomb[1]);
        azon = Integer.parseInt(tomb[2]);
        irany = tomb[3];
        be = tomb[3].equals("be");
    }

    public int getOra()
    {
        return ora;
    }

    public int getPerc()
    {
        return perc;
    }

    public int getAzon()
    {
        return azon;
    }

    public String getIrany()
    {
        return irany;
    }

    public boolean isBe()
    {
        return be;
    }

    @Override
    public String toString()
    {
        return "Ember{" + "ora=" + ora + ", perc=" + perc +
                ", azon=" + azon + ", irany=" + irany + ", be=" + be + '}';
    }
}
