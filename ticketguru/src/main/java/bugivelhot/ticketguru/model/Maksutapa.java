package bugivelhot.ticketguru.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@Table(name = "maksutavat")
public class Maksutapa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long maksutapaId;
    private String maksutapa;

    @OneToMany(mappedBy = "maksutapa", cascade = CascadeType.ALL)
    private List<Myyntitapahtuma> myyntitapahtumat;

    public Maksutapa(String maksutapa) {
        this.maksutapa = maksutapa;
    }

    public Long getMaksutapaId() {
        return maksutapaId;
    }

    public void setMaksutapaId(Long maksutapaId) {
        this.maksutapaId = maksutapaId;
    }

    public String getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(String maksutapa) {
        this.maksutapa = maksutapa;
    }

    public List<Myyntitapahtuma> getMyyntitapahtumat() {
        return myyntitapahtumat;
    }

    public void setMyyntitapahtumat(List<Myyntitapahtuma> myyntitapahtumat) {
        this.myyntitapahtumat = myyntitapahtumat;
    }



}
