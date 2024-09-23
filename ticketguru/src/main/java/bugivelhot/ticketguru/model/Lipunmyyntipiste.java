package bugivelhot.ticketguru.model;

import jakarta.persistence.*;

@Entity
public class Lipunmyyntipiste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "myyntipiste_id")
    private Long myyntipisteId;

    @Column(name = "myyntipiste_nimi", nullable = false, length = 100)
    private String myyntipisteNimi;

    @ManyToOne
    @JoinColumn(name = "osoite_id", nullable = false)
    private Osoite osoite;

    @ManyToOne
    @JoinColumn(name = "myyja_id", nullable = false)
    private Kayttaja myyja;

    public Lipunmyyntipiste() {
    }

    public Lipunmyyntipiste(String myyntipisteNimi, Osoite osoite, Kayttaja myyja) {
        this.myyntipisteNimi = myyntipisteNimi;
        this.osoite = osoite;
        this.myyja = myyja;
    }

    public Long getMyyntipisteId() {
        return myyntipisteId;
    }

    public void setMyyntipisteId(Long myyntipisteId) {
        this.myyntipisteId = myyntipisteId;
    }

    public String getMyyntipisteNimi() {
        return myyntipisteNimi;
    }

    public void setMyyntipisteNimi(String myyntipisteNimi) {
        this.myyntipisteNimi = myyntipisteNimi;
    }

    public Osoite getOsoite() {
        return osoite;
    }

    public void setOsoite(Osoite osoite) {
        this.osoite = osoite;
    }

    public Kayttaja getMyyja() {
        return myyja;
    }

    public void setMyyja(Kayttaja myyja) {
        this.myyja = myyja;
    }
}