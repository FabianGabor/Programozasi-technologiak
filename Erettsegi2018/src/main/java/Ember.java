/*
https://github.com/FabianGabor/Programozasi-technologiak/tree/main/Erettsegi2018
 */

import java.time.LocalTime;

public class Ember {
    private int azon;
    private LocalTime idopont;
    private String irany;
    private boolean be;
    private LocalTime bement, kiment;


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

    public Ember(int azon, LocalTime idopont, String irany, boolean be) {
        this.azon = azon;
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

    public LocalTime getBement() {
        return bement;
    }

    public void setBement(LocalTime bement) {
        this.bement = bement;
    }

    public LocalTime getKiment() {
        return kiment;
    }

    public void setKiment(LocalTime kiment) {
        this.kiment = kiment;
    }
}
