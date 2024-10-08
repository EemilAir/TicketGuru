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
@Table(name = "lipputyypit") // Määrittää, että tämä entiteetti vastaa tietokantataulua "lipputyypit"
public class Lipputyyppi {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että lipputyyppiId on pääavain ja se generoidaan automaattisesti
    private Long lipputyyppiId;
    private String lipputyyppiNimi;
    private String kuvaus;

    @OneToMany(mappedBy = "lipputyyppi", cascade = CascadeType.ALL)
    private List<TapahtumanLipputyyppi> tapahtumanLipputyypit;

    @OneToMany(mappedBy = "lipputyyppi", cascade = CascadeType.ALL)
    private List<Lippu> liput;

    // konstruktorit
    public Lipputyyppi(String lipputyyppiNimi, String kuvaus) {
        this.lipputyyppiNimi = lipputyyppiNimi;
        this.kuvaus = kuvaus;
    }

    public Lipputyyppi() {
    }

    // getterit ja setterit
    public Long getLipputyyppiId() {
        return lipputyyppiId;
    }

    public void setLipputyyppiId(Long lipputyyppiId) {
        this.lipputyyppiId = lipputyyppiId;
    }

    public String getLipputyyppiNimi() {
        return lipputyyppiNimi;
    }

    public void setLipputyyppiNimi(String lipputyyppiNimi) {
        this.lipputyyppiNimi = lipputyyppiNimi;
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

    public List<Lippu> getLiput() {
        return liput;
    }

    public void setLiput(List<Lippu> liput) {
        this.liput = liput;
    }

    @Override
    public String toString() {
        return "Lipputyyppi [lipputyyppiId=" + lipputyyppiId + ", lipputyyppiNimi=" + lipputyyppiNimi + ", kuvaus="
                + kuvaus + ", tapahtumanLipputyypit=" + tapahtumanLipputyypit + ", liput=" + liput + "]";
    }
}
