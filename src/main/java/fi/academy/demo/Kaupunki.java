package fi.academy.demo;

public class Kaupunki {
    private int id;
    private String nimi;
    private String maakoodi;
    private int asukasluku;

    public Kaupunki(int id, String nimi, String maakoodi, int asukasluku) {
        this.id = id;
        this.nimi = nimi;
        this.maakoodi = maakoodi;
        this.asukasluku = asukasluku;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getMaakoodi() {
        return maakoodi;
    }

    public void setMaakoodi(String maakoodi) {
        this.maakoodi = maakoodi;
    }

    public int getAsukasluku() {
        return asukasluku;
    }

    public void setAsukasluku(int asukasluku) {
        this.asukasluku = asukasluku;
    }
}
