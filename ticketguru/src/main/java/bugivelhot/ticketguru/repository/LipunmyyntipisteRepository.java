package bugivelhot.ticketguru.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Lipunmyyntipiste;

public interface LipunmyyntipisteRepository extends JpaRepository<Lipunmyyntipiste, Long> {
    Optional<Lipunmyyntipiste> findByMyyntipisteNimiContainingIgnoreCase(String myyntipisteNimi);
}