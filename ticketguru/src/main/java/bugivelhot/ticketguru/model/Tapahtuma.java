package bugivelhot.ticketguru.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tapahtumat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "tapahtumat"
public class Tapahtuma {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Määrittää, että tapahtumaId on pääavain ja se generoidaan automaattisesti
    private Long tapahtumaId;

    @NotBlank(message = "Tapahtuman nimi ei voi olla tyhjä")
    @Size(min = 3, max = 100, message = "Tapahtuman nimi voi olla korkeintaan 100 merkkiä pitkä ja 3 merkkiä lyhyt")
    @Column(unique = true) // Määrittää, että nimi on tietokannassa uniikki
    private String nimi;

    @Size(max = 500, message = "Tapahtuman kuvaus voi olla korkeintaan 500 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String kuvaus;

    @NotBlank(message = "Tapahtuman kategoria ei voi olla tyhjä")
    @Size(min = 3, max = 75, message = "Tapahtuman kategoria voi olla korkeintaan 75 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String kategoria;

    @NotNull(message = "Tapahtuman aloituspvm ei voi olla tyhjä")
    private LocalDateTime aloituspvm;

    @NotNull(message = "Tapahtuman lopetuspvm ei voi olla tyhjä")
    private LocalDateTime lopetuspvm;

    @NotBlank(message = "Tapahtuman katuosoite ei voi olla tyhjä")
    @Size(min = 3, max = 100, message = "Tapahtuman katuosoite voi olla korkeintaan 100 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String katuosoite;

    @NotNull(message = "LippujaJaljella ei voi olla null")
    @PositiveOrZero(message = "LippujaJaljella on oltava positiivinen tai nolla")
    private Integer lippujaJaljella;

    @JsonIgnore //TempFix
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "osoite_id")
    private Osoite osoite;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL)
    private List<Lippu> liput;

    @JsonIgnore //TempFix
    @OneToMany(mappedBy = "tapahtuma", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TapahtumanLipputyyppi> tapahtumanLipputyypit;

    public Tapahtuma(String nimi, String kuvaus, String kategoria, LocalDateTime aloituspvm, LocalDateTime lopetuspvm,
    String katuosoite, Osoite osoite, Integer lippujaJaljella) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.kategoria = kategoria;
        this.aloituspvm = aloituspvm;
        this.lopetuspvm = lopetuspvm;
        this.katuosoite = katuosoite;
        this.osoite = osoite;
        this.lippujaJaljella = lippujaJaljella;
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

    public Integer getLippujaJaljella() {
        return lippujaJaljella;
    }

    public void setLippujaJaljella(Integer lippujaJaljella) {
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
