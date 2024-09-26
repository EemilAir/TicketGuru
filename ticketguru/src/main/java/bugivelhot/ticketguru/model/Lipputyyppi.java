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
@Table(name = "lipputyypit")
public class Lipputyyppi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lipputyyppiId;
    private String lipputyyppi;
    private String kuvaus;

    @OneToMany(mappedBy = "lipputyyppi", cascade = CascadeType.ALL)
    private List<TapahtumanLipputyyppi> tapahtumanLipputyypit;

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

    public List<TapahtumanLipputyyppi> getTapahtumanLipputyypit() {
        return tapahtumanLipputyypit;
    }

    public void setTapahtumanLipputyypit(List<TapahtumanLipputyyppi> tapahtumanLipputyypit) {
        this.tapahtumanLipputyypit = tapahtumanLipputyypit;
    }

    @Override
    public String toString() {
        return "Lipputyyppi [lipputyyppiId=" + lipputyyppiId + ", lipputyyppi=" + lipputyyppi + ", kuvaus=" + kuvaus
                + "]";
    }

}
