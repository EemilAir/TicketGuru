package bugivelhot.ticketguru.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lipputyypit")
public class Lipputyyppi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lipputyyppiId;
    private String lipputyyppi;
    private String kuvaus;

    @ManyToMany(mappedBy = "lipputyypit")
    private List<Tapahtuma> tapahtumat;

    public Lipputyyppi(String lipputyyppi, String kuvaus) {
        this.lipputyyppi = lipputyyppi;
        this.kuvaus = kuvaus;
    }

    public Lipputyyppi() {
    }

    public Long getLipputyyppiId() {
        return lipputyyppiId;
    }

    public void setLipputyyppiId(Long lipputyyppiId) {
        this.lipputyyppiId = lipputyyppiId;
    }

    public String getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(String lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public List<Tapahtuma> getTapahtumat() {
        return tapahtumat;
    }

    public void setTapahtumat(List<Tapahtuma> tapahtumat) {
        this.tapahtumat = tapahtumat;
    }

    
}
