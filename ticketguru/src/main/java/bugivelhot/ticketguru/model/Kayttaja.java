package bugivelhot.ticketguru.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kayttajat")
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kayttajaId;

    private String etunimi;
    private String sukunimi;
    private String puhelinno;
    private String sposti;
    private String katuosoite;
    private LocalDate syntymaAika;
    private String salasanaHash;

    @ManyToOne
    @JoinColumn(name = "osoite_id")
    private Osoite osoite;

    @ManyToMany
    @JoinTable(name = "kayttajan_roolit", // välitaulun nimi
            joinColumns = @JoinColumn(name = "kayttaja_id"), inverseJoinColumns = @JoinColumn(name = "rooli_id"))
    private Set<Kayttajarooli> kayttajaroolit = new HashSet<>();

    public Kayttaja(String etunimi, String sukunimi, String puhelinno, String sposti, LocalDate syntymaAika,
            String salasanaHash, String katuosoite, Osoite osoite) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelinno = puhelinno;
        this.sposti = sposti;
        this.syntymaAika = syntymaAika;
        this.salasanaHash = salasanaHash;
        this.katuosoite = katuosoite;
        this.osoite = osoite;
    }

    public Kayttaja() {
    }

    public Long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getPuhelinno() {
        return puhelinno;
    }

    public void setPuhelinno(String puhelinno) {
        this.puhelinno = puhelinno;
    }

    public String getSposti() {
        return sposti;
    }

    public void setSposti(String sposti) {
        this.sposti = sposti;
    }

    public LocalDate getSyntymaAika() {
        return syntymaAika;
    }

    public void setSyntymaAika(LocalDate syntymaAika) {
        this.syntymaAika = syntymaAika;
    }

    public String getSalasanaHash() {
        return salasanaHash;
    }

    public void setSalasanaHash(String salasanaHash) {
        this.salasanaHash = salasanaHash;
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

    public Set<Kayttajarooli> getKayttajaroolit() {
        return kayttajaroolit;
    }

    public void setKayttajaroolit(Set<Kayttajarooli> kayttajaroolit) {
        this.kayttajaroolit = kayttajaroolit;
    }
}
