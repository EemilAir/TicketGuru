package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// import java.util.List;

@Entity
@Table(name = "myyntikanavat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "myyntikanavat"
public class Myyntikanava {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että myyntikanavaId on pääavain ja se generoidaan automaattisesti
    private Long myyntikanavaId;
    private String myyntikanava;

    /* @OneToMany(mappedBy = "myyntikanava", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Myyntitapahtuma> myyntitapahtumat; */

    // konstruktorit
    public Myyntikanava(String myyntikanava) {
        this.myyntikanava = myyntikanava;
    }

    public Myyntikanava() {
    }

    // getterit ja setterit
    public Long getMyyntikanavaId() {
        return myyntikanavaId;
    }

    public void setMyyntikanavaId(Long myyntikanavaId) {
        this.myyntikanavaId = myyntikanavaId;
    }

    public String getMyyntikanava() {
        return myyntikanava;
    }

    public void setMyyntikanava(String myyntikanava) {
        this.myyntikanava = myyntikanava;
    }

   /*  public List<Myyntitapahtuma> getMyyntitapahtumat() {
        return myyntitapahtumat;
    }

    public void setMyyntitapahtumat(List<Myyntitapahtuma> myyntitapahtumat) {
        this.myyntitapahtumat = myyntitapahtumat;
    } */

    @Override
    public String toString() {
        return "Myyntikanava [myyntikanavaId=" + myyntikanavaId + ", myyntikanava=" + myyntikanava
                + ", myyntitapahtumat=" + /* myyntitapahtumat + */ "]";
    }

}
