package bugivelhot.ticketguru.dto;

import bugivelhot.ticketguru.model.TapahtumanLipputyyppiId;

public class TapahtumanLipputyyppiDTO {

    private TapahtumanLipputyyppiId id;
    private String nimi;
    private String kuvaus;
    private double hinta;

    // konstruktorit
    public TapahtumanLipputyyppiDTO() {
    }

    public TapahtumanLipputyyppiDTO(TapahtumanLipputyyppiId id, String nimi, String kuvaus, double hinta) {
        this.id = id;
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
    }

    // getterit ja setterit
    public TapahtumanLipputyyppiId getId() {
        return id;
    }

    public void setId(TapahtumanLipputyyppiId id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    
}
