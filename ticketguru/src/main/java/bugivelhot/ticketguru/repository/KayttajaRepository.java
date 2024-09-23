package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bugivelhot.ticketguru.model.Kayttaja;

public interface KayttajaRepository extends JpaRepository<Kayttaja, Long> {
    
}
