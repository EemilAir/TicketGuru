package bugivelhot.ticketguru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class Lippu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double hinta;
    private LocalDateTime myyntiaika;
    private String koodi;
    private String paikka;
    private LocalDateTime luontiaika;
    private LocalDateTime alkupvm;
    private LocalDateTime loppupvm;
    private Long kayttaja_id;
    private Long tapahtuma_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    private Maksutapa maksutapa;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    private Myyntikanava myyntikanava;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    private Tila tila;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    private Lipputyyppi lipputyyppi;

    public Lippu() {
    }

    public Lippu(Long lippu_id, double hinta, Maksutapa maksutapa, LocalDateTime myyntiaika, 
                Myyntikanava myyntikanava, String koodi, String paikka, LocalDateTime luontiaika, LocalDateTime alkupvm, 
                LocalDateTime loppupvm, Lipputyyppi lipputyyppi, Tila tila, Long kayttaja_id, Long tapahtuma_id) {
        this.id = lippu_id;
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
        return id;
    }

    public void setLippu_id(Long lippu_id) {
        this.id = lippu_id;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public Maksutapa getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(Maksutapa maksutapa) {
        this.maksutapa = maksutapa;
    }

    public LocalDateTime getMyyntiaika() {
        return myyntiaika;
    }

    public void setMyyntiaika(LocalDateTime myyntiaika) {
        this.myyntiaika = myyntiaika;
    }

    public Myyntikanava getMyyntikanava() {
        return myyntikanava;
    }

    public void setMyyntikanava(Myyntikanava myyntikanava) {
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

    public LocalDateTime getLuontiaika() {
        return luontiaika;
    }

    public void setLuontiaika(LocalDateTime luontiaika) {
        this.luontiaika = luontiaika;
    }

    public LocalDateTime getAlkupvm() {
        return alkupvm;
    }

    public void setAlkupvm(LocalDateTime alkupvm) {
        this.alkupvm = alkupvm;
    }

    public LocalDateTime getLoppupvm() {
        return loppupvm;
    }

    public void setLoppupvm(LocalDateTime loppupvm) {
        this.loppupvm = loppupvm;
    }

    public Tila getTila() {
        return tila;
    }

    public void setTila(Tila tila) {
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

    public Lipputyyppi getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(Lipputyyppi lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }
}
