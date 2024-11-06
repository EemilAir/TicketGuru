package bugivelhot.ticketguru.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "kayttajat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "kayttajat"
public class Kayttaja {

    // Tietokantataulun kentät
    // Määrittää, että kayttajaId on pääavain ja se generoidaan automaattisesti
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long kayttajaId;
    
    // Käyttäjänimi ei voi olla tyhjä ja sen pituus on 5-25 merkkiä
    @NotBlank(message = "Käyttäjänimi ei voi olla tyhjä")
    @Size(min = 3, max = 25, message = "Käyttäjänimen on oltava 5-25 merkkiä pitkä")
    @Column(unique = true) // Käyttäjänimi on uniikki
    private String kayttajanimi;

    // Sähköposti on pakollinen ja sen tulee olla oikeassa muodossa
    @NotBlank(message = "Sähköposti ei voi olla tyhjä")
    @Email(message = "Sähköpostin tulee olla oikeassa muodossa")
    @Column(unique = true) // Sähköposti on uniikki
    private String sposti;

    // Salasanan hash-arvolle voi asettaa myös muita rajoituksia, esim. pituus, määritellään myöhemmin
    @NotBlank(message = "Salasana ei voi olla tyhjä")
    private String salasanaHash; 

    // Käyttäjäroolit
    // Ei tarvitse validointia, koska enum on määritelty ja käyttäjärooli on pakollinen
    public enum Rooli {
        ADMIN,
        MYYJA;
    }

    // Tallennetaan rooli tietokantaan merkkijonona (esim. 'ADMIN' tai 'MYYJA')
    @Enumerated(EnumType.STRING) 
    // Käyttäjällä on aina jokin rooli
    @NotNull(message = "Käyttäjärooli ei voi olla tyhjä")
    private Rooli kayttajarooli;

    @ManyToOne
    @JoinColumn(name = "myyntipiste_id")
    @JsonBackReference
    private Lipunmyyntipiste lipunmyyntipiste;

    @OneToMany(mappedBy = "kayttaja", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Myyntitapahtuma> myyntitapahtumat;

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
