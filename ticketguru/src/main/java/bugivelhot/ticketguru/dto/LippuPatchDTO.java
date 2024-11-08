package bugivelhot.ticketguru.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class LippuPatchDTO {
    
    @Min(value = 0, message = "Lipun tila voi olla vain 0 tai 1")
    @Max(value = 1, message = "Lipun tila voi olla vain 0 tai 1")
    private Integer tila;
    
    public Integer getTila() {
        return tila;
    }

    public void setTila(Integer tila) {
        this.tila = tila;
    }
}
