package bugivelhot.ticketguru.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class TapahtumanLipputyyppiId implements Serializable {

    private Long tapahtumaId;
    private Long lipputyyppiId;

    public TapahtumanLipputyyppiId() {
    }

    public TapahtumanLipputyyppiId(Long tapahtumaId, Long lipputyyppiId) {
        this.tapahtumaId = tapahtumaId;
        this.lipputyyppiId = lipputyyppiId;
    }

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
