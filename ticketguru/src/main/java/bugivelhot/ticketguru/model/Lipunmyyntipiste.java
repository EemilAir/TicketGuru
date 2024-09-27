package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "lipunmyyntipisteet") // Määrittää, että tämä entiteetti vastaa tietokantataulua "lipunmyyntipisteet"
public class Lipunmyyntipiste {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että myyntipisteId on pääavain ja se generoidaan automaattisesti
    private Long myyntipisteId;
    private String myyntipiste;
    private String katuosoite;

   
    @ManyToOne
    @JoinColumn(name = "osoite_id", nullable = false)
    private Osoite osoite;

    @OneToMany(mappedBy = "lipunmyyntipiste")
    private List<Kayttaja> myyjat;

    // konstruktorit
    public Lipunmyyntipiste(String myyntipiste, String katuosoite) {
        this.myyntipiste = myyntipiste;
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

    public String getMyyntipiste() {
        return myyntipiste;
    }

    public void setMyyntipiste(String myyntipiste) {
        this.myyntipiste = myyntipiste;
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
        return "Lipunmyyntipiste [myyntipisteId=" + myyntipisteId + ", myyntipiste=" + myyntipiste + ", katuosoite="
                + katuosoite + ", osoite=" + osoite + ", myyjat=" + myyjat + "]";
    }

}