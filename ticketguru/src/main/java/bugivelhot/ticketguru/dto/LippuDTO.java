package bugivelhot.ticketguru.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class LippuDTO {

    @NotNull(message = "Tapahtuma ID ei voi olla tyhjä")
    private Long tapahtumaId; // tapahtuma

    @NotNull(message = "Lipputyyppi ID ei voi olla tyhjä")
    private Long lipputyyppiId; // lipputyyppi

    @Min(value = 1, message = "Lippujen määrän on oltava vähintään 1")
    private int maara; // lippujen määrä

    // getterit ja setterit
    public Long getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(Long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public Long getLipputyyppiId() {
        return lipputyyppiId;
    }

    public void setLipputyyppiId(Long lipputyyppiId) {
        this.lipputyyppiId = lipputyyppiId;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }
}