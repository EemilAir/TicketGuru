package bugivelhot.ticketguru.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LipputyyppiDTO {

    @NotNull(message = "Lipputyyppi id ei voi olla null")
    @Positive(message = "Lipputyyppi ei voi olla negatiivinen")
    private Long id;

    @NotNull(message = "hinta ei voi olla null")
    @Positive(message = "hinta ei voi olla negatiivinen")
    private double hinta;

    // konstruktorit
    public LipputyyppiDTO() {
    }

    public LipputyyppiDTO(Long id, double hinta) {
        this.id = id;
        this.hinta = hinta;
    }

    // getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }
    
}
