package bugivelhot.ticketguru.dto;

public class LipputyyppiResponseDTO {

    private Long id;
    private String lipputyyppiNimi;
    private String kuvaus;

    // Default constructor
    public LipputyyppiResponseDTO() {
    }

    // All args constructor
    public LipputyyppiResponseDTO(Long id, String lipputyyppiNimi, Double hinta, String kuvaus) {
        this.id = id;
        this.lipputyyppiNimi = lipputyyppiNimi;
        this.kuvaus = kuvaus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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