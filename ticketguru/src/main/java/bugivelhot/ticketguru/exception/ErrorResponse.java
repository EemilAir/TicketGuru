package bugivelhot.ticketguru.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String viesti;
    private LocalDateTime aikaleima;
    private int tila;
    private String virhe;
    private String polku;

    // Konstruktori
    public ErrorResponse(String viesti, LocalDateTime aikaleima, int tila, String virhe, String polku) {
        this.viesti = viesti;
        this.aikaleima = aikaleima;
        this.tila = tila;
        this.virhe = virhe;
        this.polku = polku;
    }

    // Getterit ja setterit
    public String getViesti() {
        return viesti;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }

    public LocalDateTime getAikaleima() {
        return aikaleima;
    }

    public void setAikaleima(LocalDateTime aikaleima) {
        this.aikaleima = aikaleima;
    }

    public int getTila() {
        return tila;
    }

    public void setTila(int tila) {
        this.tila = tila;
    }

    public String getVirhe() {
        return virhe;
    }

    public void setVirhe(String virhe) {
        this.virhe = virhe;
    }

    public String getPolku() {
        return polku;
    }

    public void setPolku(String polku) {
        this.polku = polku;
    }
   
}
