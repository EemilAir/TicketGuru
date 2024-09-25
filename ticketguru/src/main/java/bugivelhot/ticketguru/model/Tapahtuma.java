package bugivelhot.ticketguru.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "tapahtumat")
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tapahtumaId;
    private String nimi;
    private String kuvaus;
    private LocalDateTime aloituspvm;
    private LocalDateTime lopetuspvm;
    private String katuosoite;
    private int lippuja_jaljella;

    @ManyToOne
    @JoinColumn(name = "osoite_id")
    private Osoite osoite;

    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL)
    private List<Lippu> liput;

    @ManyToMany
    @JoinTable(name = "tapahtuman_lipputyypit", joinColumns = @JoinColumn(name = "tapahtuma_id"), inverseJoinColumns = @JoinColumn(name = "lipputyyppi_id"))
    private List<Lipputyyppi> lipputyypit;

    public Tapahtuma(String nimi, String kuvaus, LocalDateTime aloituspvm, LocalDateTime lopetuspvm, String katuosoite,
            int lippuja_jaljella) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.aloituspvm = aloituspvm;
        this.lopetuspvm = lopetuspvm;
        this.katuosoite = katuosoite;
        this.lippuja_jaljella = lippuja_jaljella;
    }

    public Tapahtuma() {
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

    public int getLippuja_jaljella() {
        return lippuja_jaljella;
    }

    public void setLippuja_jaljella(int lippuja_jaljella) {
        this.lippuja_jaljella = lippuja_jaljella;
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

    public List<Lipputyyppi> getLipputyypit() {
        return lipputyypit;
    }

    public void setLipputyypit(List<Lipputyyppi> lipputyypit) {
        this.lipputyypit = lipputyypit;
    }

}
