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

    private String osoite;
    private String postino;
    private String postitmp;

    public Osoite(String osoite, String postino, String postitmp) {
        this.osoite = osoite;
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

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
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
