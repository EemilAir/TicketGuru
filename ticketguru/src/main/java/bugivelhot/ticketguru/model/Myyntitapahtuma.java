package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
@Table(name = "myyntitapahtumat")
public class Myyntitapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myyntitapahtumaId;
    private double summa;
    private LocalDateTime maksupvm;

    @ManyToOne
    @JoinColumn(name = "maksutapa_id")
    private Maksutapa maksutapa;

    @ManyToOne
    @JoinColumn(name = "myyntikanava_id")
    private Myyntikanava myyntikanava;

    @ManyToOne
    @JoinColumn(name = "myyja_id")
    private Kayttaja kayttaja;

    public Myyntitapahtuma(double summa, LocalDateTime maksupvm) {
        this.summa = summa;
        this.maksupvm = maksupvm;
    }

    public Myyntitapahtuma() {
    }

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

    public Myyntikanava getMyyntikanava() {
        return myyntikanava;
    }

    public void setMyyntikanava(Myyntikanava myyntikanava) {
        this.myyntikanava = myyntikanava;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

}