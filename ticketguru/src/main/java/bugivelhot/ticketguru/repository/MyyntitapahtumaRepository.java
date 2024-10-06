package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import java.util.List;

public interface MyyntitapahtumaRepository extends JpaRepository<Myyntitapahtuma, Long> {
    // findBy kertoo, etta kyseessa on haku, ja sen jalkeen tuleva sana kertoo, minka perusteella haku tehdaan. 
    // Suluissa oleva tyyppi kertoo, minka tyyppisia tietoja haetaan.
    List<Myyntitapahtuma> findBySumma(Double summa);
    List<Myyntitapahtuma> findByMaksutapaMaksutapaContainingIgnoreCase(String maksutapa);
    List<Myyntitapahtuma> findByKayttajaKayttajanimiContainingIgnoreCase(String kayttaja);
    List<Myyntitapahtuma> findBySummaAndMaksutapaMaksutapaContainingIgnoreCase(Double summa, String maksutapa);
   
    /*TODO
    List<Myyntitapahtuma> findByMaksupvmContainingIgnoreCase(String maksupvm);
    List<Myyntitapahtuma> findByMyyntikanavaContainingIgnoreCase(String myyntikanava);
    */
}