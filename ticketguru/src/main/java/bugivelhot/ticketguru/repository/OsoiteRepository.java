package bugivelhot.ticketguru.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Osoite;

public interface OsoiteRepository extends JpaRepository<Osoite, Long> {
    Optional<Osoite> findByPostinumero(String postinumero);
}