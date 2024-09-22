package bugivelhot.ticketguru.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kayttaja_id")
    private Long kayttajaId;

    private String etunimi;
    private String sukunimi;
    private String puhelinno;
    private String sposti;

    @Column(name = "syntyma_aika")
    private LocalDate syntymaAika;
    @Column(name = "salasana_hash")
    private String salasanaHash;

    @ManyToOne
    @JoinColumn(name = "osoite_id")
    private Osoite osoite;

    @ManyToOne
    @JoinColumn(name = "rooli_id")
    private Kayttajarooli kayttajarooli;

    public Kayttaja(String etunimi, String sukunimi, String puhelinno, String sposti, LocalDate syntymaAika,
            String salasanaHash, Osoite osoite, Kayttajarooli kayttajarooli) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelinno = puhelinno;
        this.sposti = sposti;
        this.syntymaAika = syntymaAika;
        this.salasanaHash = salasanaHash;
        this.osoite = osoite;
        this.kayttajarooli = kayttajarooli;
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

    public Osoite getOsoite() {
        return osoite;
    }

    public void setOsoite(Osoite osoite) {
        this.osoite = osoite;
    }

    public Kayttajarooli getKayttajarooli() {
        return kayttajarooli;
    }

    public void setKayttajarooli(Kayttajarooli kayttajarooli) {
        this.kayttajarooli = kayttajarooli;
    }

}
