package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.TapahtumanLipputyyppi;
import bugivelhot.ticketguru.model.TapahtumanLipputyyppiId;

public interface TapahtumanLipputyypitRepository extends JpaRepository<TapahtumanLipputyyppi, TapahtumanLipputyyppiId> {

}
