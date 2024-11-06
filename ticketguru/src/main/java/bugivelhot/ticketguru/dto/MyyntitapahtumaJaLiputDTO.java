package bugivelhot.ticketguru.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MyyntitapahtumaJaLiputDTO {

    @NotNull(message = "KäyttäjäId ei voi olla tyhjä")
    @Positive(message = "KäyttäjäId ei voi olla negatiivinen")
    private Long kayttajaId; // käyttäjä

    @NotNull(message = "MaksutapaId ei voi olla tyhjä")
    @Positive(message = "MaksutapaId ei voi olla negatiivinen")
    private Long maksutapaId; // maksutapa

    @NotNull(message = "Lippuja tulee olla vähintään 1kpl myyntitapahtumassa")
    private List<LippuDTO> liput; // liput listana, sillä niitä voi olla useita samassa myyntitapahtumassa

    // getterit
    public Long getKayttajaId() {
        return kayttajaId;
    }
    public void setKayttajaId(Long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }
    public Long getMaksutapaId() {
        return maksutapaId;
    }
    public void setMaksutapaId(Long maksutapaId) {
        this.maksutapaId = maksutapaId;
    }
    public List<LippuDTO> getLiput() {
        return liput;
    }
    public void setLiput(List<LippuDTO> liput) {
        this.liput = liput;
    }
    
}
