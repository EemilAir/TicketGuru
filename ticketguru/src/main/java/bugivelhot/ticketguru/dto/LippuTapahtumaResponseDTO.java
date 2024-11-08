package bugivelhot.ticketguru.dto;
import java.time.LocalDateTime;

public class LippuTapahtumaResponseDTO {

    private Long lippuId;
    private String koodi;
    private Integer tila;
    private LocalDateTime kayttoaika;
    private Long lipputyyppiId;
    private String lipputyyppiNimi;
    private Long tapahtumaId;
    private String tapahtumaNimi;

    // Getterit ja setterit

    public Long getLippuId() {
        return lippuId;
    }

    public void setLippuId(Long lippuId) {
        this.lippuId = lippuId;
    }

    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public Integer getTila() {
        return tila;
    }

    public void setTila(Integer tila) {
        this.tila = tila;
    }

    public Long getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(Long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public String getTapahtumaNimi() {
        return tapahtumaNimi;
    }

    public void setTapahtumaNimi(String tapahtumaNimi) {
        this.tapahtumaNimi = tapahtumaNimi;
    }

    public LocalDateTime getKayttoaika() {
        return kayttoaika;
    }

    public void setKayttoaika(LocalDateTime kayttoaika) {
        this.kayttoaika = kayttoaika;
    }

    public Long getLipputyyppiId() {
        return lipputyyppiId;
    }

    public void setLipputyyppiId(Long lipputyyppiId) {
        this.lipputyyppiId = lipputyyppiId;
    }

    public String getLipputyyppiNimi() {
        return lipputyyppiNimi;
    }

    public void setLipputyyppiNimi(String lipputyyppiNimi) {
        this.lipputyyppiNimi = lipputyyppiNimi;
    }
}