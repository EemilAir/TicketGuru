package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Tapahtuma;
import java.util.List;
import java.util.Optional;

public interface TapahtumaRepository extends JpaRepository<Tapahtuma, Long> {
    Optional<Tapahtuma> findTapahtumaByNimiContainingIgnoreCase(String nimi);
    List<Tapahtuma> findTapahtumatByNimiContainingIgnoreCase(String nimi);
    List<Tapahtuma> findByKategoriaContainingIgnoreCase(String kategoria);
    List<Tapahtuma> findTapahtumatByNimiContainingIgnoreCaseAndKategoriaContainingIgnoreCase(String nimi, String kategoria);
}