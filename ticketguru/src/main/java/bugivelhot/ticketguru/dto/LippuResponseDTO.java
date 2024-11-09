package bugivelhot.ticketguru.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LippuResponseDTO {

    // Koodilla voi olla muitakin rajoituksia, esim. pituus, määritellään myöhemmin
    @NotEmpty(message = "Lipun koodi ei voi olla tyhjä")
    private String koodi; // Lipulle javan UUID:llä generoitu uniikki koodi

    @NotNull(message = "Tapahtuma ID ei voi olla tyhjä")
    private Long tapahtumaId; // Tapahtuma

    @NotEmpty(message = "Lipputyyppi ei voi olla tyhjä")
    private String lipputyyppi; // Lipputyyppi (esim. "VIP", "Normaali")

    // Tila on enum, jolle asetetaan aina joko 1 tai 2, validointi ei välttämättä tarpeellinen
    @NotNull(message = "Lipun tila ei voi olla tyhjä")
    private Integer tila; // Lipun tila (esim. AKTIIVINEN, KÄYTETTY)

    private LocalDateTime kayttoaika;

    // getterit ja setterit
    public String getKoodi() {
        return koodi;
    }
    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }
    public Long getTapahtumaId() {
        return tapahtumaId;
    }
    public void setTapahtumaId(Long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }
    public String getLipputyyppi() {
        return lipputyyppi;
    }
    public void setLipputyyppi(String lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }
    public Integer getTila() {
        return tila;
    }
    public void setTila(Integer tila) {
        this.tila = tila;
    }
    public LocalDateTime getKayttoaika() {
        return kayttoaika;
    }
    public void setKayttoaika(LocalDateTime kayttoaika) {
        this.kayttoaika = kayttoaika;
    }
    public Object map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}
