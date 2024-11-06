package bugivelhot.ticketguru.model;

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
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "lipunmyyntipisteet") // Määrittää, että tämä entiteetti vastaa tietokantataulua "lipunmyyntipisteet"
public class Lipunmyyntipiste {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Määrittää, että myyntipisteId on pääavain ja se generoidaan automaattisesti
    private Long myyntipisteId;

    @NotBlank(message = "Myyntipisteen nimi ei voi olla tyhjä")
    @Size(max = 100, message = "Myyntipisteen nimi voi olla korkeintaan 100 merkkiä pitkä")
    @Column(unique = true) // Myyntipisteen nimi on uniikki
    private String myyntipisteNimi;

    @NotBlank(message = "Katuosoite ei voi olla tyhjä")
    @Size(max = 100, message = "Katuosoite voi olla korkeintaan 100 merkkiä pitkä")
    private String katuosoite;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "osoite_id", nullable = false)
    private Osoite osoite;

    @OneToMany(mappedBy = "lipunmyyntipiste")
    @JsonManagedReference
    private List<Kayttaja> myyjat;

    // konstruktorit
    public Lipunmyyntipiste(String myyntipisteNimi, String katuosoite) {
        this.myyntipisteNimi = myyntipisteNimi;
        this.katuosoite = katuosoite;
    }

    public Lipunmyyntipiste() {
    }

    // getterit ja setterit
    public Long getMyyntipisteId() {
        return myyntipisteId;
    }

    public void setMyyntipisteId(Long myyntipisteId) {
        this.myyntipisteId = myyntipisteId;
    }

    public String getMyyntipisteNimi() {
        return myyntipisteNimi;
    }

    public void setMyyntipisteNimi(String myyntipisteNimi) {
        this.myyntipisteNimi = myyntipisteNimi;
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

    public List<Kayttaja> getMyyjat() {
        return myyjat;
    }

    public void setMyyjat(List<Kayttaja> myyjat) {
        this.myyjat = myyjat;
    }

    @Override
    public String toString() {
        return "Lipunmyyntipiste [myyntipisteId=" + myyntipisteId + ", myyntipisteNimi=" + myyntipisteNimi + ", katuosoite="
                + katuosoite + ", osoite=" + osoite + ", myyjat=" + myyjat + "]";
    }

}