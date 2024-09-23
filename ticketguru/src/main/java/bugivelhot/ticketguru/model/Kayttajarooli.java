package bugivelhot.ticketguru.model;

import jakarta.persistence.*;

@Entity
public class Kayttajarooli {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rooli_id")
    private Long rooliId;

    @Column(name = "rooli_nimi")
    private String roolinNimi;

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

}
