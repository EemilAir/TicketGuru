package bugivelhot.ticketguru.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Maksutapa;

public interface MaksutapaRepository extends JpaRepository<Maksutapa, Long> {
    Optional<Maksutapa> findByMaksutapaId(Long maksutapaId);
    Optional<Maksutapa> findByMaksutapaNimiContainingIgnoreCase(String maksutapaNimi);
}