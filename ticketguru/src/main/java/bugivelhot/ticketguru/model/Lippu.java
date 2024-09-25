package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
@Table(name = "liput")
public class Lippu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lippuId;
    private String koodi;
    private LocalDateTime luontiaika;
    private LocalDateTime myyntiaika;

    public enum Tila {
        AKTIIVINEN,
        KAYTETTY
    }

    @Enumerated(EnumType.STRING)
    private Tila lipunTila;
    
    @ManyToOne
    @JoinColumn(name = "tapahtuma_id")
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JoinColumn(name = "myyntitapahtuma_id")
    private Myyntitapahtuma myyntitapahtuma;

    public Lippu(String koodi, LocalDateTime luontiaika, LocalDateTime myyntiaika, Tila lipunTila) {
        this.koodi = koodi;
        this.luontiaika = luontiaika;
        this.myyntiaika = myyntiaika;
        this.lipunTila = lipunTila;
    }

    public Lippu(){}

    public Long getLippuId() {
        return lippuId;
    }

    public void setLippuId(Long lippuId) {
        this.lippuId = lippuId;
    }

    public LocalDateTime getMyyntiaika() {
        return myyntiaika;
    }

    public void setMyyntiaika(LocalDateTime myyntiaika) {
        this.myyntiaika = myyntiaika;
    }

    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public LocalDateTime getLuontiaika() {
        return luontiaika;
    }

    public void setLuontiaika(LocalDateTime luontiaika) {
        this.luontiaika = luontiaika;
    }

    public Tila getLipunTila() {
        return lipunTila;
    }

    public void setLipunTila(Tila lipunTila) {
        this.lipunTila = lipunTila;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    public Myyntitapahtuma getMyyntitapahtuma() {
        return myyntitapahtuma;
    }

    public void setMyyntitapahtuma(Myyntitapahtuma myyntitapahtuma) {
        this.myyntitapahtuma = myyntitapahtuma;
    }

}
