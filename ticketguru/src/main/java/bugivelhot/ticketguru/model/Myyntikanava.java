package bugivelhot.ticketguru.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "myyntikanavat")
public class Myyntikanava {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myyntikanavaId;
    private String myyntikanava;

    @OneToMany(mappedBy = "myyntikanava", cascade = CascadeType.ALL)
    private List<Myyntitapahtuma> myyntitapahtumat;

    public Myyntikanava(String myyntikanava) {
        this.myyntikanava = myyntikanava;
    }

    public Myyntikanava() {
    }

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

    public List<Myyntitapahtuma> getMyyntitapahtumat() {
        return myyntitapahtumat;
    }

    public void setMyyntitapahtumat(List<Myyntitapahtuma> myyntitapahtumat) {
        this.myyntitapahtumat = myyntitapahtumat;
    }

}
