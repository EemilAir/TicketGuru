package bugivelhot.ticketguru.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tapahtumat")
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

    @OneToMany(mappedBy = "tapahtuma", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Tapahtumapaikka> tapahtumaPaikat;

    public Tapahtuma() {
    }

    public Tapahtuma(String nimi, String kuvaus, LocalDateTime paivamaara) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.paivamaara = paivamaara;
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

    public Set<Tapahtumapaikka> getTapahtumaPaikat() {
        return tapahtumaPaikat;
    }

    public void setTapahtumaPaikat(Set<Tapahtumapaikka> tapahtumaPaikat) {
        this.tapahtumaPaikat = tapahtumaPaikat;
    }

}
