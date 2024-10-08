package bugivelhot.ticketguru.dto;

import bugivelhot.ticketguru.model.Lippu.Tila;

public class LippuResponseDTO {

    private String koodi; // Lipulle javan UUID:llä generoitu uniikki koodi
    private Long tapahtumaId; // Tapahtuma
    private String lipputyyppi; // Lipputyyppi (esim. "VIP", "Normaali")
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
