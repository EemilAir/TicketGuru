package bugivelhot.ticketguru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// Tämä entiteetti vastaa tietokantataulua "tapahtuman_lipputyypit". 
// Taulussa on indeksi "idx_tapahtuma_lipputyyppi", joka kattaa sarakkeet "tapahtuma_id" ja "lipputyyppi_id". 
// Indeksi parantaa hakujen suorituskykyä ja estää duplikaattien syntymisen kyseisissä sarakkeissa.
@Entity
@Table(name = "tapahtuman_lipputyypit", indexes = {
        @Index(name = "idx_tapahtuma_lipputyyppi", columnList = "tapahtuma_id, lipputyyppi_id", unique = true)
}) 
public class TapahtumanLipputyyppi {

    @EmbeddedId  // määrittää yhdistetyn pääavaimen (tapahtuma_id, lipputyyppi_id)
    @NotNull(message = "TapahtumaLipputyypin TapahtumanLipputyyppiId ei voi olla null")
    private TapahtumanLipputyyppiId id;

    @ManyToOne
    @JsonIgnore
    @MapsId("tapahtumaId") // viittaa TapahtumanLipputyyppiId-luokassa olevaan tapahtumaId-kenttään.
    @JoinColumn(name = "tapahtuma_id", nullable = false) // Määrittää, että tapahtuma_id on osa yhdistettyä pääavainta
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JsonBackReference
    @MapsId("lipputyyppiId") // viittaa TapahtumanLipputyyppiId-luokassa olevaan lipputyyppiId-kenttään.
    @JoinColumn(name = "lipputyyppi_id", nullable = false) // Määrittää, että lipputyyppi_id on osa yhdistettyä pääavainta
    private Lipputyyppi lipputyyppi;

    @Positive(message = "Hinnan täytyy olla positiivinen")
    private double hinta;

    // konstruktorit
    public TapahtumanLipputyyppi(TapahtumanLipputyyppiId id, double hinta) {
        this.id = id;
        this.hinta = hinta;
    }

    public TapahtumanLipputyyppi() {
    }

    // getterit ja setterit
    public TapahtumanLipputyyppiId getId() {
        return id;
    }

    public void setId(TapahtumanLipputyyppiId id) {
        this.id = id;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    public Lipputyyppi getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(Lipputyyppi lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    @Override
    public String toString() {
        return "TapahtumanLipputyyppi [id=" + id + ", tapahtuma=" + tapahtuma + ", lipputyyppi=" + lipputyyppi
                + ", hinta=" + hinta + "]";
    }
}
