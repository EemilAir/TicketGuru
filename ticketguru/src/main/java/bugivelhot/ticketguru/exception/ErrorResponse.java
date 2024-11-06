package bugivelhot.ticketguru.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private String viesti;
    private LocalDateTime aikaleima;
    private int tilakoodi;
    private String tila;
    private String polku;
    private Map<String, String> virheet;

    // Konstruktori
    public ErrorResponse(String viesti, LocalDateTime aikaleima, int tilakoodi, String tila, String polku) {
        this.viesti = viesti;
        this.aikaleima = aikaleima;
        this.tilakoodi = tilakoodi;
        this.tila = tila;
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

    public int getTilakoodi() {
        return tilakoodi;
    }

    public void setTilakoodi(int tilakoodi) {
        this.tilakoodi = tilakoodi;
    }

    public String getTila() {
        return tila;
    }

    public void setTila(String tila) {
        this.tila = tila;
    }

    public String getPolku() {
        return polku;
    }

    public void setPolku(String polku) {
        this.polku = polku;
    }

    public Map<String, String> getVirheet() {
        if (virheet == null) {
            return new HashMap<>(); // Palauttaa tyhjän Map-olion, jos virheet on null
        }
        return virheet;
    }

    public void setVirheet(Map<String, String> virheet) {
        this.virheet = virheet;
    }

    
   
}
