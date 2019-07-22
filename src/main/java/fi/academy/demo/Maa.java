package fi.academy.demo;

public class Maa {
    private int id;
    private String maakoodi;
    private String nimi;
    private int asukasluku;
    private String paakaupunki;

    public Maa(String maakoodi, String nimi, int asukasluku, String paakaupunki) {
        this.maakoodi = maakoodi;
        this.nimi = nimi;
        this.asukasluku = asukasluku;
        this.paakaupunki = paakaupunki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaakoodi() {
        return maakoodi;
    }

    public void setMaakoodi(String maakoodi) {
        this.maakoodi = maakoodi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getAsukasluku() {
        return asukasluku;
    }

    public void setAsukasluku(int asukasluku) {
        this.asukasluku = asukasluku;
    }

    public String getPaakaupunki() {
        return paakaupunki;
    }

    public void setPaakaupunki(String paakaupunki) {
        this.paakaupunki = paakaupunki;
    }
}
