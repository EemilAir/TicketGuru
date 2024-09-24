package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Tapahtuma;

public interface TapahtumaRepository extends JpaRepository<Tapahtuma, Long> {

}