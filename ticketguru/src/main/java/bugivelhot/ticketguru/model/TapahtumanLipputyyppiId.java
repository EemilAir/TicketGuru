package bugivelhot.ticketguru.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class TapahtumanLipputyyppiId implements Serializable {

    // tietokantataulun kentät
    @NotNull(message = "TapahtumanLipputyyppiID:n tapahtumaId ei saa olla null")
    private Long tapahtumaId;

    @NotNull(message = "TapahtumanLipputyyppiID:n lipputyyppiId ei saa olla null")
    private Long lipputyyppiId;

    // konstruktorit
    public TapahtumanLipputyyppiId(Long tapahtumaId, Long lipputyyppiId) {
        this.tapahtumaId = tapahtumaId;
        this.lipputyyppiId = lipputyyppiId;
    }
    
    public TapahtumanLipputyyppiId() {
    }

    // getterit ja setterit
    public Long getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(Long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public Long getLipputyyppiId() {
        return lipputyyppiId;
    }

    public void setLipputyyppiId(Long lipputyyppiId) {
        this.lipputyyppiId = lipputyyppiId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tapahtumaId, lipputyyppiId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TapahtumanLipputyyppiId other = (TapahtumanLipputyyppiId) obj;
        return Objects.equals(tapahtumaId, other.tapahtumaId) &&
                Objects.equals(lipputyyppiId, other.lipputyyppiId);
    }

}
