package bugivelhot.ticketguru.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "maksutavat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "maksutavat"
public class Maksutapa {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Määrittää, että maksutapaId on pääavain ja se generoidaan automaattisesti
    private Long maksutapaId;

    @NotBlank(message = "Maksutavalla tulee olla nimi")
    @Size(min = 1, max = 20, message = "Maksutavan nimen tulee olla 1-20 merkkiä pitkä")
    @Column(unique = true) // Maksutavan nimi on uniikki
    private String maksutapaNimi;

    @OneToMany(mappedBy = "maksutapa", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Myyntitapahtuma> myyntitapahtumat;

    // konstruktorit
    public Maksutapa(String maksutapaNimi) {
        this.maksutapaNimi = maksutapaNimi;
    }

    public Maksutapa(){}

    public Long getMaksutapaId() {
        return maksutapaId;
    }

    public void setMaksutapaId(Long maksutapaId) {
        this.maksutapaId = maksutapaId;
    }

    public String getMaksutapaNimi() {
        return maksutapaNimi;
    }

    public void setMaksutapaNimi(String maksutapaNimi) {
        this.maksutapaNimi = maksutapaNimi;
    }

    public List<Myyntitapahtuma> getMyyntitapahtumat() {
        return myyntitapahtumat;
    }

    public void setMyyntitapahtumat(List<Myyntitapahtuma> myyntitapahtumat) {
        this.myyntitapahtumat = myyntitapahtumat;
    }

    @Override
    public String toString() {
        return "Maksutapa [maksutapaId=" + maksutapaId + ", maksutapaNimi=" + maksutapaNimi + ", myyntitapahtumat="
                + myyntitapahtumat + "]";
    }
}
