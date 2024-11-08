package bugivelhot.ticketguru.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.model.Myyntitapahtuma;

public interface LippuRepository extends JpaRepository<Lippu, Long> {
    List<Lippu> findByMyyntitapahtuma(Myyntitapahtuma myyntitapahtuma); // Metodi lipun hakemiseksi myyntitapahtuman perusteella
    Optional<Lippu> findByKoodi(String koodi); // Metodi lipun hakemiseksi koodin perusteella
    Optional<Lippu> findByLippuId(Long lippuId);
}
