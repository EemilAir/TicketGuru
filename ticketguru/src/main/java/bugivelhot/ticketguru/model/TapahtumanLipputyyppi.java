package bugivelhot.ticketguru.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tapahtuman_lipputyypit", indexes = {
        @Index(name = "idx_tapahtuma_lipputyyppi", columnList = "tapahtuma_id, lipputyyppi_id", unique = true)
})
public class TapahtumanLipputyyppi {

    @EmbeddedId
    private TapahtumanLipputyyppiId id;

    @ManyToOne
    @MapsId("tapahtumaId")
    @JoinColumn(name = "tapahtuma_id", nullable = false)
    private Tapahtuma tapahtuma;

    @ManyToOne
    @MapsId("lipputyyppiId")
    @JoinColumn(name = "lipputyyppi_id", nullable = false)
    private Lipputyyppi lipputyyppi;

    private double hinta;

    public TapahtumanLipputyyppi(TapahtumanLipputyyppiId id, double hinta) {
        this.id = id;
        this.hinta = hinta;
    }

    public TapahtumanLipputyyppi() {
    }

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

}
