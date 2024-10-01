package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Tapahtuma;
import java.util.List;

public interface TapahtumaRepository extends JpaRepository<Tapahtuma, Long> {
    List<Tapahtuma> findByNimiContainingIgnoreCase(String nimi);
    List<Tapahtuma> findByKategoriaContainingIgnoreCase(String kategoria);
    List<Tapahtuma> findByNimiContainingIgnoreCaseAndKategoriaContainingIgnoreCase(String nimi, String kategoria);
}