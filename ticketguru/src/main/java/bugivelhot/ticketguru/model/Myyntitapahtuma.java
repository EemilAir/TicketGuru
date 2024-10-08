package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;
// import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "myyntitapahtumat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "myyntitapahtumat"
public class Myyntitapahtuma {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että myyntitapahtumaId on pääavain ja se generoidaan automaattisesti
    private Long myyntitapahtumaId;

    private Double summa;
    private LocalDateTime maksupvm;

    
    @ManyToOne
    @JoinColumn(name = "maksutapa_id")
    @JsonManagedReference
    private Maksutapa maksutapa;
    
    @ManyToOne
    @JoinColumn(name = "myyja_id")
    @JsonManagedReference
    private Kayttaja kayttaja;

    /* @OneToMany(mappedBy = "myyntitapahtuma")
    private List<Lippu> liput; */

    /* @ManyToOne
    @JoinColumn(name = "myyntikanava_id")
    @JsonManagedReference
    private Myyntikanava myyntikanava; */

    // konstruktorit
    public Myyntitapahtuma() {
    }

    @PrePersist // varmistaa, että maksupvm on asetettu ennen tietokantaan tallentamista
    public void prePersist() {
        this.maksupvm = LocalDateTime.now(); // asettaa nykyhetken lipun myyntiajaksi
    }

    // getterit ja setterit
    public Long getMyyntitapahtumaId() {
        return myyntitapahtumaId;
    }

    public void setMyyntitapahtumaId(Long myyntitapahtumaId) {
        this.myyntitapahtumaId = myyntitapahtumaId;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public LocalDateTime getMaksupvm() {
        return maksupvm;
    }

    public void setMaksupvm(LocalDateTime maksupvm) {
        this.maksupvm = maksupvm;
    }

    public Maksutapa getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(Maksutapa maksutapa) {
        this.maksutapa = maksutapa;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    /* public List<Lippu> getLiput() {
        return liput;
    }

    public void setLiput(List<Lippu> liput) {
        this.liput = liput;
    } */

    @Override
    public String toString() {
        return "Myyntitapahtuma [myyntitapahtumaId=" + myyntitapahtumaId + ", summa=" + summa + ", maksupvm=" + maksupvm
                + ", maksutapa=" + maksutapa + ", kayttaja=" + kayttaja /* + ", liput=" + liput */ + "]";
    }

}