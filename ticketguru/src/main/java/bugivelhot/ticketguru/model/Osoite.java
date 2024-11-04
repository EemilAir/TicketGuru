package bugivelhot.ticketguru.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "osoitteet") // Määrittää, että tämä entiteetti vastaa tietokantataulua "osoitteet"
public class Osoite {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Määrittää, että osoiteId on pääavain ja se generoidaan automaattisesti
    private Long osoiteId;

    @NotBlank(message = "Postinumero ei voi olla tyhjä")
    @Size(min = 5, max = 5, message = "Postinumeron tulee olla 5 merkkiä pitkä")
    @Column(unique = true) // Määrittää, että postinumero on tietokannassa uniikki
    private String postinumero;

    @NotBlank(message = "Postitoimipaikka ei voi olla tyhjä")
    @Size(max = 100, message = "Postitoimipaikka voi olla korkeintaan 100 merkkiä pitkä")
    private String postitmp;

    @OneToMany(mappedBy = "osoite", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lipunmyyntipiste> lipunmyyntipisteet;

    @OneToMany(mappedBy = "osoite", cascade = CascadeType.ALL)
    @JsonManagedReference
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
        return lipunmyyntipisteet;
    }

    public void setLipunmyyntipisteet(List<Lipunmyyntipiste> lipunmyyntipisteet) {
        this.lipunmyyntipisteet = lipunmyyntipisteet;
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
                + ", lipunmyyntipisteet=" + lipunmyyntipisteet + ", tapahtumat=" + tapahtumat + "]";
    }

}
