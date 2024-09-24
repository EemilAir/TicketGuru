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
    private Long id;
    private LocalDateTime maksupvm;
    private double summa;

    @ManyToOne
    @JoinColumn(name = "tapahtuma_id")
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JoinColumn(name = "lippu_id")
    private Lippu lippu;

    public Myyntitapahtuma() {
    }

    public Myyntitapahtuma(Long myyntitapahtuma_id, LocalDateTime maksupvm, double summa, Tapahtuma tapahtuma,
            Lippu lippu) {
        this.id = myyntitapahtuma_id;
        this.maksupvm = maksupvm;
        this.summa = summa;
        this.tapahtuma = tapahtuma;
        this.lippu = lippu;
    }

    public Long getMyyntitapahtuma_id() {
        return id;
    }

    public void setMyyntitapahtuma_id(Long myyntitapahtuma_id) {
        this.id = myyntitapahtuma_id;
    }

    public LocalDateTime getMaksupvm() {
        return maksupvm;
    }

    public void setMaksupvm(LocalDateTime maksupvm) {
        this.maksupvm = maksupvm;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    public Lippu getLippu() {
        return lippu;
    }

    public void setLippu(Lippu lippu) {
        this.lippu = lippu;
    }
}