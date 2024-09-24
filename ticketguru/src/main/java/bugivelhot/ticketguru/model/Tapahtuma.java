package bugivelhot.ticketguru.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tapahtumat")
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tapahtuma_id")
    private Long tapahtumaId;

    @Column(nullable = false, length = 100)
    private String nimi;

    @Column(length = 500)
    private String kuvaus;

    @Column(name = "paivamaara")
    private LocalDateTime paivamaara;

    @Column(name = "katuosoite", nullable = false, length = 100)
    private String katuosoite;

    @ManyToOne
    @JoinColumn(name = "osoite_id", nullable = false)
    private Osoite osoite;

    public Tapahtuma() {
    }

    public Tapahtuma(String nimi, String kuvaus, LocalDateTime paivamaara, String katuosoite, Osoite osoite) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.paivamaara = paivamaara;
        this.katuosoite = katuosoite;
        this.osoite = osoite;
    }

    public Long getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(Long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public LocalDateTime getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(LocalDateTime paivamaara) {
        this.paivamaara = paivamaara;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public Osoite getOsoite() {
        return osoite;
    }

    public void setOsoite(Osoite osoite) {
        this.osoite = osoite;
    }
}
