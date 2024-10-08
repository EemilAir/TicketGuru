package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import java.util.List;

public interface MyyntitapahtumaRepository extends JpaRepository<Myyntitapahtuma, Long> {
    // findBy kertoo, etta kyseessa on haku, ja sen jalkeen tuleva sana kertoo, minka perusteella haku tehdaan. 
    // Suluissa oleva tyyppi kertoo, minka tyyppisia tietoja haetaan.
    List<Myyntitapahtuma> findBySumma(Double summa);
    List<Myyntitapahtuma> findByMaksutapa_MaksutapaNimiContainingIgnoreCase(String maksutapaNimi); // metodin nimeä muutettu, koska maksutavan nimi on muutettu -> maksutapaNimi. Spring Boot ei osaa muuttaa tätä automaattisesti.
    List<Myyntitapahtuma> findByKayttajaKayttajanimiContainingIgnoreCase(String kayttaja);
    List<Myyntitapahtuma> findBySummaAndMaksutapa_MaksutapaNimiContainingIgnoreCase(Double summa, String maksutapaNimi); // metodin nimeä muutettu, koska maksutavan nimi on muutettu -> maksutapaNimi. Spring Boot ei osaa muuttaa tätä automaattisesti.
   
    /*TODO:  
    Muut mahdolliset haut
    */
}