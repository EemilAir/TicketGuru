package bugivelhot.ticketguru.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MyyntitapahtumaResponseDTO {
    private Long myyntitapahtumaId;
    private Double summa;
    private String maksutapa;
    private LocalDateTime maksupvm;
    private Long kayttajaId;

    // Lista myyntitapahtuman lipuista. Liput sisältävät vain oleelliset tiedot
    private List<LippuResponseDTO> liput;

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

    public String getMaksutapa() {
        return maksutapa;
    }

    public void setMaksutapa(String maksutapa) {
        this.maksutapa = maksutapa;
    }

    public LocalDateTime getMaksupvm() {
        return maksupvm;
    }

    public void setMaksupvm(LocalDateTime maksupvm) {
        this.maksupvm = maksupvm;
    }

    public Long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

    public List<LippuResponseDTO> getLiput() {
        return liput;
    }

    public void setLiput(List<LippuResponseDTO> liput) {
        this.liput = liput;
    }
    

}
