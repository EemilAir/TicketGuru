package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Lippu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lippu_id;
    private double hinta;
    private Long maksutapa;
    private String myyntiaika;
    private Long myyntikanava;
    private String koodi;
    private String paikka;
    private String luontiaika;
    private String alkupvm;
    private String loppupvm;
    private Long lipputyyppi;
    private Long tila;
    private Long kayttaja_id;
    private Long tapahtuma_id;


    public Lippu() {
    }

    public Lippu(Long lippu_id, double hinta, Long maksutapa, String myyntiaika, Long myyntikanava, String koodi, String paikka, String luontiaika, String alkupvm, String loppupvm, Long lipputyyppi, Long tila, Long kayttaja_id, Long tapahtuma_id) {
        this.lippu_id = lippu_id;
        this.hinta = hinta;
        this.maksutapa = maksutapa;
        this.myyntiaika = myyntiaika;
        this.myyntikanava = myyntikanava;
        this.koodi = koodi;
        this.paikka = paikka;
        this.luontiaika = luontiaika;
        this.alkupvm = alkupvm;
        this.loppupvm = loppupvm;
        this.lipputyyppi = lipputyyppi;
        this.tila = tila;
        this.kayttaja_id = kayttaja_id;
        this.tapahtuma_id = tapahtuma_id;
    }

    public Long getLippu_id() {
        return lippu_id;
    }

    public void setLippu_id(Long lippu_id) {
        this.lippu_id = lippu_id;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public Long getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(Long maksutapa) {
        this.maksutapa = maksutapa;
    }

    public String getMyyntiaika() {
        return myyntiaika;
    }

    public void setMyyntiaika(String myyntiaika) {
        this.myyntiaika = myyntiaika;
    }

    public Long getMyyntikanava() {
        return myyntikanava;
    }

    public void setMyyntikanava(Long myyntikanava) {
        this.myyntikanava = myyntikanava;
    }

    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public String getLuontiaika() {
        return luontiaika;
    }

    public void setLuontiaika(String luontiaika) {
        this.luontiaika = luontiaika;
    }

    public String getAlkupvm() {
        return alkupvm;
    }

    public void setAlkupvm(String alkupvm) {
        this.alkupvm = alkupvm;
    }

    public String getLoppupvm() {
        return loppupvm;
    }

    public void setLoppupvm(String loppupvm) {
        this.loppupvm = loppupvm;
    }

    public Long getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(Long lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }

    public Long getTila() {
        return tila;
    }

    public void setTila(Long tila) {
        this.tila = tila;
    }

    public Long getKayttaja_id() {
        return kayttaja_id;
    }

    public void setKayttaja_id(Long kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
    }

    public Long getTapahtuma_id() {
        return tapahtuma_id;
    }

    public void setTapahtuma_id(Long tapahtuma_id) {
        this.tapahtuma_id = tapahtuma_id;
    }
}