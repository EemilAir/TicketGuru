package bugivelhot.ticketguru.dto;

import bugivelhot.ticketguru.model.Lippu.Tila;
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

    // Tila on enum, jolle asetetaan aina joko AKTIIVINEN tai KAYTETTY, validointi ei välttämättä tarpeellinen
    @NotNull(message = "Lipun tila ei voi olla tyhjä")
    private Tila tila; // Lipun tila (esim. AKTIIVINEN, KÄYTETTY)

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
    public Tila getTila() {
        return tila;
    }
    public void setTila(Tila tila) {
        this.tila = tila;
    }

}
