package bugivelhot.ticketguru.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "osoitteet") // Määrittää, että tämä entiteetti vastaa tietokantataulua "osoitteet"
public class Osoite {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että osoiteId on pääavain ja se generoidaan automaattisesti
    private Long osoiteId;
    private String postinumero;
    private String postitmp;

    @OneToMany(mappedBy = "osoite", cascade = CascadeType.ALL)
    private List<Lipunmyyntipiste> Lipunmyyntipisteet;

    @OneToMany(mappedBy = "osoite", cascade = CascadeType.ALL)
    private List<Tapahtuma> tapahtumat;

    // konstruktorit
    public Osoite(String postinumero, String postitmp) {
        this.postinumero = postinumero;
        this.postitmp = postitmp;
    }

    public Osoite() {
    }

    // getterit ja setterit
    public Long getOsoiteId() {
        return osoiteId;
    }

    public void setOsoiteId(Long osoiteId) {
        this.osoiteId = osoiteId;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public String getPostitmp() {
        return postitmp;
    }

    public void setPostitmp(String postitmp) {
        this.postitmp = postitmp;
    }

    public List<Lipunmyyntipiste> getLipunmyyntipisteet() {
        return Lipunmyyntipisteet;
    }

    public void setLipunmyyntipisteet(List<Lipunmyyntipiste> lipunmyyntipisteet) {
        Lipunmyyntipisteet = lipunmyyntipisteet;
    }

    public List<Tapahtuma> getTapahtumat() {
        return tapahtumat;
    }

    public void setTapahtumat(List<Tapahtuma> tapahtumat) {
        this.tapahtumat = tapahtumat;
    }

    @Override
    public String toString() {
        return "Osoite [osoiteId=" + osoiteId + ", postinumero=" + postinumero + ", postitmp=" + postitmp
                + ", Lipunmyyntipisteet=" + Lipunmyyntipisteet + ", tapahtumat=" + tapahtumat + "]";
    }

}
