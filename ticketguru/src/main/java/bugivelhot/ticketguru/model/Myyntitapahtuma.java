package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "myyntitapahtumat") // Määrittää, että tämä entiteetti vastaa tietokantataulua "myyntitapahtumat"
public class Myyntitapahtuma {

    // tietokantataulun kentät
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Määrittää, että myyntitapahtumaId on pääavain ja se generoidaan automaattisesti
    private Long myyntitapahtumaId;
    private double summa;
    private LocalDateTime maksupvm;

    
    @ManyToOne
    @JoinColumn(name = "maksutapa_id")
    @JsonManagedReference
    private Maksutapa maksutapa;
    
    @ManyToOne
    @JoinColumn(name = "myyja_id")
    @JsonManagedReference
    private Kayttaja kayttaja;

    /* @ManyToOne
    @JoinColumn(name = "myyntikanava_id")
    @JsonManagedReference
    private Myyntikanava myyntikanava; */
    
    // konstruktorit
    public Myyntitapahtuma(double summa, LocalDateTime maksupvm) {
        this.summa = summa;
        this.maksupvm = maksupvm;
    }

    public Myyntitapahtuma() {
    }

    // getterit ja setterit
    public Long getMyyntitapahtumaId() {
        return myyntitapahtumaId;
    }

    public void setMyyntitapahtumaId(Long myyntitapahtumaId) {
        this.myyntitapahtumaId = myyntitapahtumaId;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
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

    /* public Myyntikanava getMyyntikanava() {
        return myyntikanava;
    }

    public void setMyyntikanava(Myyntikanava myyntikanava) {
        this.myyntikanava = myyntikanava;
    } */

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    @Override
    public String toString() {
        return "Myyntitapahtuma [myyntitapahtumaId=" + myyntitapahtumaId + ", summa=" + summa + ", maksupvm=" + maksupvm
                + ", maksutapa=" + maksutapa + ", myyntikanava=" /* + myyntikanava */ + ", kayttaja=" + kayttaja + "]";
    }

}