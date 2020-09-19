import java.time.LocalTime;
import java.util.ArrayList;

public class Ember {
    private int azon;
    private LocalTime idopont;
    private String irany;
    private boolean be;

    public Ember() {
    }

    public Ember(int azon) {
        this.azon = azon;
    }

    public Ember(int azon, String irany, boolean be) {
        this.azon = azon;
        this.irany = irany;
        this.be = be;
    }
    public Ember(LocalTime idopont, String irany, boolean be) {
        this.idopont = idopont;
        this.irany = irany;
        this.be = be;
    }

    public int getAzon() {
        return azon;
    }

    public void setAzon(int azon) {
        this.azon = azon;
    }

    public LocalTime getIdopont() {
        return idopont;
    }

    public void setIdopont(LocalTime idopont) {
        this.idopont = idopont;
    }

    public String getIrany() {
        return irany;
    }

    public void setIrany(String irany) {
        this.irany = irany;
    }

    public boolean isBe() {
        return be;
    }

    public void setBe(boolean be) {
        this.be = be;
    }
}
