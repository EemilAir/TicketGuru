package bugivelhot.ticketguru.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.TapahtumanLipputyyppi;
import bugivelhot.ticketguru.model.TapahtumanLipputyyppiId;

public interface TapahtumanLipputyyppiRepository extends JpaRepository<TapahtumanLipputyyppi, TapahtumanLipputyyppiId> {
    Optional<TapahtumanLipputyyppi> findById_TapahtumaIdAndId_LipputyyppiId(Long tapahtumaId, Long lipputyyppiId); // metodin nimeä muutettu, koska Spring Boot vaatii, että metodin nimessä on selkeästi viitattu tapahtumaId ja lipputyyppiId.
}
