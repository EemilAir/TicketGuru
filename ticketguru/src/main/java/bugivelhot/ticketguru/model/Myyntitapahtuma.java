package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "myyntitapahtumat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "myyntitapahtumat"
public class Myyntitapahtuma {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Määrittää, että myyntitapahtumaId on pääavain ja se generoidaan automaattisesti
    private Long myyntitapahtumaId;

    @Positive(message = "Summan pitää olla positiivinen luku")
    private Double summa;

    @NotNull(message = "Maksupvm ei voi olla tyhjä")
    private LocalDateTime maksupvm;

    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "maksutapa_id")
    private Maksutapa maksutapa;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "myyja_id")
    private Kayttaja kayttaja;

    @OneToMany(mappedBy = "myyntitapahtuma", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Lippu> liput;

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