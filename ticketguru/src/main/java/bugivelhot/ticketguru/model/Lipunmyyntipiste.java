package bugivelhot.ticketguru.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "lipunmyyntipisteet")
public class Lipunmyyntipiste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myyntipisteId;
    private String myyntipiste;
    private String katuosoite;

    @ManyToOne
    @JoinColumn(name = "osoite_id", nullable = false)
    private Osoite osoite;

    @OneToMany(mappedBy = "lipunmyyntipiste")
    private List<Kayttaja> myyjat;

    public Lipunmyyntipiste(String myyntipiste, String katuosoite) {
        this.myyntipiste = myyntipiste;
        this.katuosoite = katuosoite;
    }

    public Lipunmyyntipiste() {
    }

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

}