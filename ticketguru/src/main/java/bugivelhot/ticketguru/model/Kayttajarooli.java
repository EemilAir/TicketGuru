package bugivelhot.ticketguru.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kayttajaroolit")
public class Kayttajarooli {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rooliId;

    private String roolinNimi;

    @ManyToMany(mappedBy = "kayttajaroolit")
    private Set<Kayttaja> kayttajat = new HashSet<>();

    public Kayttajarooli(String roolinNimi) {
        this.roolinNimi = roolinNimi;
    }

    public Kayttajarooli() {
    }

    public Long getRooliId() {
        return rooliId;
    }

    public void setRooliId(Long rooliId) {
        this.rooliId = rooliId;
    }

    public String getRoolinNimi() {
        return roolinNimi;
    }

    public void setRoolinNimi(String roolinNimi) {
        this.roolinNimi = roolinNimi;
    }

    public Set<Kayttaja> getKayttajat() {
        return kayttajat;
    }

    public void setKayttajat(Set<Kayttaja> kayttajat) {
        this.kayttajat = kayttajat;
    }

}
