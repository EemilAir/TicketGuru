package bugivelhot.ticketguru.dto;

import java.time.LocalDateTime;

public class MyyntitapahtumaDTO {

    private Long id;
    private double summa;
    private LocalDateTime maksupvm;
    private Long maksutapaId;
    // private Long myyntikanavaId;
    private Long kayttajaId;

    // Konstruktorit
    public MyyntitapahtumaDTO() {
    }

    // Getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMaksutapaId() {
        return maksutapaId;
    }

    public void setMaksutapaId(Long maksutapaId) {
        this.maksutapaId = maksutapaId;
    }

    /* public Long getMyyntikanavaId() {
        return myyntikanavaId;
    }

    public void setMyyntikanavaId(Long myyntikanavaId) {
        this.myyntikanavaId = myyntikanavaId;
    } */

    public Long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }
}