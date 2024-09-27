package bugivelhot.ticketguru.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tapahtumat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "tapahtumat"
public class Tapahtuma {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että tapahtumaId on pääavain ja se generoidaan automaattisesti
    private Long tapahtumaId;
    private String nimi;
    private String kuvaus;
    private String kategoria;
    private LocalDateTime aloituspvm;
    private LocalDateTime lopetuspvm;
    private String katuosoite;
    private int lippujaJaljella;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "osoite_id")
    private Osoite osoite;

    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL)
    private List<Lippu> liput;

    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL)
    private List<TapahtumanLipputyyppi> tapahtumanLipputyypit;

        public Tapahtuma(String nimi, String kuvaus, String kategoria, LocalDateTime aloituspvm, LocalDateTime lopetuspvm,
                String katuosoite, Osoite osoite, int lippujaJaljella) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.kategoria = kategoria;
        this.aloituspvm = aloituspvm;
        this.lopetuspvm = lopetuspvm;
        this.katuosoite = katuosoite;
        this.osoite = osoite;
        this.lippujaJaljella = lippujaJaljella;
        this.osoite = osoite;
    }

    public Tapahtuma() {
    }

    // getterit ja setterit
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

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public LocalDateTime getAloituspvm() {
        return aloituspvm;
    }

    public void setAloituspvm(LocalDateTime aloituspvm) {
        this.aloituspvm = aloituspvm;
    }

    public LocalDateTime getLopetuspvm() {
        return lopetuspvm;
    }

    public void setLopetuspvm(LocalDateTime lopetuspvm) {
        this.lopetuspvm = lopetuspvm;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public int getLippujaJaljella() {
        return lippujaJaljella;
    }

    public void setLippujaJaljella(int lippujaJaljella) {
        this.lippujaJaljella = lippujaJaljella;
    }

    public Osoite getOsoite() {
        return osoite;
    }

    public void setOsoite(Osoite osoite) {
        this.osoite = osoite;
    }

    public List<Lippu> getLiput() {
        return liput;
    }

    public void setLiput(List<Lippu> liput) {
        this.liput = liput;
    }

    public List<TapahtumanLipputyyppi> getTapahtumanLipputyypit() {
        return tapahtumanLipputyypit;
    }

    public void setTapahtumanLipputyypit(List<TapahtumanLipputyyppi> tapahtumanLipputyypit) {
        this.tapahtumanLipputyypit = tapahtumanLipputyypit;
    }

    @Override
    public String toString() {
        return "Tapahtuma [tapahtumaId=" + tapahtumaId + ", nimi=" + nimi + ", kuvaus=" + kuvaus + ", kategoria="
                + kategoria + ", aloituspvm=" + aloituspvm + ", lopetuspvm=" + lopetuspvm + ", katuosoite=" + katuosoite
                + ", lippujaJaljella=" + lippujaJaljella + "]";
    }

}
