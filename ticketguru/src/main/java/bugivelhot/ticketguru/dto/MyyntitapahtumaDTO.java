package bugivelhot.ticketguru.dto;

import java.time.LocalDateTime;

public class MyyntitapahtumaDTO {

    private Long id;
    private Double summa; // Muutettu double -> Double
    private LocalDateTime maksupvm;
    private Long maksutapaId;
    // private Long myyntikanavaId;
    private Long kayttajaId;

    // Konstruktorit
    public MyyntitapahtumaDTO() {
    }

    public MyyntitapahtumaDTO(Long id, Double summa, LocalDateTime maksupvm, Long maksutapaId, /* Long myyntikanavaId, */ Long kayttajaId) {
        this.id = id;
        this.summa = summa;
        this.maksupvm = maksupvm;
        this.maksutapaId = maksutapaId;
        // this.myyntikanavaId = myyntikanavaId;
        this.kayttajaId = kayttajaId;
    }  

    // Getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMaksutapaId() {
        return maksutapaId;
    }

    public void setMaksutapaId(Long maksutapaId) {
        this.maksutapaId = maksutapaId;
    }

    public Long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }
}