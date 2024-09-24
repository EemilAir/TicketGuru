package bugivelhot.ticketguru.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lipputyypit")
public class Lipputyyppi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
    private String nimi;


    public Lipputyyppi() {
    }  

    public Lipputyyppi(Long id, String nimi) {
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
