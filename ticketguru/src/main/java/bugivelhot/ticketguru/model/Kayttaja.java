package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "kayttajat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "kayttajat"
public class Kayttaja {

    // Tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että kayttajaId on pääavain ja se generoidaan automaattisesti
    private Long kayttajaId;
    private String kayttajanimi;
    private String sposti;
    private String salasanaHash; 

    // Käyttäjäroolit
    public enum Rooli {
        ADMIN,
        MYYJA;
    }

    @Enumerated(EnumType.STRING) // Tallennetaan rooli tietokantaan merkkijonona (esim. 'ADMIN' tai 'MYYJA')
    private Rooli kayttajarooli;

    @ManyToOne
    @JoinColumn(name = "myyntipiste_id")
    private Lipunmyyntipiste lipunmyyntipiste;

    // Konstruktorit
    public Kayttaja(String kayttajanimi, String sposti, String salasanaHash) {
        this.kayttajanimi = kayttajanimi;
        this.sposti = sposti;
        this.salasanaHash = salasanaHash;
    }

    public Kayttaja() {
    }

    // Getterit ja Setterit
    public Long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

    public String getKayttajanimi() {
        return kayttajanimi;
    }

    public void setKayttajanimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }

    public String getSposti() {
        return sposti;
    }

    public void setSposti(String sposti) {
        this.sposti = sposti;
    }

    public String getSalasanaHash() {
        return salasanaHash;
    }

    public void setSalasanaHash(String salasanaHash) {
        this.salasanaHash = salasanaHash;
    }

    public Rooli getKayttajarooli() {
        return kayttajarooli;
    }

    public void setKayttajarooli(Rooli kayttajarooli) {
        this.kayttajarooli = kayttajarooli;
    }

    public Lipunmyyntipiste getLipunmyyntipiste() {
        return lipunmyyntipiste;
    }

    public void setLipunmyyntipiste(Lipunmyyntipiste lipunmyyntipiste) {
        this.lipunmyyntipiste = lipunmyyntipiste;
    }

    @Override
    public String toString() {
        return "Kayttaja [kayttajaId=" + kayttajaId + ", kayttajanimi=" + kayttajanimi + ", sposti=" + sposti
                + ", salasanaHash=" + salasanaHash + ", kayttajarooli=" + kayttajarooli + ", lipunmyyntipiste="
                + lipunmyyntipiste + "]";
    }

}
