package bugivelhot.ticketguru.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LipputyyppiDTO {

    @NotNull(message = "Lipputyyppi id ei voi olla null")
    @Positive(message = "Lipputyyppi ei voi olla negatiivinen")
    private Long id;

    @NotNull(message = "hinta ei voi olla null")
    @Positive(message = "hinta ei voi olla negatiivinen")
    private double hinta;

    @NotBlank(message = "lipputyypinNimi ei voi olla tyhjä")
    @Size(min = 3, max = 25, message = "lipputyypinNimi on oltava 3-25 merkkiä pitkä")
    private String lipputyyppiNimi;

    @Size(min = 3, max = 25, message = "Kuvauksen on oltava 3-250 merkkiä pitkä")
    private String kuvaus;

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
    
    public String getLipputyyppiNimi() {
        return lipputyyppiNimi;
    }

    public void setLipputyyppiNimi(String lipputyyppiNimi) {
        this.lipputyyppiNimi = lipputyyppiNimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
