import java.time.LocalTime;
import java.util.ArrayList;

public class Ember {
    private LocalTime idopont;
    private String irany;
    private boolean be;

    public Ember(String irany, boolean be) {
        this.irany = irany;
        this.be = be;
    }
    public Ember(LocalTime idopont, String irany, boolean be) {
        this.idopont = idopont;
        this.irany = irany;
        this.be = be;
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
