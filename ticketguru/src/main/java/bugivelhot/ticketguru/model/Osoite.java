package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "osoitteet")
public class Osoite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long osoiteId;

    private String postino;
    private String postitmp;

    public Osoite(String postino, String postitmp) {
        this.postino = postino;
        this.postitmp = postitmp;
    }

    public Osoite() {
    }

    public Long getOsoiteId() {
        return osoiteId;
    }

    public void setOsoiteId(Long osoiteId) {
        this.osoiteId = osoiteId;
    }

    public String getPostino() {
        return postino;
    }

    public void setPostino(String postino) {
        this.postino = postino;
    }

    public String getPostitmp() {
        return postitmp;
    }

    public void setPostitmp(String postitmp) {
        this.postitmp = postitmp;
    }

}
