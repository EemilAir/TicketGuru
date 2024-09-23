package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tila {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nimi;

    public Tila() {
    }  

    public Tila(Long id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }  
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
