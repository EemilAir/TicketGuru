package bugivelhot.ticketguru.model;

import org.hibernate.annotations.ManyToAny;

// importit
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
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että kayttajaId on pääavain ja generoidaan
                                                    // automaattisesti
    private Long kayttajaId; // Pääavain, käyttäjän yksilöivä tunniste tietokantataulussa (kayttaja_id)
    private String kayttajanimi; // Käyttäjän tunnus, jota käytetään sisäänkirjautumiseen (kayttajanimi)
    private String sposti; // Käyttäjän sähköpostiosoite (sposti)
    private String salasanaHash; // Käyttäjän salasanan tiiviste (hash), tallennetaan turvallisuuden vuoksi
                                 // tiivistettynä (salasana_hash)

    // Käyttäjäroolit
    public enum Rooli {
        ADMIN, // Ylläpitäjä, jolla on korkeammat oikeudet järjestelmässä
        MYYJA // Käyttäjä, joka toimii myyjänä, rajoitetummat oikeudet kuin adminilla
    }

    @Enumerated(EnumType.STRING) // Tallennetaan rooli tietokantaan merkkijonona (esim. 'ADMIN' tai 'MYYJA')
    private Rooli kayttajarooli; // Käyttäjän rooli, joka määrittää käyttöoikeudet järjestelmässä (kayttajarooli)

    @ManyToOne
    @JoinColumn(name = "myyntipiste_id")
    private Lipunmyyntipiste lipunmyyntipiste;

    // Konstruktorit
    public Kayttaja(String kayttajanimi, String sposti, String salasanaHash, Rooli kayttajarooli) {
        this.kayttajanimi = kayttajanimi;
        this.sposti = sposti;
        this.salasanaHash = salasanaHash;
        this.kayttajarooli = kayttajarooli;
    }

    public Kayttaja() {
    }

    // Getterit ja Setterit, jotka mahdollistavat kenttien arvon lukemisen ja
    // muuttamisen
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

    

}
