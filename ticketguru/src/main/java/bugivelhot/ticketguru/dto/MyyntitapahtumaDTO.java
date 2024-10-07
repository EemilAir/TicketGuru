package bugivelhot.ticketguru.dto;

import java.time.LocalDateTime;

public class MyyntitapahtumaDTO {

    private Double summa;
    private LocalDateTime maksupvm;
    private Long maksutapaId;
    private Long myyntikanavaId;
    private Long kayttajaId;

    // Konstruktorit
    public MyyntitapahtumaDTO() {
    }

    // Myyntitapahtuman id:tä ei tarvita konstruktoriin, koska se generoidaan automaattisesti
    // Konstuktorille annetaan pelkästään käyttäjän id, sillä muut tiedot voivat muuttua lippujen lisäämisen yhteydessä. Muille tiedoille lisätään oletusarvot MyyntitapahtumaService-luokassa.
    public MyyntitapahtumaDTO(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

    // getterit ja setterit
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

    public Long getMyyntikanavaId() {
        return myyntikanavaId;
    }

    public void setMyyntikanavaId(Long myyntikanavaId) {
        this.myyntikanavaId = myyntikanavaId;
    }

    public Long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

}