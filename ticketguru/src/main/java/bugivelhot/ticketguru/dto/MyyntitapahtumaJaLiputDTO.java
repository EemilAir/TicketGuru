package bugivelhot.ticketguru.dto;

import java.util.List;

public class MyyntitapahtumaJaLiputDTO {
    private Long kayttajaId; // käyttäjä
    private Long maksutapaId; // maksutapa
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
