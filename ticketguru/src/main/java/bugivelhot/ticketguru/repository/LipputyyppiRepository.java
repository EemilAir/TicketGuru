package bugivelhot.ticketguru.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Lipputyyppi;

public interface LipputyyppiRepository extends JpaRepository<Lipputyyppi, Long> {
    Optional<Lipputyyppi> findByLipputyyppiNimiContainingIgnoreCase(String lipputyyppiNimi);
}
